package com.nxc.repository;

import com.nxc.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    void saveOrUpdate(Order order);
    void delete(Order order);
    Order findById(Long id);
    List<Order> findAll(Map<String, String> params);
}
