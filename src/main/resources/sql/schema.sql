-- Sequence
CREATE SEQUENCE IF NOT EXISTS billing_sequence
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
NO CYCLE;

-- Table: users
CREATE TABLE IF NOT EXISTS users (
  id         INT PRIMARY KEY,
  username   VARCHAR(50)  NOT NULL,
  email      VARCHAR(50)  NOT NULL,
  password   VARCHAR(255) NOT NULL,
  account_id INT
);

-- Table: roles
CREATE TABLE IF NOT EXISTS roles (
  id   INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

-- Table: accounts
CREATE TABLE IF NOT EXISTS accounts (
  id     INT PRIMARY KEY,
  debit  DECIMAL,
  credit DECIMAL
);

-- Table for mapping user and roles: user_roles
CREATE TABLE IF NOT EXISTS user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);
