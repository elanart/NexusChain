package com.nxc.service.impl;

import com.nxc.enums.OrderTypeEnum;
import com.nxc.enums.RoleEnum;
import com.nxc.enums.ShippingStatusEnum;
import com.nxc.pojo.*;
import com.nxc.service.DataLoaderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public void loadData() {
        Session session = this.getCurrentSession();

        // Create categories
        Category electronics = Category.builder().name("Electronics").description("Electronics and gadgets").build();
        Category books = Category.builder().name("Books").description("Books and literature").build();
        Category clothing = Category.builder().name("Clothing").description("Clothing and apparel").build();
        session.save(electronics);
        session.save(books);
        session.save(clothing);

        // Create users
        User user1 = User.builder().fullName("John Doe").email("john.doe@example.com").role(RoleEnum.ROLE_CARRIER).isConfirm(true).build();
        User user2 = User.builder().fullName("Jane Smith").email("jane.smith@example.com").role(RoleEnum.ROLE_DISTRIBUTOR).isConfirm(true).build();
        User user3 = User.builder().fullName("Alice Johnson").email("alice.johnson@example.com").role(RoleEnum.ROLE_SUPPLIER).isConfirm(true).build();
        session.save(user1);
        session.save(user2);
        session.save(user3);

        // Create accounts
        Account account1 = Account.builder().username("john123").password(this.passwordEncoder.encode("123456")).user(user1).build();
        Account account2 = Account.builder().username("jane456").password(this.passwordEncoder.encode("123456")).user(user2).build();
        Account account3 = Account.builder().username("alice789").password(this.passwordEncoder.encode("123456")).user(user3).build();
        session.save(account1);
        session.save(account2);
        session.save(account3);

        // Create suppliers
        Supplier supplier1 = Supplier.builder().user(user3).paymentTerms("Net 30 days").build();
        session.save(supplier1);

        // Create warehouses
        Warehouse warehouse1 = Warehouse.builder().location("Location A").capacity(1500).build();
        Warehouse warehouse2 = Warehouse.builder().location("Location B").capacity(8000).build();
        Warehouse warehouse3 = Warehouse.builder().location("Location C").capacity(9700).build();
        session.save(warehouse1);
        session.save(warehouse2);
        session.save(warehouse3);

        // Create products
        Product laptop = Product.builder().name("Laptop").price(new BigDecimal("1200.00")).category(electronics).build();
        Product smartphone = Product.builder().name("Smartphone").price(new BigDecimal("800.00")).category(electronics).build();
        Product novel = Product.builder().name("Novel").price(new BigDecimal("15.00")).category(books).build();
        session.save(laptop);
        session.save(smartphone);
        session.save(novel);

        SupplierProduct supplierProduct1 = SupplierProduct.builder().supplier(supplier1).product(laptop).build();
        SupplierProduct supplierProduct2 = SupplierProduct.builder().supplier(supplier1).product(smartphone).build();
        SupplierProduct supplierProduct3 = SupplierProduct.builder().supplier(supplier1).product(novel).build();
        session.save(supplierProduct1);
        session.save(supplierProduct2);
        session.save(supplierProduct3);

        // Create inventory for each product in each warehouse
        Inventory laptopInventory1 = Inventory.builder().product(laptop).warehouse(warehouse1).quantity(50).build();
        Inventory laptopInventory2 = Inventory.builder().product(laptop).warehouse(warehouse2).quantity(30).build();
        Inventory smartphoneInventory1 = Inventory.builder().product(smartphone).warehouse(warehouse1).quantity(100).build();
        Inventory smartphoneInventory2 = Inventory.builder().product(smartphone).warehouse(warehouse3).quantity(50).build();
        Inventory novelInventory1 = Inventory.builder().product(novel).warehouse(warehouse2).quantity(200).build();
        Inventory novelInventory2 = Inventory.builder().product(novel).warehouse(warehouse3).quantity(150).build();
        session.save(laptopInventory1);
        session.save(laptopInventory2);
        session.save(smartphoneInventory1);
        session.save(smartphoneInventory2);
        session.save(novelInventory1);
        session.save(novelInventory2);

        // Create orders
        Order order1 = Order.builder().orderDate(new Date()).user(user3).warehouse(warehouse1).type(OrderTypeEnum.INBOUND).build();
        Order order2 = Order.builder().orderDate(new Date()).user(user2).warehouse(warehouse2).type(OrderTypeEnum.OUTBOUND).build();
        Order order3 = Order.builder().orderDate(new Date()).user(user3).warehouse(warehouse3).type(OrderTypeEnum.INBOUND).build();
        session.save(order1);
        session.save(order2);
        session.save(order3);

        // Create order details
        OrderDetail orderDetail1 = OrderDetail.builder().order(order1).product(laptop).quantity(2).price(new BigDecimal("2400.00")).build();
        OrderDetail orderDetail2 = OrderDetail.builder().order(order2).product(smartphone).quantity(1).price(new BigDecimal("800.00")).build();
        OrderDetail orderDetail3 = OrderDetail.builder().order(order3).product(novel).quantity(5).price(new BigDecimal("75.00")).build();
        session.save(orderDetail1);
        session.save(orderDetail2);
        session.save(orderDetail3);

        Carrier carrier1 = Carrier.builder()
                .cooperationTerms("Standard Cooperation Terms")
                .rating(new BigDecimal("4.5"))
                .user(user1)
                .build();
        session.save(carrier1);

        Shipment shipment1 = Shipment.builder()
                .trackingNumber("TRACK123456")
                .shipmentDate(new Date())
                .status(ShippingStatusEnum.SHIPPED)
                .expectedDelivery(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 1 week later
                .cost(new BigDecimal("150.00"))
                .destination("Ho Chi Minh City")
                .carrier(carrier1)
                .warehouse(warehouse1)
                .build();
        session.save(shipment1);
    }
}

