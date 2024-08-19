package com.nxc.repository;

import com.nxc.pojo.OrderDetail;

public interface OrderDetailRepository {
    void saveOrUpdate(OrderDetail orderDetail);
    OrderDetail findById(Long id);
}
