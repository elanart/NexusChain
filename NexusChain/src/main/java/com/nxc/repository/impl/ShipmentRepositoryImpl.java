package com.nxc.repository.impl;

import com.nxc.pojo.Shipment;
import com.nxc.repository.ShipmentRepository;
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
public class ShipmentRepositoryImpl implements ShipmentRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Shipment shipment) {
        Session session = getCurrentSession();
        session.saveOrUpdate(shipment);
    }

    @Override
    public Shipment findById(Long id) {
        Session session = getCurrentSession();
        return session.get(Shipment.class, id);
    }

    @Override
    public List<Shipment> findAll() {
        Session session = getCurrentSession();
        return session.createCriteria(Shipment.class).list();
    }

    @Override
    public void delete(Shipment shipment) {
        Session session = getCurrentSession();
        session.delete(shipment);
    }
}
