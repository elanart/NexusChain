/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.repository.impl;

import com.nxc.pojo.Supplier;
import com.nxc.repository.SupplierRepository;
import java.util.List;
import javax.persistence.Query;
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
public class SupplierRepositoryImpl implements SupplierRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Supplier> getSupplier() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Supplier");
        return q.getResultList();
    }
    
    
}
