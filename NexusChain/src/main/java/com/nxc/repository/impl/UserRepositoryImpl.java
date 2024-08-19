package com.nxc.repository.impl;

import com.nxc.enums.RoleEnum;
import com.nxc.pojo.User;
import com.nxc.repository.UserRepository;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public User findById(Long id) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));

        Query query = session.createQuery(criteria);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findUser(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        List<Predicate> predicates = new ArrayList<>();
        String userId = params.get("userId");
        if (userId != null && !userId.isEmpty()) {
            Predicate p1 = builder.equal(root.get("userId"), Long.parseLong(userId));
            predicates.add(p1);
        }

        String email = params.get("email");
        if (email != null && !email.isEmpty()) {
            Predicate p2 = builder.equal(root.get("email"), email);
            predicates.add(p2);
        }

        String kw = params.get("fullName");
        if (kw != null && !kw.isEmpty()) {
            Predicate p3 = builder.like(root.get("fullName"), String.format("%%%s%%", kw));
            predicates.add(p3);
        }

        String role = params.get("role");
        if (role != null && !role.isEmpty()) {
            try {
                RoleEnum userRole = RoleEnum.valueOf(role);
                Predicate p4 = builder.equal(root.get("role"), userRole);
                predicates.add(p4);
            } catch (IllegalArgumentException e) {
                Logger.getLogger(UserRepositoryImpl.class.getName()).log(Level.SEVERE , null, e);
            }

        }

        criteria.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(User user) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public List<User> findAll() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }
}
