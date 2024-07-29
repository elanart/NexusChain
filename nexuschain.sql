use nexuschain;

CREATE TABLE supplier(
	id VARCHAR(50) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    address NVARCHAR(255),
    phone VARCHAR(50),
    contact_info NVARCHAR(100),
    payment_terms NVARCHAR(100),
    rating DECIMAL(3, 2),
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE supplier_rating (
	rating_id VARCHAR(50) PRIMARY KEY,
    review_date DATE,
	criteria NVARCHAR(100),
	rating DECIMAL(3, 2),
	comments TEXT,
     
    supplier_id VARCHAR(50),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
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

CREATE TABLE customer(
     id VARCHAR(50) PRIMARY KEY,
     name NVARCHAR(255) NOT NULL,
     address NVARCHAR(255),
     phone VARCHAR(50),
     contact_info NVARCHAR(100),
     active BOOLEAN DEFAULT TRUE
);

CREATE TABLE purchase_order(
	id VARCHAR(50) PRIMARY KEY,
	order_date DATETIME,
    status ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
    total_amount DECIMAL(10, 2),

    tax_id VARCHAR(50),
    supplier_id VARCHAR(50),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (tax_id) REFERENCES tax_rate(id)
);

CREATE TABLE purchase_order_detail(
    purchase_id VARCHAR(50) PRIMARY KEY,
    quantity INT,
    price DECIMAL(10,2),

    product_id VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (purchase_id) REFERENCES purchase_order(id)
);

CREATE TABLE sale_order (
     id VARCHAR(50) PRIMARY KEY,
     order_date DATETIME,
     status ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
     total_amount DECIMAL(10, 2),

     tax_id VARCHAR(50),
     customer_id VARCHAR(50),
     FOREIGN KEY (customer_id) REFERENCES customer(id),
     FOREIGN KEY (tax_id) REFERENCES tax_rate(id)
);

CREATE TABLE sale_order_detail(
     sale_id VARCHAR(50) PRIMARY KEY,
     quantity INT,
     price DECIMAL(10, 2),
     
     product_id VARCHAR(50),
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
     quantity INT,

     warehouse_id VARCHAR(50),
     product_id VARCHAR(50),
     FOREIGN KEY (warehouse_id) REFERENCES warehouse(id),
     FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE shipment_company(
     id VARCHAR(50) PRIMARY KEY,
     company_name NVARCHAR(255) NOT NULL,
     contact_info NVARCHAR(255),
     active BOOLEAN DEFAULT TRUE,
     rating DECIMAL(5, 2) NOT NULL
);

CREATE TABLE shipment(
     id VARCHAR(50) PRIMARY KEY,
     shipment_date DATE NOT NULL,
     status ENUM('Shipped', 'In Transit', 'Delivered', 'Returned') NOT NULL,
     tracking_number VARCHAR(100),
     expected_delivery DATE,

     company_id VARCHAR(50),
     sale_id VARCHAR(50),
     purchase_id VARCHAR(50),
     FOREIGN KEY (company_id) REFERENCES shipment_company(id),
     FOREIGN KEY (sale_id) REFERENCES sale_order(id),
     FOREIGN KEY (purchase_id) REFERENCES purchase_order(id)
);

CREATE TABLE pricing (
     price DECIMAL(10, 2),
     effective_date DATETIME,

     product_id VARCHAR(50),
     supplier_id VARCHAR(50),
     PRIMARY KEY(product_id, supplier_id),
     FOREIGN KEY (product_id) REFERENCES product(id),
     FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

CREATE TABLE invoice(
     id VARCHAR(50) PRIMARY KEY,
     invoice_date DATE,
     total_amount DECIMAL(10, 2),
     paid BOOLEAN DEFAULT FALSE,

     tax_id VARCHAR(50),
     sale_id VARCHAR(50),
     purchase_id VARCHAR(50),
     FOREIGN KEY (tax_id) REFERENCES tax_rate(id),
     FOREIGN KEY (sale_id) REFERENCES sale_order(id),
     FOREIGN KEY (purchase_id) REFERENCES purchase_order(id)
);

INSERT INTO supplier (id, name, address, phone, contact_info, payment_terms, rating, active)
VALUES
(UUID(), 'Công ty TNHH Thực Phẩm Việt', '123 Đường Lê Lợi, Quận 1, TP. Hồ Chí Minh', '0901234567', 'Ngô Thùy Dung, ngothuydung@vietfood.vn', 'Trả sau 30 ngày', 4.5, TRUE),
(UUID(), 'Công ty Cổ Phần Dược Phẩm Hòa Bình', '456 Đường Trần Hưng Đạo, Quận 5, TP. Hồ Chí Minh', '0912345678', 'Diệp Anh Việt, viet@hoabinhpharma.vn', 'Trả sau 60 ngày', 4.3, TRUE),
(UUID(), 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt', '789 Đường Nguyễn Văn Cừ, Quận 10, TP. Hồ Chí Minh', '0923456789', 'Đinh Quốc Tuấn, tuan@aosomiviet.vn', 'Trả trước 50%', 4.7, TRUE),
(UUID(), 'Công ty TNHH Điện Tử Minh Khang', '321 Đường Lý Thái Tổ, Quận 3, TP. Hồ Chí Minh', '0934567890', 'Đỗ Đức Hậu, hau@dientuminhkhang.vn', 'Trả sau 45 ngày', 4.6, TRUE),
(UUID(), 'Công ty TNHH Dược Phẩm Nam An', '654 Đường Hai Bà Trưng, Quận 1, TP. Hồ Chí Minh', '0945678901', 'Đỗ Thị Quý, quy@duocphamnaman.vn', 'Trả sau 30 ngày', 4.8, TRUE);

INSERT INTO supplier_rating (rating_id, review_date, criteria, rating, comments, supplier_id)
VALUES
(UUID(), '2024-06-01', 'Chất lượng sản phẩm', 4.5, 'Sản phẩm đạt chất lượng tốt', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt')),
(UUID(), '2024-06-02', 'Dịch vụ khách hàng', 4.2, 'Dịch vụ khách hàng nhanh chóng và thân thiện', (SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình')),
(UUID(), '2024-06-03', 'Thời gian giao hàng', 4.7, 'Giao hàng đúng hẹn', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt')),
(UUID(), '2024-06-04', 'Giá cả', 4.6, 'Giá cả hợp lý', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Điện Tử Minh Khang')),
(UUID(), '2024-06-05', 'Hậu mãi', 4.8, 'Dịch vụ hậu mãi tốt', (SELECT id FROM supplier WHERE name = 'Công ty TNHH Dược Phẩm Nam An'));

INSERT INTO category (id, name, description)
VALUES
(UUID(), 'Thực phẩm', 'Các sản phẩm thực phẩm'),
(UUID(), 'Dược phẩm', 'Các sản phẩm dược phẩm'),
(UUID(), 'Áo sơ mi', 'Các loại áo sơ mi'),
(UUID(), 'Điện tử', 'Các sản phẩm điện tử'),
(UUID(), 'Dụng cụ y tế', 'Các dụng cụ y tế');

INSERT INTO product (id, name, description, price, quantity, active, category_id)
VALUES
(UUID(), 'Gạo Jasmine', 'Gạo Jasmine chất lượng cao', 15000.00, 1000, TRUE, (SELECT id FROM category WHERE name = 'Thực phẩm')),
(UUID(), 'Thuốc Paracetamol', 'Thuốc giảm đau và hạ sốt Paracetamol', 2000.00, 5000, TRUE, (SELECT id FROM category WHERE name = 'Dược phẩm')),
(UUID(), 'Áo sơ mi nam', 'Áo sơ mi nam trắng', 300000.00, 300, TRUE, (SELECT id FROM category WHERE name = 'Áo sơ mi')),
(UUID(), 'Nồi cơm điện', 'Nồi cơm điện đa năng', 500000.00, 200, TRUE, (SELECT id FROM category WHERE name = 'Điện tử')),
(UUID(), 'Máy đo huyết áp', 'Máy đo huyết áp điện tử', 700000.00, 150, TRUE, (SELECT id FROM category WHERE name = 'Dụng cụ y tế'));

INSERT INTO supplier_product (supplier_id, product_id)
VALUES
((SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt'), (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
((SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình'), (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
((SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt'), (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
((SELECT id FROM supplier WHERE name = 'Công ty TNHH Điện Tử Minh Khang'), (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
((SELECT id FROM supplier WHERE name = 'Công ty TNHH Dược Phẩm Nam An'), (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

INSERT INTO tax_rate (id, region, rate)
VALUES
(UUID(), 'TP. Hồ Chí Minh', 10.00),
(UUID(), 'Hà Nội', 10.00),
(UUID(), 'Đà Nẵng', 10.00),
(UUID(), 'Cần Thơ', 10.00),
(UUID(), 'Hải Phòng', 10.00);

INSERT INTO customer (id, name, address, phone, contact_info, active)
VALUES
(UUID(), 'Lê Quốc Danh', '123 Đường Lê Lợi, Quận 1, TP. Hồ Chí Minh', '0901234567', 'danh@gmail.com', TRUE),
(UUID(), 'Nguyễn Anh Phúc', '456 Đường Trần Hưng Đạo, Quận 5, TP. Hồ Chí Minh', '0912345678', 'phuc@gmail.com', TRUE),
(UUID(), 'Bùi Lê Khang', '789 Đường Nguyễn Văn Cừ, Quận 10, TP. Hồ Chí Minh', '0923456789', 'khang@gmail.com', TRUE),
(UUID(), 'Nguyễn Thị Xuân Lan', '321 Đường Lý Thái Tổ, Quận 3, TP. Hồ Chí Minh', '0934567890', 'lan@gmail.com', TRUE),
(UUID(), 'Nguyễn Thị Bích Liễu', '654 Đường Hai Bà Trưng, Quận 1, TP. Hồ Chí Minh', '0945678901', 'lieu@gmail.com', TRUE);

INSERT INTO purchase_order (id, order_date, status, total_amount, tax_id, supplier_id)
VALUES
(UUID(), '2024-06-01 10:00:00', 'Pending', 1500000.00, (SELECT id FROM tax_rate WHERE region = 'TP. Hồ Chí Minh'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt')),
(UUID(), '2024-06-02 11:00:00', 'Completed', 2500000.00, (SELECT id FROM tax_rate WHERE region = 'Hà Nội'), (SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình')),
(UUID(), '2024-06-03 12:00:00', 'Cancelled', 3500000.00, (SELECT id FROM tax_rate WHERE region = 'Đà Nẵng'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt')),
(UUID(), '2024-06-04 13:00:00', 'Pending', 4500000.00, (SELECT id FROM tax_rate WHERE region = 'Cần Thơ'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Điện Tử Minh Khang')),
(UUID(), '2024-06-05 14:00:00', 'Completed', 5500000.00, (SELECT id FROM tax_rate WHERE region = 'Hải Phòng'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Dược Phẩm Nam An'));

INSERT INTO purchase_order_detail (purchase_id, quantity, price, product_id)
VALUES
((SELECT id FROM purchase_order WHERE order_date = '2024-06-01 10:00:00'), 1000, 15000.00, (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
((SELECT id FROM purchase_order WHERE order_date = '2024-06-02 11:00:00'), 5000, 2000.00, (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
((SELECT id FROM purchase_order WHERE order_date = '2024-06-03 12:00:00'), 300, 300000.00, (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
((SELECT id FROM purchase_order WHERE order_date = '2024-06-04 13:00:00'), 200, 500000.00, (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
((SELECT id FROM purchase_order WHERE order_date = '2024-06-05 14:00:00'), 150, 700000.00, (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

INSERT INTO sale_order (id, order_date, status, total_amount, tax_id, customer_id)
VALUES
(UUID(), '2024-06-01 10:00:00', 'Pending', 1500000.00, (SELECT id FROM tax_rate WHERE region = 'TP. Hồ Chí Minh'), (SELECT id FROM customer WHERE name = 'Lê Quốc Danh')),
(UUID(), '2024-06-02 11:00:00', 'Completed', 2500000.00, (SELECT id FROM tax_rate WHERE region = 'Hà Nội'), (SELECT id FROM customer WHERE name = 'Nguyễn Anh Phúc')),
(UUID(), '2024-06-03 12:00:00', 'Cancelled', 3500000.00, (SELECT id FROM tax_rate WHERE region = 'Đà Nẵng'), (SELECT id FROM customer WHERE name = 'Bùi Lê Khang')),
(UUID(), '2024-06-04 13:00:00', 'Pending', 4500000.00, (SELECT id FROM tax_rate WHERE region = 'Cần Thơ'), (SELECT id FROM customer WHERE name = 'Nguyễn Thị Xuân Lan')),
(UUID(), '2024-06-05 14:00:00', 'Completed', 5500000.00, (SELECT id FROM tax_rate WHERE region = 'Hải Phòng'), (SELECT id FROM customer WHERE name = 'Nguyễn Thị Bích Liễu'));

INSERT INTO sale_order_detail (sale_id, quantity, price, product_id)
VALUES
((SELECT id FROM sale_order WHERE order_date = '2024-06-01 10:00:00'), 1000, 15000.00, (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
((SELECT id FROM sale_order WHERE order_date = '2024-06-02 11:00:00'), 5000, 2000.00, (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
((SELECT id FROM sale_order WHERE order_date = '2024-06-03 12:00:00'), 300, 300000.00, (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
((SELECT id FROM sale_order WHERE order_date = '2024-06-04 13:00:00'), 200, 500000.00, (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
((SELECT id FROM sale_order WHERE order_date = '2024-06-05 14:00:00'), 150, 700000.00, (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

INSERT INTO warehouse (id, location, capacity, active)
VALUES
(UUID(), 'Quận 1, TP. Hồ Chí Minh', 10000, TRUE),
(UUID(), 'Quận 5, TP. Hồ Chí Minh', 8000, TRUE),
(UUID(), 'Quận 10, TP. Hồ Chí Minh', 6000, TRUE),
(UUID(), 'Quận 3, TP. Hồ Chí Minh', 4000, TRUE),
(UUID(), 'Quận 2, TP. Hồ Chí Minh', 2000, TRUE);

INSERT INTO inventory (id, quantity, warehouse_id, product_id)
VALUES
(UUID(), 1000, (SELECT id FROM warehouse WHERE location = 'Quận 1, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Gạo Jasmine')),
(UUID(), 5000, (SELECT id FROM warehouse WHERE location = 'Quận 5, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Thuốc Paracetamol')),
(UUID(), 300, (SELECT id FROM warehouse WHERE location = 'Quận 10, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Áo sơ mi nam')),
(UUID(), 200, (SELECT id FROM warehouse WHERE location = 'Quận 3, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Nồi cơm điện')),
(UUID(), 150, (SELECT id FROM warehouse WHERE location = 'Quận 2, TP. Hồ Chí Minh'), (SELECT id FROM product WHERE name = 'Máy đo huyết áp'));

INSERT INTO shipment_company (id, company_name, contact_info, active, rating)
VALUES
(UUID(), 'Giao Hàng Nhanh', '0123456789, contact@ghn.vn', TRUE, 4.7),
(UUID(), 'Viettel Post', '0987654321, support@viettelpost.vn', TRUE, 4.5),
(UUID(), 'J&T Express', '0234567890, care@jtexpress.vn', TRUE, 4.6),
(UUID(), 'VNPost', '0345678901, info@vnpost.vn', TRUE, 4.4),
(UUID(), 'DHL', '0456789012, service@dhl.vn', TRUE, 4.8);

INSERT INTO shipment (id, shipment_date, status, tracking_number, expected_delivery, company_id, sale_id, purchase_id)
VALUES
(UUID(), '2024-07-01', 'Shipped', 'GHN1234567890', '2024-07-05', (SELECT id FROM shipment_company WHERE company_name = 'Giao Hàng Nhanh'), (SELECT id FROM sale_order WHERE order_date = '2024-06-01 10:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-01 10:00:00')),
(UUID(), '2024-07-02', 'In Transit', 'VTP1234567890', '2024-07-06', (SELECT id FROM shipment_company WHERE company_name = 'Viettel Post'), (SELECT id FROM sale_order WHERE order_date = '2024-06-02 11:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-02 11:00:00')),
(UUID(), '2024-07-03', 'Delivered', 'J&T1234567890', '2024-07-07', (SELECT id FROM shipment_company WHERE company_name = 'J&T Express'), (SELECT id FROM sale_order WHERE order_date = '2024-06-03 12:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-03 12:00:00')),
(UUID(), '2024-07-04', 'Returned', 'VNPost1234567890', '2024-07-08', (SELECT id FROM shipment_company WHERE company_name = 'VNPost'), (SELECT id FROM sale_order WHERE order_date = '2024-06-04 13:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-04 13:00:00')),
(UUID(), '2024-07-05', 'Shipped', 'DHL1234567890', '2024-07-09', (SELECT id FROM shipment_company WHERE company_name = 'DHL'), (SELECT id FROM sale_order WHERE order_date = '2024-06-05 14:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-05 14:00:00'));

INSERT INTO pricing (price, effective_date, product_id, supplier_id)
VALUES
(15000.00, '2024-06-01 10:00:00', (SELECT id FROM product WHERE name = 'Gạo Jasmine'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Thực Phẩm Việt')),
(2000.00, '2024-06-02 11:00:00', (SELECT id FROM product WHERE name = 'Thuốc Paracetamol'), (SELECT id FROM supplier WHERE name = 'Công ty Cổ Phần Dược Phẩm Hòa Bình')),
(300000.00, '2024-06-03 12:00:00', (SELECT id FROM product WHERE name = 'Áo sơ mi nam'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Sản Xuất Áo Sơ Mi Việt')),
(500000.00, '2024-06-04 13:00:00', (SELECT id FROM product WHERE name = 'Nồi cơm điện'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Điện Tử Minh Khang')),
(700000.00, '2024-06-05 14:00:00', (SELECT id FROM product WHERE name = 'Máy đo huyết áp'), (SELECT id FROM supplier WHERE name = 'Công ty TNHH Dược Phẩm Nam An'));

INSERT INTO invoice (id, invoice_date, total_amount, paid, tax_id, sale_id, purchase_id)
VALUES
(UUID(), '2024-07-01', 1650000.00, FALSE, (SELECT id FROM tax_rate WHERE region = 'TP. Hồ Chí Minh'), (SELECT id FROM sale_order WHERE order_date = '2024-06-01 10:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-01 10:00:00')),
(UUID(), '2024-07-02', 2750000.00, TRUE, (SELECT id FROM tax_rate WHERE region = 'Hà Nội'), (SELECT id FROM sale_order WHERE order_date = '2024-06-02 11:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-02 11:00:00')),
(UUID(), '2024-07-03', 3850000.00, FALSE, (SELECT id FROM tax_rate WHERE region = 'Đà Nẵng'), (SELECT id FROM sale_order WHERE order_date = '2024-06-03 12:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-03 12:00:00')),
(UUID(), '2024-07-04', 4950000.00, TRUE, (SELECT id FROM tax_rate WHERE region = 'Cần Thơ'), (SELECT id FROM sale_order WHERE order_date = '2024-06-04 13:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-04 13:00:00')),
(UUID(), '2024-07-05', 6050000.00, FALSE, (SELECT id FROM tax_rate WHERE region = 'Hải Phòng'), (SELECT id FROM sale_order WHERE order_date = '2024-06-05 14:00:00'), (SELECT id FROM purchase_order WHERE order_date = '2024-06-05 14:00:00'));
