use base;
Drop procedure if exists callGetCommentProcedure;
DELIMITER \\
create procedure callGetCommentProcedure()
begin
select * from Comment where id=1;
end \\
DELIMITER ;
Call callGetCommentProcedure();
Drop procedure if exists checkDayCreateCMT;
DELIMITER \\

create procedure checkDayCreateCMT(
    in i_NgayTaoCMT Date
)
begin
select id,id_user,content , createcmtday from Comment as c
where c.createcmtday < i_NgayTaoCMT ;
end \\
DELIMITER ;
call checkDayCreateCMT('2025-06-28');
select*from Comment
                DELIMITER \\
create procedure getCommentByUserID(
    in UserID int)
begin
select * from Comment as c where c.id_user=UserID;
end \\
DELIMITER ;
Call getCommentByUserID(1);
DELIMITER \\
create procedure getAllComment()
begin
select * from Comment;
end \\
DELIMITER ;
Call getAllComment();

Drop procedure if exists sp_addComment;
DELIMITER \\
CREATE PROCEDURE sp_addComment(
    IN i_idUser INT,
    IN i_content VARCHAR(255),
    IN i_createcmtday DATE,
    OUT o_commentID INT
)
BEGIN
INSERT INTO Comment(createcmtday, id_user, content)
VALUES (i_createcmtday, i_idUser, i_content);
SET o_commentID = LAST_INSERT_ID();
SELECT * FROM Comment WHERE id = o_commentID;
END \\
DELIMITER ;
CALL sp_addComment(1, 'Bai nay cuc hay', '2025-01-12', @commentID);

DELIMITER \\
CREATE PROCEDURE sp_updateComment(
    IN i_commentID INT,
    IN i_newContent VARCHAR(255),
    IN i_createcmtday DATE
)
BEGIN
    -- Cập nhật nội dung của comment
UPDATE Comment SET createcmtday = i_createcmtday, content = i_newContent WHERE id = i_commentID;

-- Trả về thông tin của comment sau khi cập nhật
SELECT * FROM Comment WHERE id = i_commentID;
END \\
DELIMITER ;
Call sp_updateComment(1,"Bai nay cuc ki cuc ki hay","2025-12-15")
DELIMITER \\
create procedure deleteCommentByID(
    in i_ID int)
begin
DELETE from Comment  where id=i_ID;
end \\
DELIMITER ;
Call deleteCommentByID(1)