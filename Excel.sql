CREATE TABLE term (         -- 학기 테이블
    code INT PRIMARY KEY,   -- 학기코드
    term VARCHAR2(20)       -- 학기
);

CREATE TABLE major (        -- 학과 테이블
    code INT PRIMARY KEY,   -- 학과코드
    major VARCHAR2(50)      -- 학과명
);

CREATE TABLE professor (    -- 교수 테이블
    code INT PRIMARY KEY,   -- 교수코드
    name VARCHAR2(50),      -- 교수명
    profMajor VARCHAR2(50)  -- 교수전공
);

CREATE TABLE student (      -- 학생 테이블
    studentNum INT PRIMARY KEY, -- 학번
    name VARCHAR2(50),          -- 이름
    birth DATE,                 -- 생년월일
    major INT,                  -- 학과
    CONSTRAINT major_student_fk FOREIGN KEY(major)
    REFERENCES major(code),     -- 학과 FK
    admission DATE,             -- 입학일
    term INT,                   -- 학기
    CONSTRAINT term_student_fk FOREIGN KEY(term)
    REFERENCES term(code),      -- 학기 FK
    addr VARCHAR2(100),         -- 주소
    professor INT,              -- 담당교수
    CONSTRAINT professor_student_fk FOREIGN KEY(professor)
    REFERENCES professor(code), -- 담당교수 FK
    beIn VARCHAR2(1)            -- 재학여부
);

CREATE TABLE probation (        -- 학사 경고 테이블
    proNum INT PRIMARY KEY,     -- 경고번호
    stuNum INT,                 -- 학번
    CONSTRAINT student_probation_fk FOREIGN KEY(stuNum)
    REFERENCES student(studentNum), -- 학번 FK
    term INT,                   -- 학기코드
    CONSTRAINT term_probation_fk FOREIGN KEY(term)
    REFERENCES term(code)       -- 학기코드 FK
);

INSERT INTO term VALUES (1, '1학기');
INSERT INTO term VALUES (2, '2학기');
INSERT INTO term VALUES (3, '3학기');
INSERT INTO term VALUES (4, '4학기');
INSERT INTO term VALUES (5, '5학기');
INSERT INTO term VALUES (6, '6학기');
INSERT INTO term VALUES (7, '7학기');
INSERT INTO term VALUES (8, '8학기');

SELECT * FROM term;

INSERT INTO professor VALUES (1, '한세미', '운영체제');
INSERT INTO professor VALUES (2, '김명지', '자료구조');
INSERT INTO professor VALUES (3, '조혜경', '통계학');
INSERT INTO professor VALUES (4, '김효진', '컴파일러');
INSERT INTO professor VALUES (5, '신효주', '객체지향');
INSERT INTO professor VALUES (6, '김익추', '데이터');
INSERT INTO professor VALUES (7, '박옥자', '네트워크');
INSERT INTO professor VALUES (8, '김정규', '보안');
INSERT INTO professor VALUES (9, '신말순', '임베디드');
INSERT INTO professor VALUES (0, '김덕수', '로봇');

SELECT * FROM professor;

INSERT INTO major VALUES (1, '소프트웨어');
INSERT INTO major VALUES (2, '데이터베이스');
INSERT INTO major VALUES (3, '임베디드');
INSERT INTO major VALUES (4, '드론');
INSERT INTO major VALUES (5, '알고리즘');
INSERT INTO major VALUES (6, '논리회로');
INSERT INTO major VALUES (7, '계산기구조');
INSERT INTO major VALUES (8, '운영체제');
INSERT INTO major VALUES (9, '멀티미디어');
INSERT INTO major VALUES (0, '네트워크');

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