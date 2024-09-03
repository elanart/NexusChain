package com.nxc.repository.impl;

import com.nxc.pojo.SupplierRating;
import com.nxc.repository.SupplierRatingRepository;
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
public class SupplierRatingRepositoryImpl implements SupplierRatingRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public SupplierRating save(SupplierRating supplierRating) {
        Session session = getCurrentSession();
        session.save(supplierRating);
        return supplierRating;
    }

    @Override
    public SupplierRating findById(Long id) {
        Session session = getCurrentSession();
        return session.get(SupplierRating.class, id);
    }

    @Override
    public List<SupplierRating> findBySupplierId(Long supplierId) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SupplierRating> criteria = builder.createQuery(SupplierRating.class);
        Root<SupplierRating> root = criteria.from(SupplierRating.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("supplier").get("id"), supplierId));

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Session session = getCurrentSession();
        SupplierRating supplierRating = session.get(SupplierRating.class, id);
        session.delete(supplierRating);
    }
}
