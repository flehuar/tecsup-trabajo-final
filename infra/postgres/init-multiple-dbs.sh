#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE DATABASE usuarios_db;
  CREATE DATABASE catalogo_db;
  CREATE DATABASE pedidos_db;
  CREATE DATABASE pagos_db;
  CREATE DATABASE entregas_db;

  CREATE USER usuarios_user WITH PASSWORD 'usuarios_pass';
  CREATE USER catalogo_user WITH PASSWORD 'catalogo_pass';
  CREATE USER pedidos_user WITH PASSWORD 'pedidos_pass';
  CREATE USER pagos_user WITH PASSWORD 'pagos_pass';
  CREATE USER entregas_user WITH PASSWORD 'entregas_pass';

  GRANT ALL PRIVILEGES ON DATABASE usuarios_db TO usuarios_user;
  GRANT ALL PRIVILEGES ON DATABASE catalogo_db TO catalogo_user;
  GRANT ALL PRIVILEGES ON DATABASE pedidos_db TO pedidos_user;
  GRANT ALL PRIVILEGES ON DATABASE pagos_db TO pagos_user;
  GRANT ALL PRIVILEGES ON DATABASE entregas_db TO entregas_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "usuarios_db" <<-EOSQL
  GRANT USAGE, CREATE ON SCHEMA public TO usuarios_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO usuarios_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO usuarios_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "catalogo_db" <<-EOSQL
  GRANT USAGE, CREATE ON SCHEMA public TO catalogo_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO catalogo_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO catalogo_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "pedidos_db" <<-EOSQL
  GRANT USAGE, CREATE ON SCHEMA public TO pedidos_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO pedidos_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO pedidos_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "pagos_db" <<-EOSQL
  GRANT USAGE, CREATE ON SCHEMA public TO pagos_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO pagos_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO pagos_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "entregas_db" <<-EOSQL
  GRANT USAGE, CREATE ON SCHEMA public TO entregas_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO entregas_user;
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO entregas_user;
EOSQL
