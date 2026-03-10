INSERT INTO payments (id, order_id, amount, status) VALUES
  (1, 1, 18.50, 'PENDING'),
  (2, 2, 42.90, 'PENDING');

SELECT setval(pg_get_serial_sequence('payments', 'id'), COALESCE((SELECT MAX(id) FROM payments), 1), true);
