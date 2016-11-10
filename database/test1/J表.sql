--指定使用的数据库
USE SPJ
GO
--创建表P,并插入数据
CREATE TABLE J
(
JNO CHAR(10) NOT NULL,
JNAME VARCHAR(50) NOT NULL,
CITY VARCHAR(50)
)
INSERT INTO J(JNO,JNAME,CITY)
VALUES('J1','三建','北京'),
('J2','一汽','长春'),
('J3','弹簧厂','天津'),
('J4','造船厂','天津'),
('J5','机车厂','唐山'),
('J6','无线电厂','常州'),
('J7','半导体厂','南京')
GO --提交T-SQL语句的一个标志

