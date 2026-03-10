INSERT INTO products (id, name, restaurant, price) VALUES
  (1, 'Hamburguesa Clasica', 'Food House', 18.50),
  (2, 'Pizza Familiar', 'La Italiana', 42.90),
  (3, 'Ensalada Cesar', 'Green Spot', 16.00);

SELECT setval(pg_get_serial_sequence('products', 'id'), COALESCE((SELECT MAX(id) FROM products), 1), true);
