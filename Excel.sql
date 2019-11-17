CREATE TABLE term (         -- �б� ���̺�
    code INT PRIMARY KEY,   -- �б��ڵ�
    term VARCHAR2(20)       -- �б�
);

CREATE TABLE major (        -- �а� ���̺�
    code INT PRIMARY KEY,   -- �а��ڵ�
    major VARCHAR2(50)      -- �а���
);

CREATE TABLE professor (    -- ���� ���̺�
    code INT PRIMARY KEY,   -- �����ڵ�
    name VARCHAR2(50),      -- ������
    profMajor VARCHAR2(50)  -- ��������
);

CREATE TABLE student (      -- �л� ���̺�
    studentNum INT PRIMARY KEY, -- �й�
    name VARCHAR2(50),          -- �̸�
    birth DATE,                 -- �������
    major INT,                  -- �а�
    CONSTRAINT major_student_fk FOREIGN KEY(major)
    REFERENCES major(code),     -- �а� FK
    admission DATE,             -- ������
    term INT,                   -- �б�
    CONSTRAINT term_student_fk FOREIGN KEY(term)
    REFERENCES term(code),      -- �б� FK
    addr VARCHAR2(100),         -- �ּ�
    professor INT,              -- ��米��
    CONSTRAINT professor_student_fk FOREIGN KEY(professor)
    REFERENCES professor(code), -- ��米�� FK
    beIn VARCHAR2(1)            -- ���п���
);

CREATE TABLE probation (        -- �л� ��� ���̺�
    proNum INT PRIMARY KEY,     -- ����ȣ
    stuNum INT,                 -- �й�
    CONSTRAINT student_probation_fk FOREIGN KEY(stuNum)
    REFERENCES student(studentNum), -- �й� FK
    term INT,                   -- �б��ڵ�
    CONSTRAINT term_probation_fk FOREIGN KEY(term)
    REFERENCES term(code)       -- �б��ڵ� FK
);

INSERT INTO term VALUES (1, '1�б�');
INSERT INTO term VALUES (2, '2�б�');
INSERT INTO term VALUES (3, '3�б�');
INSERT INTO term VALUES (4, '4�б�');
INSERT INTO term VALUES (5, '5�б�');
INSERT INTO term VALUES (6, '6�б�');
INSERT INTO term VALUES (7, '7�б�');
INSERT INTO term VALUES (8, '8�б�');

SELECT * FROM term;

INSERT INTO professor VALUES (1, '�Ѽ���', '�ü��');
INSERT INTO professor VALUES (2, '�����', '�ڷᱸ��');
INSERT INTO professor VALUES (3, '������', '�����');
INSERT INTO professor VALUES (4, '��ȿ��', '�����Ϸ�');
INSERT INTO professor VALUES (5, '��ȿ��', '��ü����');
INSERT INTO professor VALUES (6, '������', '������');
INSERT INTO professor VALUES (7, '�ڿ���', '��Ʈ��ũ');
INSERT INTO professor VALUES (8, '������', '����');
INSERT INTO professor VALUES (9, '�Ÿ���', '�Ӻ����');
INSERT INTO professor VALUES (0, '�����', '�κ�');

SELECT * FROM professor;

INSERT INTO major VALUES (1, '����Ʈ����');
INSERT INTO major VALUES (2, '�����ͺ��̽�');
INSERT INTO major VALUES (3, '�Ӻ����');
INSERT INTO major VALUES (4, '���');
INSERT INTO major VALUES (5, '�˰���');
INSERT INTO major VALUES (6, '��ȸ��');
INSERT INTO major VALUES (7, '���ⱸ��');
INSERT INTO major VALUES (8, '�ü��');
INSERT INTO major VALUES (9, '��Ƽ�̵��');
INSERT INTO major VALUES (0, '��Ʈ��ũ');

SELECT * FROM major;

COMMIT;
ROLLBACK;

DROP TABLE student;
DROP TABLE probation;
PURGE RECYCLEBIN;

CREATE SEQUENCE seq_probation START WITH 1  INCREMENT BY 1;

SELECT * FROM student ORDER BY studentNum ASC;
INSERT INTO student VALUES (1, 'asdf', '19121212', 1, '19121212', 1, 'asdf', 1, 'Y');
DELETE FROM student WHERE studentNum > 0;
DELETE FROM probation WHERE proNum > 0;

select * from probation;
INSERT INTO probation (proNum, stuNum, term) VALUES (seq_probation.nextval, 10002, 2);
DESC probation;

SELECT name, major, term FROM student WHERE studentNum = 10029 ORDER BY studentNum ASC;