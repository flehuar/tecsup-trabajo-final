INSERT INTO deliveries (id, order_id, status) VALUES
  (1, 1, 'PENDING_ASSIGNMENT'),
  (2, 2, 'PENDING_ASSIGNMENT');

SELECT setval(pg_get_serial_sequence('deliveries', 'id'), COALESCE((SELECT MAX(id) FROM deliveries), 1), true);
