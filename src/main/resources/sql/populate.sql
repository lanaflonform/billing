-- Insert data
-- crypted https://www.dailycred.com/article/bcrypt-calculator
INSERT INTO users
VALUES (1, 'admin', 'admin@gmail.com', '$2a$10$SRikmt23vmocG0fn3lDLY.vxUdred2NMl5OpU1A89k6.2q2Oec8w.');
INSERT INTO users
VALUES (2, 'user', 'user@gmail.com', '$2a$10$KiT/FGjwueFIhSTNnUvGOePa.JucZA5vvT8zRCVtLqZ0sL7sae/DK');

INSERT INTO roles VALUES (1, 'ROLE_USER'); -- ROLE_USER in class Constants
INSERT INTO roles VALUES (2, 'ROLE_ADMIN'); -- ROLE_ADMIN in class Constants

INSERT INTO user_roles VALUES (1, 2); -- user ADMIN has role ADMIN :)
INSERT INTO user_roles VALUES (2, 1); -- user USER has role USER :)
