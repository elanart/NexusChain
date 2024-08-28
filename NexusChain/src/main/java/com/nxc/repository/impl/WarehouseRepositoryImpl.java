package com.nxc.repository.impl;

import com.nxc.pojo.Warehouse;
import com.nxc.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class WarehouseRepositoryImpl implements WarehouseRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(warehouse);
    }

    @Override
    public Warehouse findById(Long id) {
        return this.getCurrentSession().get(Warehouse.class, id);
    }

    @Override
    public void delete(Warehouse warehouse) {
        Session session = this.getCurrentSession();
        session.delete(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        Session session = this.getCurrentSession();
        return session.createQuery("from Warehouse", Warehouse.class).getResultList();
    }
}
