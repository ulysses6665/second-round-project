INSERT INTO member (member_name, member_gender, member_college, member_student_id, member_role) VALUES
     ('小林',   '男', '计算机学院', '2026001', 'MEMBER'),
     ('王老师', '男', '计算机学院', 'T0001',   'TEACHER'),
     ('李财务', '女', '财务处',     'F0001',   'FINANCE');

INSERT INTO project (project_name, project_total, project_available, project_frozen, project_used)
VALUES ('传感器采购', 1000, 1000, 0, 0);

INSERT INTO project_member (project_id, member_id) VALUES (1, 1), (1, 2), (1, 3);