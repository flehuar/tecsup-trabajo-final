INSERT INTO orders (id, user_id, total_amount, status) VALUES
  (1, 1, 18.50, 'CREATED'),
  (2, 2, 42.90, 'CREATED');

INSERT INTO order_items (order_id, product_id, quantity) VALUES
  (1, 1, 1),
  (2, 2, 1);

SELECT setval(pg_get_serial_sequence('orders', 'id'), COALESCE((SELECT MAX(id) FROM orders), 1), true);
SELECT setval(pg_get_serial_sequence('order_items', 'id'), COALESCE((SELECT MAX(id) FROM order_items), 1), true);
