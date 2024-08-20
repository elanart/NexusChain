package com.nxc.repository.impl;

import com.nxc.pojo.Supplier;
import com.nxc.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        Session session = this.getCurrentSession();
        session.save(supplier);
    }
}
