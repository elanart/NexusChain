package com.nxc.service.impl;

import com.nxc.enums.RoleEnum;
import com.nxc.pojo.*;
import com.nxc.service.DataLoaderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DataLoaderServiceImpl implements DataLoaderService {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void loadData() {
        Session session = this.getCurrentSession();

        Category category1 = Category.builder().name("Electronics").description("Electronics and gadgets").build();
        Category category2 = Category.builder().name("Books").description("Books and literature").build();
        Category category3 = Category.builder().name("Clothing").description("Clothing and apparel").build();
        session.save(category1);
        session.save(category2);
        session.save(category3);

        User user1 = User.builder().fullName("John Doe").email("john.doe@example.com").role(RoleEnum.ROLE_CARRIER).isConfirm(true).build();
        User user2 = User.builder().fullName("Jane Smith").email("jane.smith@example.com").role(RoleEnum.ROLE_DISTRIBUTOR).isConfirm(true).build();
        User user3 = User.builder().fullName("Alice Johnson").email("alice.johnson@example.com").role(RoleEnum.ROLE_SUPPLIER).isConfirm(true).build();
        session.save(user1);
        session.save(user2);
        session.save(user3);

        Account account1 = Account.builder().username("john123").password("password123").user(user1).build();
        Account account2 = Account.builder().username("jane456").password("password456").user(user2).build();
        Account account3 = Account.builder().username("alice789").password("password789").user(user3).build();
        session.save(account1);
        session.save(account2);
        session.save(account3);

        Supplier supplier1 = Supplier.builder().user(user3).paymentTerms("Net 30 days").build();
        Supplier supplier2 = Supplier.builder().user(user2).paymentTerms("Net 15 days").build();
        Supplier supplier3 = Supplier.builder().user(user1).paymentTerms("Net 45 days").build();
        session.save(supplier1);
        session.save(supplier2);
        session.save(supplier3);

        Product product1 = Product.builder().name("Laptop").price(new BigDecimal("1200.00")).category(category1).build();
        Product product2 = Product.builder().name("Smartphone").price(new BigDecimal("800.00")).category(category1).build();
        Product product3 = Product.builder().name("Novel").price(new BigDecimal("15.00")).category(category2).build();
        session.save(product1);
        session.save(product2);
        session.save(product3);

        Order order1 = Order.builder().orderDate(new Date()).user(user1).build();
        Order order2 = Order.builder().orderDate(new Date()).user(user2).build();
        Order order3 = Order.builder().orderDate(new Date()).user(user3).build();
        session.save(order1);
        session.save(order2);
        session.save(order3);

        OrderDetail orderDetail1 = OrderDetail.builder().order(order1).product(product1).quantity(2).price(new BigDecimal("2400.00")).build();
        OrderDetail orderDetail2 = OrderDetail.builder().order(order2).product(product2).quantity(1).price(new BigDecimal("800.00")).build();
        OrderDetail orderDetail3 = OrderDetail.builder().order(order3).product(product3).quantity(5).price(new BigDecimal("75.00")).build();
        session.save(orderDetail1);
        session.save(orderDetail2);
        session.save(orderDetail3);
    }
}
