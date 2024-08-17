package com.nxc.repository.impl;

import com.nxc.pojo.Category;
import com.nxc.repository.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public Category findById(Long id) {
        Session session = this.getCurrentSession();
        return session.get(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = criteria.from(Category.class);
        criteria.select(root);

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Category category) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public void delete(Category category) {
        Session session = this.getCurrentSession();
        session.delete(category);
    }
}
