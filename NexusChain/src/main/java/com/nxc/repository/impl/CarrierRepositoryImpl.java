package com.nxc.repository.impl;

import com.nxc.pojo.Carrier;
import com.nxc.repository.CarrierRepository;
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
public class CarrierRepositoryImpl implements CarrierRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void saveCarrier(Carrier carrier) {
        Session session = this.getCurrentSession();
        session.save(carrier);
    }

    @Override
    public Carrier findById(Long id) {
        Session session = this.getCurrentSession();
        return session.get(Carrier.class, id);
    }

    @Override
    public List<Carrier> findAll() {
        Session session = this.getCurrentSession();
        return session.createCriteria(Carrier.class).list();
    }

    @Override
    public void delete(Carrier carrier) {
        Session session = this.getCurrentSession();
        session.delete(carrier);
    }
}
