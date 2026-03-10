INSERT INTO orders (id, user_id, total_amount, status) VALUES
  (1, 1, 18.50, 'CREATED'),
  (2, 2, 42.90, 'CREATED');

SELECT setval(pg_get_serial_sequence('orders', 'id'), COALESCE((SELECT MAX(id) FROM orders), 1), true);
