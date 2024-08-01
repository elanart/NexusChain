/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.repository.impl;

import com.nxc.pojo.Supplier;
import com.nxc.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tuann
 */
@Repository
@Transactional
public class SupplierRepositoryImpl implements SupplierRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Supplier> getSuppliers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Supplier> q = b.createQuery(Supplier.class);
        Root root = q.from(Supplier.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Supplier supplier) {
        Session s = this.factory.getObject().getCurrentSession();
        if (supplier.getId() != null) {
            s.update(supplier);
        } else {
            s.save(supplier);
        }
    }
}
