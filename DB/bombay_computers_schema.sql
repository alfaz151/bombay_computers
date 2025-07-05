DROP DATABASE IF EXISTS bombay_computers;

CREATE DATABASE IF NOT EXISTS bombay_computers;
USE bombay_computers;

-- USERS TABLE
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    address TEXT,
    role ENUM('BUYER', 'SELLER', 'ADMIN') DEFAULT 'BUYER',
    is_admin BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- CATEGORIES TABLE
CREATE TABLE categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- PRODUCTS TABLE
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    `condition` ENUM('New', 'Like New', 'Good', 'Fair', 'Poor') DEFAULT 'Good',
    price DECIMAL(10, 2),
    location VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- ORDERS TABLE
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    buyer_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Confirmed', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    FOREIGN KEY (buyer_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- REVIEWS TABLE
CREATE TABLE reviews (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    reviewed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- MESSAGES TABLE (optional chat)
CREATE TABLE messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    product_id BIGINT,
    message TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- ADMIN LOGS TABLE (admin actions tracking)
CREATE TABLE admin_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    admin_id BIGINT NOT NULL,
    action_type VARCHAR(100),
    target_table VARCHAR(50),
    target_id BIGINT,
    description TEXT,
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES users(id)
);

-- INDEXES
CREATE INDEX idx_products_category ON products(category_id);
CREATE INDEX idx_orders_buyer ON orders(buyer_id);
CREATE INDEX idx_reviews_product ON reviews(product_id);
CREATE INDEX idx_messages_product ON messages(product_id);
