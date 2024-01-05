
INSERT INTO users (id, username, password, first_name, last_name, email, phone_number, date_of_birth, created_at,
modified_at)
VALUES (1, 'admin', '$2a$10$dXeDpSKxG7TIekLJAjYMPuW1pilwmY9Cf6vnRfBtZC85dJ/wRtEsu', 'Nguyen Van', 'Admin',
'admin@gmail.com', '0987654321', '2002-11-21 00:00:00.000000', '2022-12-14 00:00:00.000000', '2022-12-14 00:00:00.000000');

INSERT INTO roles (id, role_name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO users_roles (role_id, user_id)
VALUES (1, 1);