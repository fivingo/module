CREATE TABLE gis (
    idx     NUMBER PRIMARY KEY,
    xCoord  VARCHAR2(100) not null,
    yCoord  VARCHAR2(100) not null,
    address VARCHAR2(255) not null,
    uName   VARCHAR2(50),
    uGroup  VARCHAR2(20)
);

DESC gis;
CREATE SEQUENCE seq_gis START WITH 1  INCREMENT BY 1;
DROP TABLE gis;
PURGE RECYCLEBIN;

commit;

INSERT INTO gis (idx, xCoord, yCoord, address, uName, uGroup) VALUES (seq_gis.nextval, '37', '126', '서울특별시 구로구로구', '회사5', '직장');

SELECT * FROM gis;