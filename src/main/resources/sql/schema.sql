CREATE TABLE IF NOT EXISTS users (
  id       INT PRIMARY KEY,
  email    VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  id      INT PRIMARY KEY,
  debit   DECIMAL,
  credit  DECIMAL,
  user_id INT
);