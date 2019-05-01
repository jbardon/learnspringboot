-- Init script to populate in-memory h2 database

-- Hibernate way: import.sql in classpath root (resources)
-- Spring way: schema.sql and data-h2.sql in classpath root (resources)
--    Can append with database to type to apply only if the current DB matches

INSERT INTO product (id, make, name, price) VALUES
  (1, 'Mir', 'Lessive liquide Mir Raviveur de Blancheur', 4.93),
  (2, 'Aquafresh', 'Dentifrice Aquafresh Night repair', 1.97),
  (3, 'Plantation', 'Café en capsule Plantation Cappuccino - x8', 2.84),
  (4, 'Nos régions ont du talent', 'Fromage Brillat Savarin 38%mg Nos Régions ont du Talent', 2.70),
  (5, 'Nestea', 'Thé glacé Nestea Thé vert,citron, vert,menthe', 1.50);

INSERT INTO customer (id, firstname, lastname) VALUES
  (1, 'Jérémy', 'Bardon');

INSERT INTO customer_address (customer_id, street, zipcode, city, country) VALUES
  (1, '12 rue des Roses', '44000', 'Nantes', 'FR');

INSERT INTO orders (id, status, customer_id) VALUES
  (1, 'PENDING', 1);

INSERT INTO orders_products(order_id, product_id) VALUES
  (1, 1),
  (1, 3),
  (1, 4);
