--指定使用的数据库
USE SPJ
GO
--创建表S,并插入数据
CREATE TABLE S
(
SNO CHAR(10) NOT NULL,
SNAME VARCHAR(50) NOT NULL,
STATUS INT,
CITY VARCHAR(50)
)
INSERT INTO S(SNO,SNAME,STATUS,CITY)
VALUES('S1','精益',20,'天津'),
('S2','盛锡',10,'北京'),
('S3','东方红',30,'北京'),
('S4','丰泰盛',20,'天津'),
('S5','为民',30,'上海')
GO --提交T-SQL语句的一个标志