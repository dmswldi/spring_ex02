DROP SEQUENCE seq_board;
CREATE SEQUENCE seq_board;

DROP TABLE tbl_board;
CREATE TABLE tbl_board (
    bno NUMBER(10, 0),
    title VARCHAR2(200) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    writer VARCHAR2(50) NOT NULL,
    regdate DATE DEFAULT sysdate,
    updatedate DATE DEFAULT sysdate
);

ALTER TABLE tbl_board ADD CONSTRAINT pk_board
PRIMARY KEY (bno);

INSERT INTO tbl_board (bno, title, content, writer)
VALUES (seq_board.nextval, '제목', '내용', 'eun');

SELECT * FROM tbl_board;
commit;

SELECT * FROM tbl_board WHERE bno > 0;
SELECT seq_board.nextval FROM dual;

CREATE SEQUENCE seq_comment;

DROP TABLE tbl_comment;
CREATE TABLE tbl_comment (
    cno NUMBER(10, 0),-- GENERATED AS IDENTITY
    bno NUMBER(10, 0) NOT NULL,
    content VARCHAR2(500) NOT NULL,
    writer VARCHAR2(50) NOT NULL,
    regdate DATE DEFAULT sysdate,
    PRIMARY KEY (cno)
);

ALTER TABLE tbl_comment ADD updatedate DATE DEFAULT sysdate;

INSERT INTO tbl_comment
VALUES (seq_comment.nextval, 1, 'eunjiya anyoung', 'yun', sysdate, sysdate);

SELECT * FROM tbl_comment;
commit;
