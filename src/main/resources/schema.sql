CREATE TABLE IF NOT EXISTS menu_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(255),
    image_upload_hint VARCHAR(500),
    price_edit_hint VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS customer_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    menu_item_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    CONSTRAINT fk_order_menu_item FOREIGN KEY (menu_item_id) REFERENCES menu_item(id)
);
