--Ϊ������ѧ���γ̳ɼ��Լ���ѯ�ɼ������ǽ���ѧ���γ̳ɼ���ͼ����������ͼ���������
CREATE VIEW V_StudentCourseGrade
AS
	SELECT Student.*,Major.Mname,Course.Cno,Course.Cname,sc.Grade
	FROM Student LEFT OUTER JOIN SC on SC.Sno=Student.Sno
	LEFT OUTER JOIN Course ON SC.Cno=Course.Cno
	LEFT OUTER JOIN Major ON Student.Mno=Major.Mno