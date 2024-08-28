package com.nxc.repository.impl;

import com.nxc.pojo.Inventory;
import com.nxc.repository.InventoryRepository;
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
public class InventoryRepositoryImpl implements InventoryRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Inventory inventory) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(inventory);
    }

    @Override
    public Inventory findById(Long id) {
        Session session = this.getCurrentSession();
        return session.get(Inventory.class, id);
    }

    @Override
    public void delete(Inventory inventory) {
        Session session = this.getCurrentSession();
        session.delete(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        Session session = this.getCurrentSession();
        return session.createCriteria(Inventory.class).list();
    }

    @Override
    public Inventory getByWarehouseId(Long warehouseId) {
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Inventory WHERE warehouse.id = :warehouseId", Inventory.class)
                .setParameter("warehouseId", warehouseId)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public Inventory findByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        Session session = this.getCurrentSession();
        return session.createQuery("FROM Inventory WHERE product.id = :productId AND warehouse.id = :warehouseId", Inventory.class)
                .setParameter("productId", productId)
                .setParameter("warehouseId", warehouseId)
                .uniqueResult();
    }
}
