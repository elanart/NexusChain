/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.repository.impl;

import com.nxc.pojo.Warehouse;
import com.nxc.repository.WarehouseRepository;
import java.util.Objects;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tuann
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class WarehouseRepositoryImpl implements WarehouseRepository{
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }
    
    @Override
    public Warehouse findById(String id) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Warehouse> criteria = builder.createQuery(Warehouse.class);
        Root<Warehouse> root = criteria.from(Warehouse.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));

        Query query = session.createQuery(criteria);
        return (Warehouse) query.getSingleResult();
    }
    
    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(warehouse);
    }
    
}
