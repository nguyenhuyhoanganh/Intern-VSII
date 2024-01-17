-- Insert Table User
INSERT INTO users (id, username, authentication_code, first_name, last_name, email, phone_number, date_of_birth)
VALUES (1, 'admin', '$2a$10$Hj4cEnXFNuUuVucxMa18ceoYoK.XFh3QwJ.OPsspWFI3jnsw9cx3i', 'Nguyen Van', 'Hung','admin@gmail.com', '0987654321', '2002-11-21');
INSERT INTO users (id, username, authentication_code, first_name, last_name, email, phone_number, date_of_birth)
VALUES (2, 'user', '$2a$10$Hj4cEnXFNuUuVucxMa18ceoYoK.XFh3QwJ.OPsspWFI3jnsw9cx3i', 'Nguyen Van', 'Hung','user@gmail.com', '0987654321', '2002-11-21');

-- Insert Table Role
INSERT INTO roles (id, role_name)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role_name)
VALUES (2, 'USER');

-- Insert table Roles_Users
INSERT INTO users_roles (role_id, user_id)
VALUES (1, 1);
-- INSERT INTO users_roles (role_id, user_id)
-- VALUES (2, 1);
INSERT INTO users_roles (role_id, user_id)
VALUES (2, 2);


-- Insert Table Address
INSERT INTO address (id_user, line, ward, district, province)
VALUES (1, 'Số 7', 'Yên Sở', 'Hoài Đức', 'Hà Nội');
INSERT INTO address (id_user, line, ward, district, province)
VALUES (1, 'Số 8', 'abc', 'Nam Từ Liêm', 'Hà Nội');

INSERT INTO comment(id_user,content,createcmtday)
VALUES (1,'Bai nay rat hay','2024-06-24');
INSERT INTO comment(id_user,content,createcmtday)
VALUES (1,'Bai nay khong hay','2026-06-24');