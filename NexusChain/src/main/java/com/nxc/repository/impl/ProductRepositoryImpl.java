package com.nxc.repository.impl;

import com.nxc.pojo.Product;
import com.nxc.pojo.Supplier;
import com.nxc.pojo.SupplierProduct;
import com.nxc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public Product findById(Long id) {
        Session session = this.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        criteria.select(product);
        criteria.where(builder.equal(product.get("isDeleted"), false));

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Product product) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public void delete(Product product) {
        Session session = this.getCurrentSession();
        product.setIsDeleted(true);
        session.saveOrUpdate(product);
    }

    @Override
    public List<Product> findProductsByUserId(Long userId) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);

        Join<Product, SupplierProduct> supplierProductJoin = productRoot.join("supplierProducts");
        Join<SupplierProduct, Supplier> supplierJoin = supplierProductJoin.join("supplier");

        criteria.select(productRoot)
                .where(
                        builder.and(
                                builder.equal(supplierJoin.get("user").get("id"), userId),
                                builder.isFalse(productRoot.get("isDeleted"))
                        )
                );

        Query query = session.createQuery(criteria);
        return query.getResultList();
    }
}
