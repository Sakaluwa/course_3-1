--指定使用的数据库
USE SPJ
GO
--创建表P,并插入数据
CREATE TABLE SPJ
(
SNO CHAR(10) NOT NULL,
PNO CHAR(10) NOT NULL,
JNO CHAR(10) NOT NULL,
QTY INT NOT NULL
)
INSERT INTO SPJ(SNO,PNO,JNO,QTY)
VALUES('S1','P1','J1',200),
('S1','P1','J3',100),
('S1','P1','J4',700),
('S1','P2','J2',100),
('S2','P3','J1',400),
('S2','P3','J2',200),
('S2','P3','J4',500),
('S2','P3','J5',400),
('S2','P5','J1',400),
('S2','P5','J2',100),
('S3','P1','J1',200),
('S3','P3','J1',200),
('S4','P5','J1',100),
('S4','P6','J3',300),
('S4','P6','J4',200),
('S5','P2','J4',100),
('S5','P3','J1',200),
('S5','P6','J2',200),
('S5','P6','J4',500)
GO --提交T-SQL语句的一个标志


