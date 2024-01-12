/*
	Procedure helper User Entity
*/
-- find all ----------------------------------------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_findAllUser $$
CREATE PROCEDURE sp_findAllUser()
BEGIN
SELECT `users`.`date_of_birth`,
       `users`.`id`,
       `users`.`authentication_code`,
       `users`.`email`,
       `users`.`first_name`,
       `users`.`last_name`,
       `users`.`phone_number`,
       `users`.`username`
FROM `base`.`users`;
END $$
DELIMITER ;
-----------------------------------------------------------------------------------
-- find user by id ----------------------------------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_findUserById $$
CREATE PROCEDURE sp_findUserById(id_in bigint)
BEGIN
SELECT date_of_birth,id,authentication_code,email,first_name,last_name,phone_number,username
FROM users u
where u.id = id_in;
END; $$
DELIMITER ;
---------------------------------------------------------------------------------------
-- create
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_createUser $$
CREATE PROCEDURE sp_createUser( in date_of_birth date,in authentication_code varchar(255),in email varchar(255), in first_name varchar(255), in last_name varchar(255),in phone_number varchar(255),in username varchar(255)
)
BEGIN
INSERT INTO `base`.`users`(`date_of_birth`,`authentication_code`,`email`,`first_name`,`last_name`,`phone_number`,`username`)
VALUES
    (date_of_birth,authentication_code,email,first_name,last_name,phone_number,username);

call sp_findUserById( LAST_INSERT_ID());
END $$
DELIMITER ;

call sp_createUser('2003-01-30','12345','duongviethung2003@gmail.com',N'Hùng',N'Dương Việt','0375773850','viethung2801')
-- update

-- create
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_updateUser $$
CREATE PROCEDURE sp_updateUser(
    in id_in bigint, in date_of_birth date,in authentication_code varchar(255),in email varchar(255), in first_name varchar(255), in last_name varchar(255),in phone_number varchar(255),in username varchar(255)
)
BEGIN
UPDATE users u
SET
    date_of_birth = date_of_birth,
    authentication_code =authentication_code,
    email = email,
    first_name = first_name,
    last_name = last_name,
    phone_number = phone_number,
    username = username
WHERE u.id = id_in;

call sp_findUserById( id_in);
END $$
DELIMITER ;

call sp_updateUser(1,'2003-01-30','12345','duongviethung2003@gmail.com',N'Hùng',N'Dương Việt','0375773850','viethung2801')

-- delete
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_deleteUserById $$
CREATE PROCEDURE sp_deleteUserById(id_in bigint)
BEGIN
delete from users_roles
where user_id = id_in;

delete from users
where id = id_in;
END $$
DELIMITER ;

call sp_deleteUserById(1)
