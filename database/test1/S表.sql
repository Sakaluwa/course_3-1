--ָ��ʹ�õ����ݿ�
USE SPJ
GO
--������S,����������
CREATE TABLE S
(
SNO CHAR(10) NOT NULL,
SNAME VARCHAR(50) NOT NULL,
STATUS INT,
CITY VARCHAR(50)
)
INSERT INTO S(SNO,SNAME,STATUS,CITY)
VALUES('S1','����',20,'���'),
('S2','ʢ��',10,'����'),
('S3','������',30,'����'),
('S4','��̩ʢ',20,'���'),
('S5','Ϊ��',30,'�Ϻ�')
GO --�ύT-SQL����һ����־