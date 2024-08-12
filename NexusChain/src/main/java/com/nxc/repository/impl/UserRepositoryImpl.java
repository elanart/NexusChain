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
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

//            Predicate isValid = builder.and(
//                    builder.equal(root.get("isDeleted"), false),
//                    builder.equal(root.get("isConfirm"), true)
//            );
//            predicates.add(isValid);

            String fullName = params.get("fullName");
            if (fullName != null && !fullName.isEmpty()) {
                Predicate p1 = builder.like(root.get("fullName"), String.format("%%%s%%", fullName));
                predicates.add(p1);
            }

            String role = params.get("role");
            if (role != null && !role.isEmpty()) {
                RoleEnum userRole = RoleEnum.valueOf(role);
                Predicate p2 = builder.equal(root.get("role"), userRole);
                predicates.add(p2);
            }

            criteria.where(predicates.toArray(Predicate[]::new));
        }

        Query query = session.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public User saveOrUpdate(User user) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public void delete(User user) {
        Session session = this.getCurrentSession();
        user.setIsDeleted(true);
        session.update(user);
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("username"), username));

        Query query = session.createQuery(criteria);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findByRole(RoleEnum role) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        List<Predicate> predicates = new ArrayList<>();

        Predicate isValid = builder.and(
                builder.equal(root.get("isDeleted"), false),
                builder.equal(root.get("isConfirm"), true)
        );
        predicates.add(isValid);

        Predicate userRole = builder.equal(root.get("role"), role);
        predicates.add(userRole);

        criteria.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(criteria);

        return query.getResultList();
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
}
