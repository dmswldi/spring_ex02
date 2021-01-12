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