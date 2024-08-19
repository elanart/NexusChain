package com.nxc.repository.impl;

import com.nxc.pojo.OrderDetail;
import com.nxc.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }
    @Override
    public void saveOrUpdate(OrderDetail orderDetail) {
        Session session = this.getCurrentSession();
        if (orderDetail.getId() == null) {
            this.getCurrentSession().save(orderDetail);
        } else {
            this.getCurrentSession().update(orderDetail);
        }
    }

    @Override
    public OrderDetail findById(Long id) {
        return this.getCurrentSession().get(OrderDetail.class, id);
    }
}
