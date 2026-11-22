
/*
member	id 主键自增、name、gender、college、student_id、role
project	id、name、total、available、frozen、used（四列金额都是 DECIMAL(12,2)）
project_member	id、project_id、member_id（两个外键，多对多）
application	id、project_id、member_id、purpose、apply_amount、approve_amount、settled_amount、status、reason
建表顺序：先建 member、project，再建 project_member、application（有外键的要在后面）。
 注意：application 表要删在最后、member 表要最后建——顺序搞反会报外键错。
 */


CREATE TABLE IF NOT EXISTS `member`(
#列信息
member_id INT PRIMARY KEY AUTO_INCREMENT                  COMMENT '成员编号',
member_name        VARCHAR(50)  NOT NULL                  COMMENT '成员名字',
member_gender      VARCHAR(10)                            COMMENT '性别',
member_college     VARCHAR(100)                           COMMENT '学院',
member_student_id  VARCHAR(20)  UNIQUE                    COMMENT '学号',
member_role        VARCHAR(20)  NOT NULL DEFAULT 'MEMBER' COMMENT '角色',
member_created_at  DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'

)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='成员表';

CREATE TABLE IF NOT EXISTS `project` (
    project_id        INT PRIMARY KEY AUTO_INCREMENT       COMMENT '项目编号',
    project_name      VARCHAR(100) NOT NULL                COMMENT '项目名称',
    project_total     DECIMAL(12,2) NOT NULL               COMMENT '总预算',
    project_available DECIMAL(12,2) NOT NULL DEFAULT 0     COMMENT '可用',
    project_frozen    DECIMAL(12,2) NOT NULL DEFAULT 0     COMMENT '冻结',
    project_used      DECIMAL(12,2) NOT NULL DEFAULT 0     COMMENT '已使用'
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='项目表';

CREATE TABLE IF NOT EXISTS `project_member` (
    project_member_id INT PRIMARY KEY AUTO_INCREMENT            COMMENT '关系编号',
    project_id        INT NOT NULL                              COMMENT '项目编号',
    member_id         INT NOT NULL                              COMMENT '成员编号', 
    joined_at         DATETIME DEFAULT CURRENT_TIMESTAMP        COMMENT '加入时间',
    UNIQUE KEY uk_project_member (project_id, member_id),
    CONSTRAINT fk_pm_project FOREIGN KEY (project_id) REFERENCES project(project_id),
    CONSTRAINT fk_pm_member  FOREIGN KEY (member_id)  REFERENCES MEMBER(member_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='成员项目关系表';

CREATE TABLE IF NOT EXISTS `application` (
    application_id  INT PRIMARY KEY AUTO_INCREMENT                                 COMMENT '申请编号',
    project_id      INT NOT NULL                                                   COMMENT '项目编号',
    member_id       INT NOT NULL                                                   COMMENT '申请人编号',
    purpose         VARCHAR(255) NOT NULL                                          COMMENT '用途',
    apply_amount    DECIMAL(12,2) NOT NULL                                         COMMENT '申请金额',
    approve_amount  DECIMAL(12,2) NOT NULL DEFAULT 0                               COMMENT '批准金额',
    settled_amount  DECIMAL(12,2) NOT NULL DEFAULT 0                               COMMENT '结算金额',
    STATUS          VARCHAR(20) NOT NULL DEFAULT 'DRAFT'                           COMMENT '状态',
    reason          VARCHAR(255)                                                   COMMENT '拒绝/取消原因',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP                             COMMENT '创建时间',
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT fk_app_project FOREIGN KEY (project_id) REFERENCES project(project_id),
    CONSTRAINT fk_app_member  FOREIGN KEY (member_id)  REFERENCES MEMBER(member_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='申请表';


/*INSERT INTO member (member_name, member_gender, member_college, member_student_id, member_role) VALUES
('小林',   '男', '计算机学院', '2026001', 'MEMBER'),
('王老师', '男', '计算机学院', 'T0001',   'TEACHER'),
('李财务', '女', '财务处',     'F0001',   'FINANCE');

INSERT INTO project (project_name, project_total, project_available, project_frozen, project_used)
VALUES ('传感器采购', 1000, 1000, 0, 0);

INSERT INTO project_member (project_id, member_id) VALUES (1, 1), (1, 2), (1, 3);



SELECT member_id, member_name, member_role FROM member;
SELECT project_id, project_name, project_total, project_available FROM project;
SELECT * FROM project_member;*/  #ai测试