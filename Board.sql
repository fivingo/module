CREATE TABLE member (
    userId  VARCHAR2(50) PRIMARY KEY,   -- 아이디
    pwd     VARCHAR2(20),   -- 비밀번호
    name    VARCHAR2(15)    -- 이름
);

CREATE TABLE board (
    boardId NUMBER PRIMARY KEY, -- 번호
    title   VARCHAR2(100),      -- 제목
    userId  VARCHAR2(50),       -- 작성자
    CONSTRAINT userId_board_fk FOREIGN KEY(userId)
    REFERENCES member(userId),  -- 작성자 fk
    createDate  DATE,           -- 작성일자
    content VARCHAR2(2000),     -- 내용
    deleteYN    CHAR(1),        -- 삭제여부 (Y/N)
    viewCount   NUMBER,         -- 조회수
    ref     NUMBER,             -- 그룹 ID
    step    NUMBER,             -- 들여쓰기
    position    NUMBER          -- 그룹 내 위치(정렬)
);
    
CREATE TABLE board_reply (
    replyId NUMBER PRIMARY KEY, -- 번호
    boardId NUMBER,             -- 게시물 번호
    CONSTRAINT boardId_fk FOREIGN KEY(boardId)
    REFERENCES board(boardId),  -- 게시물 번호 fk
    userId  VARCHAR2(50),       -- 작성자
    CONSTRAINT userId_reply_fk FOREIGN KEY(userId)
    REFERENCES member(userId),  -- 작성자 fk
    createDate  DATE,           -- 작성일자
    content VARCHAR2(2000),     -- 내용
    deleteYN    CHAR(1),        -- 삭제여부 (Y/N)
    ref     NUMBER,             -- 그룹 ID
    step    NUMBER,             -- 들여쓰기
    position    NUMBER          -- 그룹 내 위치(정렬)
);

CREATE TABLE files (
    fileId      NUMBER PRIMARY KEY, -- 번호
    originalName    VARCHAR2(100),  -- 파일명(원본)
    maskName    VARCHAR2(100),      -- 파일명(변형)
    boardId     NUMBER,             -- 게시물 번호
    CONSTRAINT boardId_files_fk FOREIGN KEY(boardId)
    REFERENCES board(boardId)       -- 게시물 번호 fk
);

COMMIT;
ROLLBACK;

DROP TABLE board_reply;
PURGE RECYCLEBIN;

SELECT * FROM TAB;

DESC member;
DESC board;
DESC board_reply;
DESC files;


INSERT INTO member (userId, pwd, name) VALUES (123, 123, 123);

SELECT * FROM member;
SELECT * FROM board;
SELECT * FROM board_reply;

INSERT INTO board (boardId, title, userId, createDate, content, deleteYN, viewCount, ref, step, position)
VALUES (seq_board.nextval, 'ㅁㄴㅇㄻㄴ', 123, SYSDATE, 'ㅁㄴㅇㄹ', 'N', 123, 123, 123, 123);
-- 외래키 참고값과 같은 데이터 설정

-- 시퀀스 생성
CREATE SEQUENCE seq_board START WITH 1  INCREMENT BY 1;
CREATE SEQUENCE seq_board_reply START WITH 1  INCREMENT BY 1;
CREATE SEQUENCE seq_files START WITH 1  INCREMENT BY 1;

UPDATE board SET title = 'ㅁㅁㅁ', content = 'ㅁㅁ' WHERE boardId = 19;

SELECT * FROM board WHERE deleteYN = 'N'  ORDER BY ref DESC, position ASC;

UPDATE board SET deleteYN = 'Y' WHERE boardId = 1;

SELECT COUNT(*) FROM board;
SELECT MAX(ref) FROM board_reply WHERE boardId = 64;

DELETE FROM board WHERE ref = 1;
DELETE FROM board WHERE deleteyn = 'Y';
DELETE FROM board WHERE step >= 0;
DELETE FROM board_reply WHERE step >= 0 and boardId = 76;
DELETE FROM files WHERE boardId >= 0;

select step from board where boardId = 61;
select max(step) from board where ref = 3 and deleteYN not in 'Y';
SELECT * FROM board ORDER BY ref DESC, position ASC;

select count(*) from board where ref = 3 and step = 3 and position = 3 and deleteYN = 'Y';
SELECT * FROM (SELECT * FROM board_reply WHERE boardId = 76 and deleteYN = 'N' ORDER BY ref DESC, position ASC);
UPDATE board_reply SET position = position + 1 WHERE boardId = 76 and ref = 7 and position >= 0;

insert into files values (seq_files.nextval, CONCAT('asdf_',seq_files.nextval) , 'asdf', 76);
SELECT * FROM files;
SELECT originalName FROM files WHERE boardId = 111;

SELECT * FROM (SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM board WHERE deleteYN = 'N' ORDER BY ref ASC, position ASC) a) b WHERE rnum >= 1 and rnum <= 100) order by rnum DESC;
SELECT COUNT(*) FROM board WHERE userId LIKE '%2%';
SELECT pwd FROM member WHERE userId = 'test';

----------------------------------------------------------------------------------------------------------

CREATE TABLE t_shortUrl (
    bod_idx     NUMBER,             -- 게시물 번호
    CONSTRAINT bod_shortUrl_fk FOREIGN KEY(bod_idx)
    REFERENCES board(boardId),      -- 게시물 번호 fk
    or_URL      varchar2(200),       -- 원본 url
    sh_URL      varchar2(10),        -- 단축 url 키코드
    cr_ID       varchar2(50)         -- 생성한 사람 ID
);

CREATE TABLE t_QR (
    bod_idx     NUMBER,
    CONSTRAINT bod_QR_fk FOREIGN KEY(bod_idx)
    REFERENCES board(boardId),
    cr_ID       VARCHAR2(50),
    fileName    VARCHAR2(200)
);

DROP TABLE t_shortUrl;
DROP TABLE t_QR;
PURGE RECYCLEBIN;

DESC t_shortUrl;
DESC t_QR;

SELECT * FROM t_QR;
SELECT * FROM t_shortUrl;