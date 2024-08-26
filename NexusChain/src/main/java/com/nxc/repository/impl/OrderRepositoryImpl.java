package com.nxc.repository.impl;

import com.nxc.pojo.Account;
import com.nxc.pojo.Order;
import com.nxc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Order order) {
        if (order.getId() == null) {
            this.getCurrentSession().save(order);
        } else {
            this.getCurrentSession().update(order);
        }
    }

    @Override
    public void delete(Order order) {
        Session session = this.getCurrentSession();
        order.setIsDeleted(true);
        session.saveOrUpdate(order);
    }

    @Override
    public Order findById(Long id) {
        Session session = this.getCurrentSession();
        return session.get(Order.class, id);
    }

    @Override
    public List<Order> findAll(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);

        List<Predicate> predicates = new ArrayList<>();

        String userId = params.get("userId");
        if (userId != null && !userId.isEmpty()) {
            Predicate p1 = builder.equal(root.get("user").get("id"), Long.parseLong(userId));
            predicates.add(p1);
        }

        String username = params.get("username");
        if (username != null && !username.isEmpty()) {
            Predicate p2 = builder.equal(root.get("user").get("account").get("username"), username);
            predicates.add(p2);
        }

        criteria.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }
}
