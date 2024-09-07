package com.nxc.repository.impl;

import com.nxc.pojo.Invoice;
import com.nxc.pojo.Order;
import com.nxc.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

import static org.eclipse.persistence.jpa.JpaHelper.createQuery;

@Repository
@Transactional
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void save(Invoice invoice) {
        Session session = getCurrentSession();
        session.save(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        Session session = getCurrentSession();
        session.update(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        Session session = getCurrentSession();
        session.delete(invoice);
    }

    @Override
    public Invoice findById(Long id) {
        Session session = getCurrentSession();
        return (Invoice) session.get(Invoice.class, id);
    }

    @Override
    public Invoice findInvoiceByOrderId(Long orderId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Invoice where order.id = :orderId");
        query.setParameter("orderId", orderId);
        return (Invoice) query.getSingleResult();
    }

    @Override
    public List<Invoice> findAll() {
        Session session = getCurrentSession();
        return session.createQuery("from Invoice", Invoice.class).list();
    }

    @Override
    public List<Invoice> findByOrder(Order order) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Invoice where order = :order");
        query.setParameter("order", order);
        return query.getResultList();
    }

    @Override
    public List<Invoice> findByIsPaid(Boolean isPaid) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Invoice where isPaid = :isPaid");
        query.setParameter("isPaid", isPaid);
        return query.getResultList();
    }
}