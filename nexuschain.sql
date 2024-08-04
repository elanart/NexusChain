use nexuschainscm;

CREATE TABLE user(
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role NVARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE supplier(
    id VARCHAR(50) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    address NVARCHAR(255),
    phone VARCHAR(50),
    contact_info NVARCHAR(100),
    payment_terms NVARCHAR(100),
    rating DECIMAL(3, 2),
    active BOOLEAN DEFAULT TRUE,
    user_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE supplier_rating(
    id VARCHAR(50) PRIMARY KEY,
    review_date DATETIME,
    criteria NVARCHAR(100),
    rating DECIMAL(3, 2),
    comments TEXT,
    supplier_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

CREATE TABLE customer(
    id VARCHAR(50) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    address NVARCHAR(255),
    phone VARCHAR(50),
    contact_info NVARCHAR(100),
    active BOOLEAN DEFAULT TRUE,
    user_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE category(
    id VARCHAR(50) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE product(
    id VARCHAR(50) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    description TEXT, 
    price DECIMAL(10, 2), 
    quantity INT NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    category_id VARCHAR(50),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE supplier_product(
    supplier_id VARCHAR(50),
    product_id VARCHAR(50),
    PRIMARY KEY(supplier_id, product_id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE tax_rate(
    id VARCHAR(50) PRIMARY KEY,
    region VARCHAR(100),
    rate DECIMAL(5, 2)
);

CREATE TABLE invoice(
    id VARCHAR(50) PRIMARY KEY,
    invoice_date DATETIME NOT NULL,
    total_amount DECIMAL(10, 2),
    paid BOOLEAN DEFAULT FALSE,
    tax_id VARCHAR(50),
    FOREIGN KEY (tax_id) REFERENCES tax_rate(id)
);

CREATE TABLE shipment_company(
    id VARCHAR(50) PRIMARY KEY,
    company_name NVARCHAR(255) NOT NULL,
    contact_info NVARCHAR(255),
    active BOOLEAN DEFAULT TRUE,
    rating DECIMAL(5, 2),
    user_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE shipment(
    id VARCHAR(50) PRIMARY KEY,
    shipment_date DATETIME NOT NULL,
    status NVARCHAR(50) NOT NULL CHECK (status IN ('Shipped', 'In Transit', 'Delivered', 'Returned')),
    tracking_number VARCHAR(100),
    expected_delivery DATETIME,
    company_id VARCHAR(50),
    FOREIGN KEY (company_id) REFERENCES shipment_company(id)
);



CREATE TABLE purchase_order(
    id VARCHAR(50) PRIMARY KEY,
    order_date DATETIME,
    status NVARCHAR(50) NOT NULL CHECK (status IN ('Pending', 'Completed', 'Cancelled')),
    supplier_id VARCHAR(50),
    invoice_id VARCHAR(50) UNIQUE,
    shipment_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (shipment_id) REFERENCES shipment(id)
);

CREATE TABLE purchase_order_detail(
    id VARCHAR(50) PRIMARY KEY,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    product_id VARCHAR(50),
    purchase_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (purchase_id) REFERENCES purchase_order(id)
);

CREATE TABLE sale_order(
    id VARCHAR(50) PRIMARY KEY,
    order_date DATETIME,
    status NVARCHAR(50) NOT NULL CHECK (status IN ('Pending', 'Completed', 'Cancelled')),
    customer_id VARCHAR(50),
    invoice_id VARCHAR(50) UNIQUE,
    shipment_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (shipment_id) REFERENCES shipment(id)
);

CREATE TABLE sale_order_detail(
    id VARCHAR(50) PRIMARY KEY,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    product_id VARCHAR(50),
    sale_id VARCHAR(50) UNIQUE,
    FOREIGN KEY (sale_id) REFERENCES sale_order(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE warehouse(
    id VARCHAR(50) PRIMARY KEY,
    location NVARCHAR(255),
    capacity INT,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE inventory(
    id VARCHAR(50) PRIMARY KEY,
    quantity INT NOT NULL,
    warehouse_id VARCHAR(50),
    product_id VARCHAR(50),
    FOREIGN KEY (warehouse_id) REFERENCES warehouse(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE pricing(
    price DECIMAL(10, 2) NOT NULL,
    effective_date DATETIME NOT NULL,
    product_id VARCHAR(50),
    supplier_id VARCHAR(50),
    PRIMARY KEY(product_id, supplier_id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

-- Insert unique users first
INSERT INTO user (id, username, password, email, role, active) VALUES
(UUID(), 'johndoe', 'password123', 'johndoe@example.com', 'Supplier', TRUE),
(UUID(), 'janedoe', 'password456', 'janedoe@example.com', 'Customer', TRUE),
(UUID(), 'shipco', 'password789', 'shipco@example.com', 'ShipmentCompany', TRUE),
(UUID(), 'adminuser', 'adminpass', 'admin@example.com', 'Admin', TRUE),
(UUID(), 'guestuser', 'guestpass', 'guest@example.com', 'Guest', TRUE);

-- Insert suppliers with unique user_ids
INSERT INTO supplier (id, name, address, phone, contact_info, payment_terms, rating, active, user_id) VALUES
(UUID(), 'Supplier A', '123 Elm St', '555-1234', 'contact@supplierA.com', 'Net 30', 4.5, TRUE, (SELECT id FROM user WHERE username='johndoe')),
(UUID(), 'Supplier B', '456 Oak St', '555-5678', 'contact@supplierB.com', 'Net 45', 3.8, TRUE, (SELECT id FROM user WHERE username='adminuser'));

-- Insert supplier ratings
INSERT INTO supplier_rating (id, review_date, criteria, rating, comments, supplier_id) VALUES
(UUID(), '2024-07-15', 'Product Quality', 4.7, 'Excellent quality!', (SELECT id FROM supplier WHERE name='Supplier A')),
(UUID(), '2024-08-01', 'Delivery Timeliness', 4.0, 'Timely delivery but packaging could be improved.', (SELECT id FROM supplier WHERE name='Supplier B'));

-- Insert customers with unique user_ids
INSERT INTO customer (id, name, address, phone, contact_info, active, user_id) VALUES
(UUID(), 'Customer X', '789 Pine St', '555-9999', 'contact@customerX.com', TRUE, (SELECT id FROM user WHERE username='janedoe')),
(UUID(), 'Customer Y', '101 Maple St', '555-0000', 'contact@customerY.com', TRUE, (SELECT id FROM user WHERE username='guestuser'));

-- Insert categories
INSERT INTO category (id, name, description) VALUES
(UUID(), 'Electronics', 'All kinds of electronic gadgets'),
(UUID(), 'Home Appliances', 'Appliances for home use');

-- Insert products
INSERT INTO product (id, name, description, price, quantity, active, category_id) VALUES
(UUID(), 'Smartphone', 'Latest model smartphone', 699.99, 100, TRUE, (SELECT id FROM category WHERE name='Electronics')),
(UUID(), 'Washing Machine', 'Front-load washing machine', 499.99, 50, TRUE, (SELECT id FROM category WHERE name='Home Appliances'));

-- Insert supplier_products
INSERT INTO supplier_product (supplier_id, product_id) VALUES
((SELECT id FROM supplier WHERE name='Supplier A' LIMIT 1), (SELECT id FROM product WHERE name='Smartphone' LIMIT 1)),
((SELECT id FROM supplier WHERE name='Supplier B' LIMIT 1), (SELECT id FROM product WHERE name='Washing Machine' LIMIT 1));

-- Insert tax_rates
INSERT INTO tax_rate (id, region, rate) VALUES
(UUID(), 'Region A', 7.50),
(UUID(), 'Region B', 5.00);

-- Insert invoices
INSERT INTO invoice (id, invoice_date, total_amount, paid, tax_id) VALUES
(UUID(), '2024-08-01', 699.99, FALSE, (SELECT id FROM tax_rate WHERE region='Region A')),
(UUID(), '2024-08-02', 499.99, TRUE, (SELECT id FROM tax_rate WHERE region='Region B'));

-- Insert shipment_companies with unique user_ids
INSERT INTO shipment_company (id, company_name, contact_info, active, rating, user_id) VALUES
(UUID(), 'ShipCo Logistics', 'contact@shipco.com', TRUE, 4.2, (SELECT id FROM user WHERE username='shipco')),
(UUID(), 'FastShip Inc.', 'contact@fastship.com', TRUE, 4.8, (SELECT id FROM user WHERE username='adminuser'));

-- Insert shipments
INSERT INTO shipment (id, shipment_date, status, tracking_number, expected_delivery, company_id) VALUES
(UUID(), '2024-08-02', 'Shipped', 'TRACK1234', '2024-08-10', (SELECT id FROM shipment_company WHERE company_name='ShipCo Logistics')),
(UUID(), '2024-08-03', 'In Transit', 'TRACK5678', '2024-08-12', (SELECT id FROM shipment_company WHERE company_name='FastShip Inc.'));

-- Insert purchase_orders
INSERT INTO purchase_order (id, order_date, status, supplier_id, invoice_id, shipment_id) VALUES
(UUID(), '2024-08-01', 'Completed', (SELECT id FROM supplier WHERE name='Supplier A' LIMIT 1), (SELECT id FROM invoice WHERE total_amount=699.99 LIMIT 1), (SELECT id FROM shipment WHERE tracking_number='TRACK1234' LIMIT 1)),
(UUID(), '2024-08-02', 'Pending', (SELECT id FROM supplier WHERE name='Supplier B' LIMIT 1), (SELECT id FROM invoice WHERE total_amount=499.99 LIMIT 1), (SELECT id FROM shipment WHERE tracking_number='TRACK5678' LIMIT 1));

-- Insert purchase_order_details
INSERT INTO purchase_order_detail (id, quantity, price, product_id, purchase_id) VALUES
(UUID(), 1, 699.99, (SELECT id FROM product WHERE name='Smartphone' LIMIT 1), (SELECT id FROM purchase_order WHERE order_date='2024-08-01' LIMIT 1)),
(UUID(), 2, 499.99, (SELECT id FROM product WHERE name='Washing Machine' LIMIT 1), (SELECT id FROM purchase_order WHERE order_date='2024-08-02' LIMIT 1));

-- Insert sale_orders
INSERT INTO sale_order (id, order_date, status, customer_id, invoice_id, shipment_id) VALUES
(UUID(), '2024-08-03', 'Completed', (SELECT id FROM customer WHERE name='Customer X' LIMIT 1), (SELECT id FROM invoice WHERE total_amount=699.99 LIMIT 1), (SELECT id FROM shipment WHERE tracking_number='TRACK1234' LIMIT 1)),
(UUID(), '2024-08-04', 'Pending', (SELECT id FROM customer WHERE name='Customer Y' LIMIT 1), (SELECT id FROM invoice WHERE total_amount=499.99 LIMIT 1), (SELECT id FROM shipment WHERE tracking_number='TRACK5678' LIMIT 1));

-- Insert sale_order_details
INSERT INTO sale_order_detail (id, quantity, price, product_id, sale_id) VALUES
(UUID(), 1, 699.99, (SELECT id FROM product WHERE name='Smartphone' LIMIT 1), (SELECT id FROM sale_order WHERE order_date='2024-08-03' LIMIT 1)),
(UUID(), 2, 499.99, (SELECT id FROM product WHERE name='Washing Machine' LIMIT 1), (SELECT id FROM sale_order WHERE order_date='2024-08-04' LIMIT 1));

-- Insert warehouses
INSERT INTO warehouse (id, location, capacity, active) VALUES
(UUID(), 'Warehouse A', 1000, TRUE),
(UUID(), 'Warehouse B', 2000, TRUE);

-- Insert inventory
INSERT INTO inventory (id, quantity, warehouse_id, product_id) VALUES
(UUID(), 100, (SELECT id FROM warehouse WHERE location='Warehouse A' LIMIT 1), (SELECT id FROM product WHERE name='Smartphone' LIMIT 1)),
(UUID(), 50, (SELECT id FROM warehouse WHERE location='Warehouse B' LIMIT 1), (SELECT id FROM product WHERE name='Washing Machine' LIMIT 1));

-- Insert pricing
INSERT INTO pricing (price, effective_date, product_id, supplier_id) VALUES
(699.99, '2024-08-01', (SELECT id FROM product WHERE name='Smartphone' LIMIT 1), (SELECT id FROM supplier WHERE name='Supplier A' LIMIT 1)),
(499.99, '2024-08-02', (SELECT id FROM product WHERE name='Washing Machine' LIMIT 1), (SELECT id FROM supplier WHERE name='Supplier B' LIMIT 1));

-- INSERT INTO user (id, username, password, email, role, active)
-- VALUES
-- (UUID(), 'sup1', '123456', 'supplier1@gmail.com', 'Supplier', TRUE),
-- (UUID(), 'sup2', '123456', 'supplier2@gmail.com', 'Supplier', TRUE),
-- (UUID(), 'cus1', '123456', 'customer1@gmail.com', 'Customer', TRUE),
-- (UUID(), 'cus2', '123456', 'customer2@gmail.com', 'Customer', TRUE),
-- (UUID(), 'ship1', '123456', 'shipment1@gmail.com', 'ShipmentCompany', TRUE),
-- (UUID(), 'ship2', '123456', 'shipment2@gmail.com', 'ShipmentCompany', TRUE),
-- (UUID(), 'sup3', '123456', 'supplier3@gmail.com', 'Supplier', FALSE),
-- (UUID(), 'cus3', '123456', 'customer3@gmail.com', 'Customer', TRUE),
-- (UUID(), 'ship3', '123456', 'shipment3@gmail.com', 'ShipmentCompany', TRUE),
-- (UUID(), 'admin', '123456', '2151050223lan@ou.edu.vn', 'Admin', TRUE);

-- INSERT INTO supplier (id, name, address, phone, contact_info, payment_terms, rating, active, user_id)
-- VALUES
-- (UUID(), 'Công ty TNHH Thực Phẩm Việt', '123 Đường Lê Lợi, Quận 1, TP. Hồ Chí Minh', '0901234567', 'Ngô Thùy Dung, ngothuydung@vietfood.vn', 'Trả sau 30 ngày', 4.5, TRUE, (SELECT id FROM user WHERE username = 'sup1')),
-- (UUID(), 'Công ty Cổ Phần Dược Phẩm Hòa Bình', '456 Đường Trần Hưng Đạo, Quận 5, TP. Hồ Chí Minh', '0912345678', 'Diệp Anh Việt, viet@hoabinhpharma.vn', 'Trả sau 60 ngày', 4.3, TRUE, (SELECT id FROM user WHERE username = 'sup2')),
-- (UUID(), 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt', '789 Đường Nguyễn Văn Cừ, Quận 10, TP. Hồ Chí Minh', '0923456789', 'Đinh Quốc Tuấn, tuan@aosomiviet.vn', 'Trả trước 50%', 4.7, TRUE, (SELECT id FROM user WHERE username = 'sup3'));

-- INSERT INTO supplier_rating (rating_id, review_date, criteria, rating, comments, supplier_id)
-- VALUES
-- (UUID(), '2024-06-01', 'Chất lượng sản phẩm', 4.5, 'Sản phẩm đạt chất lượng tốt', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt')),
-- (UUID(), '2024-06-02', 'Dịch vụ khách hàng', 4.2, 'Dịch vụ khách hàng nhanh chóng và thân thiện', (SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình')),
-- (UUID(), '2024-06-03', 'Thời gian giao hàng', 4.7, 'Giao hàng đúng hẹn', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt'));

-- INSERT INTO category (id, name, description)
-- VALUES
-- (UUID(), 'Thực phẩm', 'Các sản phẩm thực phẩm bao gồm đồ ăn, đồ uống, nguyên liệu nấu ăn.'),
-- (UUID(), 'Dược phẩm', 'Các sản phẩm dược phẩm bao gồm thuốc, thực phẩm chức năng, thiết bị y tế.'),
-- (UUID(), 'Thời trang', 'Các sản phẩm thời trang bao gồm quần áo, giày dép, phụ kiện.'),
-- (UUID(), 'Điện tử', 'Các sản phẩm điện tử bao gồm điện thoại, máy tính, thiết bị gia dụng.'),
-- (UUID(), 'Gia dụng', 'Các sản phẩm gia dụng bao gồm đồ nội thất, đồ gia đình, dụng cụ nấu ăn.'),
-- (UUID(), 'Văn phòng phẩm', 'Các sản phẩm văn phòng phẩm bao gồm bút, giấy, dụng cụ văn phòng.'),
-- (UUID(), 'Đồ chơi', 'Các sản phẩm đồ chơi bao gồm đồ chơi trẻ em, trò chơi giáo dục.'),
-- (UUID(), 'Mỹ phẩm', 'Các sản phẩm mỹ phẩm bao gồm trang điểm, chăm sóc da, chăm sóc tóc.'),
-- (UUID(), 'Thể thao', 'Các sản phẩm thể thao bao gồm dụng cụ thể thao, quần áo thể thao, giày thể thao.'),
-- (UUID(), 'Ô tô và xe máy', 'Các sản phẩm liên quan đến ô tô, xe máy, phụ kiện và bảo dưỡng.');

-- INSERT INTO product (id, name, description, price, quantity, active, category_id)
-- VALUES
-- (UUID(), 'Gạo Jasmine', 'Gạo Jasmine nhập khẩu từ Thái Lan, chất lượng cao.', 50.00, 1000, TRUE, (SELECT id FROM category WHERE name = 'Thực phẩm')),
-- (UUID(), 'Vitamin C 1000mg', 'Thực phẩm chức năng Vitamin C 1000mg, hỗ trợ hệ miễn dịch.', 120.00, 500, TRUE, (SELECT id FROM category WHERE name = 'Dược phẩm')),
-- (UUID(), 'Áo Sơ Mi Nam', 'Áo sơ mi nam chất liệu cotton, màu trắng, kích cỡ L.', 300.00, 200, TRUE, (SELECT id FROM category WHERE name = 'Thời trang')),
-- (UUID(), 'Điện thoại Samsung Galaxy S21', 'Điện thoại thông minh Samsung Galaxy S21, bộ nhớ 128GB.', 15000.00, 50, TRUE, (SELECT id FROM category WHERE name = 'Điện tử')),
-- (UUID(), 'Bàn ăn gỗ', 'Bàn ăn gỗ tự nhiên, 6 ghế, màu nâu.', 2500.00, 30, TRUE, (SELECT id FROM category WHERE name = 'Gia dụng')),
-- (UUID(), 'Bút bi Parker', 'Bút bi Parker, mực đen, thiết kế sang trọng.', 150.00, 1000, TRUE, (SELECT id FROM category WHERE name = 'Văn phòng phẩm')),
-- (UUID(), 'Xe ô tô mô hình', 'Mô hình xe ô tô đồ chơi cho trẻ em, tỉ lệ 1:18.', 400.00, 300, TRUE, (SELECT id FROM category WHERE name = 'Đồ chơi')),
-- (UUID(), 'Kem dưỡng da ban đêm', 'Kem dưỡng da ban đêm chống lão hóa, 50ml.', 350.00, 150, TRUE, (SELECT id FROM category WHERE name = 'Mỹ phẩm')),
-- (UUID(), 'Giày thể thao Nike', 'Giày thể thao Nike Air Max, kích cỡ 42.', 1800.00, 80, TRUE, (SELECT id FROM category WHERE name = 'Thể thao')),
-- (UUID(), 'Dầu nhớt xe máy', 'Dầu nhớt cho xe máy, loại 1 lít.', 200.00, 500, TRUE, (SELECT id FROM category WHERE name = 'Ô tô và xe máy'));

-- INSERT INTO supplier_product (supplier_id, product_id)
-- VALUES
-- ((SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt'), (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
-- ((SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình'), (SELECT id FROM product WHERE name = 'Vitamin C 1000mg')),
-- ((SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt'), (SELECT id FROM product WHERE name = 'Áo Sơ Mi Nam')),
-- ((SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình'), (SELECT id FROM product WHERE name = 'Kem dưỡng da ban đêm'));

-- INSERT INTO tax_rate (id, region, rate)
-- VALUES
-- (UUID(), 'TP. Hồ Chí Minh', 10.00),
-- (UUID(), 'Hà Nội', 10.00),
-- (UUID(), 'Đà Nẵng', 10.00),
-- (UUID(), 'Cần Thơ', 10.00),
-- (UUID(), 'Hải Phòng', 10.00);

-- INSERT INTO invoice (id, invoice_date, total_amount, paid, tax_id)
-- VALUES
-- (UUID(), '2024-07-01', 1650000.00, FALSE, (SELECT id FROM tax_rate WHERE region = 'TP. Hồ Chí Minh')),
-- (UUID(), '2024-07-02', 2750000.00, TRUE, (SELECT id FROM tax_rate WHERE region = 'Hà Nội')),
-- (UUID(), '2024-07-03', 3850000.00, FALSE, (SELECT id FROM tax_rate WHERE region = 'Đà Nẵng')),
-- (UUID(), '2024-07-04', 4950000.00, TRUE, (SELECT id FROM tax_rate WHERE region = 'Cần Thơ')),
-- (UUID(), '2024-07-05', 6050000.00, FALSE, (SELECT id FROM tax_rate WHERE region = 'Hải Phòng'), (SELECT id FROM sale_order WHERE order_date = '2024-06-05 14:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-05 14:00:00'));


-- INSERT INTO customer (id, name, address, phone, contact_info, active, user_id)
-- VALUES
-- (UUID(), 'Lê Quốc Danh', '123 Đường Lê Lợi, Quận 1, TP. Hồ Chí Minh', '0901234567', 'danh@gmail.com', TRUE, (SELECT id FROM user WHERE username = 'cus1')),
-- (UUID(), 'Nguyễn Anh Phúc', '456 Đường Trần Hưng Đạo, Quận 5, TP. Hồ Chí Minh', '0912345678', 'phuc@gmail.com', TRUE, (SELECT id FROM user WHERE username = 'cus2')),
-- (UUID(), 'Bùi Lê Khang', '789 Đường Nguyễn Văn Cừ, Quận 10, TP. Hồ Chí Minh', '0923456789', 'khang@gmail.com', TRUE, (SELECT id FROM user WHERE username = 'cus3'));

-- INSERT INTO purchase_order (id, order_date, status, supplier_id, invoice_id)
-- VALUES
-- (UUID(), '2024-06-01 10:00:00', 'Pending', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt')),
-- (UUID(), '2024-06-02 11:00:00', 'Completed', (SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình')),
-- (UUID(), '2024-06-03 12:00:00', 'Cancelled', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt'));

-- INSERT INTO purchase_order_detail (purchase_id, quantity, price, product_id)
-- VALUES
-- ((SELECT id FROM purchase_order WHERE order_date = '2024-06-01 10:00:00'), 1000, 15000.00, (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
-- ((SELECT id FROM purchase_order WHERE order_date = '2024-06-02 11:00:00'), 5000, 2000.00, (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
-- ((SELECT id FROM purchase_order WHERE order_date = '2024-06-03 12:00:00'), 300, 300000.00, (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
-- ((SELECT id FROM purchase_order WHERE order_date = '2024-06-04 13:00:00'), 200, 500000.00, (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
-- ((SELECT id FROM purchase_order WHERE order_date = '2024-06-05 14:00:00'), 150, 700000.00, (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

-- INSERT INTO sale_order (id, order_date, status, total_amount, tax_id, customer_id)
-- VALUES
-- (UUID(), '2024-06-01 10:00:00', 'Pending', 1500000.00, (SELECT id FROM tax_rate WHERE region = 'TP. Hồ Chí Minh'), (SELECT id FROM customer WHERE name = 'Lê Quốc Danh')),
-- (UUID(), '2024-06-02 11:00:00', 'Completed', 2500000.00, (SELECT id FROM tax_rate WHERE region = 'Hà Nội'), (SELECT id FROM customer WHERE name = 'Nguyễn Anh Phúc')),
-- (UUID(), '2024-06-03 12:00:00', 'Cancelled', 3500000.00, (SELECT id FROM tax_rate WHERE region = 'Đà Nẵng'), (SELECT id FROM customer WHERE name = 'Bùi Lê Khang')),
-- (UUID(), '2024-06-04 13:00:00', 'Pending', 4500000.00, (SELECT id FROM tax_rate WHERE region = 'Cần Thơ'), (SELECT id FROM customer WHERE name = 'Nguyễn Thị Xuân Lan')),
-- (UUID(), '2024-06-05 14:00:00', 'Completed', 5500000.00, (SELECT id FROM tax_rate WHERE region = 'Hải Phòng'), (SELECT id FROM customer WHERE name = 'Nguyễn Thị Bích Liễu'));

-- INSERT INTO sale_order_detail (sale_id, quantity, price, product_id)
-- VALUES
-- ((SELECT id FROM sale_order WHERE order_date = '2024-06-01 10:00:00'), 1000, 15000.00, (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
-- ((SELECT id FROM sale_order WHERE order_date = '2024-06-02 11:00:00'), 5000, 2000.00, (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
-- ((SELECT id FROM sale_order WHERE order_date = '2024-06-03 12:00:00'), 300, 300000.00, (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
-- ((SELECT id FROM sale_order WHERE order_date = '2024-06-04 13:00:00'), 200, 500000.00, (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
-- ((SELECT id FROM sale_order WHERE order_date = '2024-06-05 14:00:00'), 150, 700000.00, (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

-- INSERT INTO warehouse (id, location, capacity, active)
-- VALUES
-- (UUID(), 'Quận 1, TP. Hồ Chí Minh', 10000, TRUE),
-- (UUID(), 'Quận 5, TP. Hồ Chí Minh', 8000, TRUE),
-- (UUID(), 'Quận 10, TP. Hồ Chí Minh', 6000, TRUE),
-- (UUID(), 'Quận 3, TP. Hồ Chí Minh', 4000, TRUE),
-- (UUID(), 'Quận 2, TP. Hồ Chí Minh', 2000, TRUE);

-- INSERT INTO inventory (id, quantity, warehouse_id, product_id)
-- VALUES
-- (UUID(), 1000, (SELECT id FROM warehouse WHERE location = 'Quận 1, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
-- (UUID(), 5000, (SELECT id FROM warehouse WHERE location = 'Quận 5, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
-- (UUID(), 300, (SELECT id FROM warehouse WHERE location = 'Quận 10, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
-- (UUID(), 200, (SELECT id FROM warehouse WHERE location = 'Quận 3, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
-- (UUID(), 150, (SELECT id FROM warehouse WHERE location = 'Quận 2, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

-- INSERT INTO shipment_company (id, company_name, contact_info, active, rating)
-- VALUES
-- (UUID(), 'Giao Hàng Nhanh', '0123456789, contact@ghn.vn', TRUE, 4.7),
-- (UUID(), 'Viettel Post', '0987654321, support@viettelpost.vn', TRUE, 4.5),
-- (UUID(), 'J&T Express', '0234567890, care@jtexpress.vn', TRUE, 4.6),
-- (UUID(), 'VNPost', '0345678901, info@vnpost.vn', TRUE, 4.4),
-- (UUID(), 'DHL', '0456789012, service@dhl.vn', TRUE, 4.8);

-- INSERT INTO shipment (id, shipment_date, status, tracking_number, expected_delivery, company_id, sale_id, purchase_id)
-- VALUES
-- (UUID(), '2024-07-01', 'Shipped', 'GHN1234567890', '2024-07-05', (SELECT id FROM shipment_company WHERE company_name = 'Giao Hàng Nhanh'), (SELECT id FROM sale_order WHERE order_date = '2024-06-01 10:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-01 10:00:00')),
-- (UUID(), '2024-07-02', 'In Transit', 'VTP1234567890', '2024-07-06', (SELECT id FROM shipment_company WHERE company_name = 'Viettel Post'), (SELECT id FROM sale_order WHERE order_date = '2024-06-02 11:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-02 11:00:00')),
-- (UUID(), '2024-07-03', 'Delivered', 'J&T1234567890', '2024-07-07', (SELECT id FROM shipment_company WHERE company_name = 'J&T Express'), (SELECT id FROM sale_order WHERE order_date = '2024-06-03 12:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-03 12:00:00')),
-- (UUID(), '2024-07-04', 'Returned', 'VNPost1234567890', '2024-07-08', (SELECT id FROM shipment_company WHERE company_name = 'VNPost'), (SELECT id FROM sale_order WHERE order_date = '2024-06-04 13:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-04 13:00:00')),
-- (UUID(), '2024-07-05', 'Shipped', 'DHL1234567890', '2024-07-09', (SELECT id FROM shipment_company WHERE company_name = 'DHL'), (SELECT id FROM sale_order WHERE order_date = '2024-06-05 14:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-05 14:00:00'));

-- INSERT INTO pricing (price, effective_date, product_id, supplier_id)
-- VALUES
-- (15000.00, '2024-06-01 10:00:00', (SELECT id FROM product WHERE name = 'Gạo Jasmine'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt')),
-- (2000.00, '2024-06-02 11:00:00', (SELECT id FROM product WHERE name = 'Thuốc Paracetamol'), (SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình')),
-- (300000.00, '2024-06-03 12:00:00', (SELECT id FROM product WHERE name = 'Áo sơ mi nam'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt')),
-- (500000.00, '2024-06-04 13:00:00', (SELECT id FROM product WHERE name = 'Nồi cơm điện'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Điện Tử Minh Khang')),
-- (700000.00, '2024-06-05 14:00:00', (SELECT id FROM product WHERE name = 'Máy đo huyết áp'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Dược Phẩm Nam An'));
