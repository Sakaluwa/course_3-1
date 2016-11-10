--为了输入学生课程成绩以及查询成绩，我们建立学生课程成绩视图，建立该视图的语句如下
CREATE VIEW V_StudentCourseGrade
AS
	SELECT Student.*,Major.Mname,Course.Cno,Course.Cname,sc.Grade
	FROM Student LEFT OUTER JOIN SC on SC.Sno=Student.Sno
	LEFT OUTER JOIN Course ON SC.Cno=Course.Cno
	LEFT OUTER JOIN Major ON Student.Mno=Major.Mno