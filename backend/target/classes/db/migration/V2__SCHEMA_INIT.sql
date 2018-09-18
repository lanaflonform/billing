-- Sequence
CREATE SEQUENCE IF NOT EXISTS billing_sequence
MINVALUE 3
MAXVALUE 9999999999
START WITH 3
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

-- -- Insert data
INSERT INTO accounts VALUES (1, 100.00, 100.00);
INSERT INTO accounts VALUES (2, 100.00, 100.00);

-- crypted https://www.dailycred.com/article/bcrypt-calculator
INSERT INTO users
VALUES (1, 'admin', 'admin@gmail.com', '$2a$10$SRikmt23vmocG0fn3lDLY.vxUdred2NMl5OpU1A89k6.2q2Oec8w.', 1);
INSERT INTO users
VALUES (2, 'user', 'user@gmail.com', '$2a$10$KiT/FGjwueFIhSTNnUvGOePa.JucZA5vvT8zRCVtLqZ0sL7sae/DK', 2);

INSERT INTO roles VALUES (1, 'ROLE_USER'); -- ROLE_USER in class Constants
INSERT INTO roles VALUES (2, 'ROLE_ADMIN'); -- ROLE_ADMIN in class Constants

INSERT INTO user_roles VALUES (1, 2); -- user ADMIN has role ADMIN :)
INSERT INTO user_roles VALUES (2, 1); -- user USER has role USER :)