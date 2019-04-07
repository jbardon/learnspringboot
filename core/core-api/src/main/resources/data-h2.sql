-- Init script to populate in-memory h2 database

-- Hibernate way: import.sql in classpath root (resources)
-- Spring way: schema.sql and data-h2.sql in classpath root (resources)
--    Can append with database to type to apply only if the current DB matches

INSERT INTO provider (id, name) VALUES
  (1, 'E.Leclerc'),
  (2, 'SuperU');


INSERT INTO product (unique_id, manufacturer, full_name, price, provider_id) VALUES
  (1, 'Mir', 'Lessive liquide Mir Raviveur de Blancheur', 4.93, 1),
  (2, 'Aquafresh', 'Dentifrice Aquafresh Night repair', 1.97, 1),
  (3, 'Plantation', 'Café en capsule Plantation Cappuccino - x8', 2.84, 1),
  (4, 'Nos régions ont du talent', 'Fromage Brillat Savarin 38%mg Nos Régions ont du Talent', 2.70, 2),
  (5, 'Nestea', 'Thé glacé Nestea Thé vert,citron, vert,menthe', 1.50, 2);

/*
With @OneToMany
INSERT INTO review (id, message, product_id) VALUES
  (1, 'Bien', 1),
  (2, 'Mauvais', 1),
  (3, 'Trop cher', 1);
*/

-- With @ElementCollection
INSERT INTO review (product_id, message) VALUES
  (1, 'Bien'),
  (1, 'Mauvais'),
  (1, 'Trop cher');




