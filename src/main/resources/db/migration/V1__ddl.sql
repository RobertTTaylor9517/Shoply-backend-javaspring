CREATE TABLE users
(
    id UUID,
    USER_NAME   VARCHAR(30),
    PASSWORD  VARCHAR(12),
    wallet INT4,
    PRIMARY KEY (id)
);
CREATE TABLE products
(
    id UUID,
    name VARCHAR(300),
    price INT4,
    category VARCHAR(30),
    description VARCHAR(1000),
    img VARCHAR(80),
    rating INT4,
    PRIMARY KEY (id)
);
CREATE TABLE reviews
(
    id UUID,
    comment VARCHAR(1000),
    rating INT4,
    PRIMARY KEY (id)
);
ALTER TABLE reviews
  ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users;
  
ALTER TABLE reviews
  ADD CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES products;