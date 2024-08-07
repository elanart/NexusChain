/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.repository.impl;

import com.nxc.pojo.Supplier;
import com.nxc.pojo.User;
import com.nxc.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
        CriteriaQuery<String> q = b.createQuery(String.class);

        Root rootSupplier = q.from(User.class);
        q.select(rootSupplier);

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Supplier supplier) {
        Session s = this.factory.getObject().getCurrentSession();
        User user = supplier.getUser();

        // Save or update User
        if (user.getId() == null) {
            s.save(user);
        } else {
            s.update(user);
        }

        // Save or update Supplier
        if (supplier.getId() == null) {
            s.save(supplier);
        } else {
            s.update(supplier);
        }
    }
}
