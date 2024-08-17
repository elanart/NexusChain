package com.nxc.repository.impl;

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
import javax.persistence.criteria.Root;
import java.util.List;
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
        Session session = this.getCurrentSession();
        session.saveOrUpdate(order);
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
    public List<Order> findAll() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }
}
