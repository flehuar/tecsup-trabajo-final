INSERT INTO users (id, name, email) VALUES
  (1, 'Ana Torres', 'ana.torres@tecsup.local'),
  (2, 'Luis Ramos', 'luis.ramos@tecsup.local');

SELECT setval(pg_get_serial_sequence('users', 'id'), COALESCE((SELECT MAX(id) FROM users), 1), true);
