CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE client
(
    id UUID,
    USERNAME   VARCHAR(30),
    PASSWORD  VARCHAR(300),
    wallet INT4,
    PRIMARY KEY (id)
);
CREATE TABLE product
(
    id UUID,
    name VARCHAR(300),
    price INT4,
    category VARCHAR(30),
    description VARCHAR(1000),
    img VARCHAR(1000),
    rating INT4,
    PRIMARY KEY (id)
);
CREATE TABLE review
(
    id UUID,
    comment VARCHAR(1000),
    rating INT4,
    client_id UUID,
    product_id UUID,
    PRIMARY KEY (id)
);
ALTER TABLE review
  ADD CONSTRAINT fk_review_client FOREIGN KEY (client_id) REFERENCES client;

ALTER TABLE review
  ADD CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES product;