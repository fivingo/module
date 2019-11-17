CREATE TABLE member (
    userId  VARCHAR2(50) PRIMARY KEY,   -- ���̵�
    pwd     VARCHAR2(20),   -- ��й�ȣ
    name    VARCHAR2(15)    -- �̸�
);

CREATE TABLE board (
    boardId NUMBER PRIMARY KEY, -- ��ȣ
    title   VARCHAR2(100),      -- ����
    userId  VARCHAR2(50),       -- �ۼ���
    CONSTRAINT userId_board_fk FOREIGN KEY(userId)
    REFERENCES member(userId),  -- �ۼ��� fk
    createDate  DATE,           -- �ۼ�����
    content VARCHAR2(2000),     -- ����
    deleteYN    CHAR(1),        -- �������� (Y/N)
    viewCount   NUMBER,         -- ��ȸ��
    ref     NUMBER,             -- �׷� ID
    step    NUMBER,             -- �鿩����
    position    NUMBER          -- �׷� �� ��ġ(����)
);
    
CREATE TABLE board_reply (
    replyId NUMBER PRIMARY KEY, -- ��ȣ
    boardId NUMBER,             -- �Խù� ��ȣ
    CONSTRAINT boardId_fk FOREIGN KEY(boardId)
    REFERENCES board(boardId),  -- �Խù� ��ȣ fk
    userId  VARCHAR2(50),       -- �ۼ���
    CONSTRAINT userId_reply_fk FOREIGN KEY(userId)
    REFERENCES member(userId),  -- �ۼ��� fk
    createDate  DATE,           -- �ۼ�����
    content VARCHAR2(2000),     -- ����
    deleteYN    CHAR(1),        -- �������� (Y/N)
    ref     NUMBER,             -- �׷� ID
    step    NUMBER,             -- �鿩����
    position    NUMBER          -- �׷� �� ��ġ(����)
);

CREATE TABLE files (
    fileId      NUMBER PRIMARY KEY, -- ��ȣ
    originalName    VARCHAR2(100),  -- ���ϸ�(����)
    maskName    VARCHAR2(100),      -- ���ϸ�(����)
    boardId     NUMBER,             -- �Խù� ��ȣ
    CONSTRAINT boardId_files_fk FOREIGN KEY(boardId)
    REFERENCES board(boardId)       -- �Խù� ��ȣ fk
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
VALUES (seq_board.nextval, '����������', 123, SYSDATE, '��������', 'N', 123, 123, 123, 123);
-- �ܷ�Ű ������ ���� ������ ����

-- ������ ����
CREATE SEQUENCE seq_board START WITH 1  INCREMENT BY 1;
CREATE SEQUENCE seq_board_reply START WITH 1  INCREMENT BY 1;
CREATE SEQUENCE seq_files START WITH 1  INCREMENT BY 1;

UPDATE board SET title = '������', content = '����' WHERE boardId = 19;

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
    bod_idx     NUMBER,             -- �Խù� ��ȣ
    CONSTRAINT bod_shortUrl_fk FOREIGN KEY(bod_idx)
    REFERENCES board(boardId),      -- �Խù� ��ȣ fk
    or_URL      varchar2(200),       -- ���� url
    sh_URL      varchar2(10),        -- ���� url Ű�ڵ�
    cr_ID       varchar2(50)         -- ������ ��� ID
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