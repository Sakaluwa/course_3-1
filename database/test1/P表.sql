--指定使用的数据库
USE SPJ
GO
--创建表P,并插入数据
CREATE TABLE P
(
PNO CHAR(10) NOT NULL,
PNAME VARCHAR(50) NOT NULL,
COLOR VARCHAR(50),
WEIGHT INT
)
INSERT INTO P(PNO,PNAME,COLOR,WEIGHT)
VALUES('P1','螺母','红',12),
('P2','螺栓','绿',17),
('P3','螺丝刀','蓝',14),
('P4','螺丝刀','红',14),
('P5','凸轮','蓝',40),
('P6','齿轮','红',30)
GO --提交T-SQL语句的一个标志

