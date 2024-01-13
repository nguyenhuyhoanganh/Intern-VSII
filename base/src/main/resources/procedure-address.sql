use base;
DELIMITER //
CREATE PROCEDURE getAllAddressByUserId(IN userId INT)
BEGIN
SELECT * FROM address WHERE id_user = userId;
END; //
DELIMITER ;
CALL getAllAddressByUserId(2);

DELIMITER //
CREATE PROCEDURE getAllAddress()
BEGIN
SELECT *
FROM address;
END; //
DELIMITER ;
CALL getAllAddress();

DELIMITER //
CREATE PROCEDURE getAllAddressById(IN addressId INT)
BEGIN
SELECT * FROM address WHERE id = addressId;
END; //
DELIMITER ;
CALL getAllAddressById(2);

DELIMITER //
CREATE PROCEDURE createAddressProcedure(
    IN p_userId long,
    IN p_line VARCHAR(255),
    IN p_ward VARCHAR(255),
    IN p_district VARCHAR(255),
    IN p_province VARCHAR(255)

)
BEGIN
INSERT INTO Address (id_user, line, ward, district, province)
VALUES (p_userId, p_line, p_ward, p_district, p_province);

CALL getAllAddressById( LAST_INSERT_ID());
END //
DELIMITER ;

CALL createAddressProcedure(2, 'aaa', 'aaa', 'aaa', 'aaa');

DELIMITER //
CREATE PROCEDURE updateAddressProcedure(
    IN p_Id long,
    IN p_userId long,
    IN p_line VARCHAR(255),
    IN p_ward VARCHAR(255),
    IN p_district VARCHAR(255),
    IN p_province VARCHAR(255)
)
BEGIN
UPDATE address ad
SET
    line =p_line,
    ward = p_ward,
    district = p_district,
    province = p_province
WHERE ad.id = p_Id AND p_userId= ad.id_user ;

CALL getAllAddressById(p_Id);
CALL getAllAddressByUserId(p_userId);
END //
DELIMITER ;
CALL updateAddressProcedure(2,1, 'aaa', 'aaa', 'aaa', 'aaa');

DELIMITER //
CREATE PROCEDURE deleteAddressProcedure(
    IN p_addressId INT
)
BEGIN
DELETE FROM Address WHERE id = p_addressId ;
END //
DELIMITER ;
CALL deleteAddressProcedure(2);


