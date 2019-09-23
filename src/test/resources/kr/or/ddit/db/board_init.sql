-- 테이블, 시퀀스 삭제
/* 게시판 */
DROP TABLE GS_BOARD CASCADE CONSTRAINTS;

/* 게시글 */
DROP TABLE GS_POST CASCADE CONSTRAINTS;

/* 사용자 */
DROP TABLE USERS CASCADE CONSTRAINTS;

/* 첨부파일 */
DROP TABLE GS_FILE CASCADE CONSTRAINTS;

/* 댓글 */
DROP TABLE GS_REPLY CASCADE CONSTRAINTS;

/* seq_board */
DROP SEQUENCE seq_board;

/* seq_post */
DROP SEQUENCE seq_post;

/* seq_reply */
DROP SEQUENCE seq_reply;

/* seq_file */
DROP SEQUENCE seq_file;

/* seq_postGn */
DROP SEQUENCE seq_postGn;

----------------------------------------------------------------------------

-- 시퀀스 생성
/* seq_board */
CREATE SEQUENCE seq_board;

/* seq_post */
CREATE SEQUENCE seq_post;

/* seq_reply */
CREATE SEQUENCE seq_reply;

/* seq_file */
CREATE SEQUENCE seq_file;

/* seq_postGn */
CREATE SEQUENCE seq_postGn start with 13;

/* 게시판 */
CREATE TABLE GS_BOARD (
	boardSeq NUMBER NOT NULL, /* 게시판 번호 */
	boardNm VARCHAR2(100) NOT NULL, /* 게시판 이름 */
	userId VARCHAR2(20) NOT NULL, /* 관리자 */
	boardLocation NUMBER(3) NOT NULL, /* 게시판 순서 */
	boardStatus NUMBER(1) NOT NULL /* 사용여부 */
);

COMMENT ON TABLE GS_BOARD IS '게시판';

COMMENT ON COLUMN GS_BOARD.boardSeq IS '게시판 번호';

COMMENT ON COLUMN GS_BOARD.boardNm IS '게시판 이름';

COMMENT ON COLUMN GS_BOARD.userId IS '관리자';

COMMENT ON COLUMN GS_BOARD.boardLocation IS '게시판 순서';

COMMENT ON COLUMN GS_BOARD.boardStatus IS '사용여부';

CREATE UNIQUE INDEX PK_GS_BOARD
	ON GS_BOARD (
		boardSeq ASC
	);

ALTER TABLE GS_BOARD
	ADD
		CONSTRAINT PK_GS_BOARD
		PRIMARY KEY (
			boardSeq
		);

/* 게시글 */
CREATE TABLE GS_POST (
	postSeq NUMBER NOT NULL, /* 게시글 번호 */
	boardSeq NUMBER NOT NULL, /* 게시판 번호 */
	postNm VARCHAR2(100) NOT NULL, /* 게시글 제목 */
	postContent CLOB NOT NULL, /* 게시글 내용 */
	userId VARCHAR2(20) NOT NULL, /* 작성자 */
	postRegDate DATE NOT NULL, /* 작성일시 */
	postModDate DATE, /* 수정일시 */
	postStatus NUMBER(1) NOT NULL, /* 삭제여부 */
	postGn NUMBER NOT NULL, /* 그룹 번호 */
	parentSeq NUMBER /* 부모게시글번호 */
);

COMMENT ON TABLE GS_POST IS '게시글';

COMMENT ON COLUMN GS_POST.postSeq IS '게시글 번호';

COMMENT ON COLUMN GS_POST.boardSeq IS '게시판 번호';

COMMENT ON COLUMN GS_POST.postNm IS '게시글 제목';

COMMENT ON COLUMN GS_POST.postContent IS '게시글 내용';

COMMENT ON COLUMN GS_POST.userId IS '작성자';

COMMENT ON COLUMN GS_POST.postRegDate IS '작성일시';

COMMENT ON COLUMN GS_POST.postModDate IS '수정일시';

COMMENT ON COLUMN GS_POST.postStatus IS '삭제여부';

COMMENT ON COLUMN GS_POST.postGn IS '그룹 번호';

COMMENT ON COLUMN GS_POST.parentSeq IS '부모게시글번호';

CREATE UNIQUE INDEX PK_GS_POST
	ON GS_POST (
		postSeq ASC
	);

ALTER TABLE GS_POST
	ADD
		CONSTRAINT PK_GS_POST
		PRIMARY KEY (
			postSeq
		);

/* 사용자 */
CREATE TABLE USERS (
	userId VARCHAR2(20) NOT NULL, /* 사용자 아이디 */
	userNm VARCHAR2(20), /* 사용자 이름 */
	pass VARCHAR2(100), /* 비밀번호 */
	reg_dt DATE, /* 생일 */
	alias VARCHAR2(50), /* 별명 */
	addr1 VARCHAR2(50), /* 주소 */
	addr2 VARCHAR2(50), /* 상세주소 */
	zipCode VARCHAR2(5), /* 우편번호 */
	fileName VARCHAR2(50), /* 이미지파일명 */
	realFileName VARCHAR2(200), /* 이미지경로 */
     realFileName2 VARCHAR2(200) /* 이미지경로 */
);

COMMENT ON TABLE USERS IS '사용자';

COMMENT ON COLUMN USERS.userId IS '사용자 아이디';

COMMENT ON COLUMN USERS.userNm IS '사용자 이름';

COMMENT ON COLUMN USERS.pass IS '비밀번호';

COMMENT ON COLUMN USERS.reg_dt IS '생일';

COMMENT ON COLUMN USERS.alias IS '별명';

COMMENT ON COLUMN USERS.addr1 IS '주소';

COMMENT ON COLUMN USERS.addr2 IS '상세주소';

COMMENT ON COLUMN USERS.zipCode IS '우편번호';

COMMENT ON COLUMN USERS.fileName IS '이미지파일명';

COMMENT ON COLUMN USERS.realFileName IS '이미지경로';

CREATE UNIQUE INDEX PK_USERS
	ON USERS (
		userId ASC
	);

/* 첨부파일 */
CREATE TABLE GS_FILE (
	fileSeq NUMBER NOT NULL, /* 첨부파일번호 */
	fileName VARCHAR2(50) NOT NULL, /* 업로드파일명 */
	realFileName VARCHAR2(200) NOT NULL, /* 실제파일경로 */
	postSeq NUMBER NOT NULL /* 게시글 번호 */
);

COMMENT ON TABLE GS_FILE IS '첨부파일';

COMMENT ON COLUMN GS_FILE.fileSeq IS '첨부파일번호';

COMMENT ON COLUMN GS_FILE.fileName IS '업로드파일명';

COMMENT ON COLUMN GS_FILE.realFileName IS '실제파일경로';

COMMENT ON COLUMN GS_FILE.postSeq IS '게시글 번호';

CREATE UNIQUE INDEX PK_GS_FILE
	ON GS_FILE (
		fileSeq ASC
	);

ALTER TABLE GS_FILE
	ADD
		CONSTRAINT PK_GS_FILE
		PRIMARY KEY (
			fileSeq
		);

/* 댓글 */
CREATE TABLE GS_REPLY (
	replySeq NUMBER NOT NULL, /* 댓글번호 */
	postSeq NUMBER NOT NULL, /* 게시글 번호 */
	userId VARCHAR2(20) NOT NULL, /* 사용자 아이디 */
	replyContent CLOB NOT NULL, /* 내용 */
	replyRegDate DATE NOT NULL, /* 작성일시 */
	replyModDate DATE, /* 수정일시 */
	replyStatus NUMBER(1) NOT NULL /* 삭제여부 */
);

COMMENT ON TABLE GS_REPLY IS '댓글';

COMMENT ON COLUMN GS_REPLY.replySeq IS '댓글번호';

COMMENT ON COLUMN GS_REPLY.postSeq IS '게시글 번호';

COMMENT ON COLUMN GS_REPLY.userId IS '사용자 아이디';

COMMENT ON COLUMN GS_REPLY.replyContent IS '내용';

COMMENT ON COLUMN GS_REPLY.replyRegDate IS '작성일시';

COMMENT ON COLUMN GS_REPLY.replyModDate IS '수정일시';

COMMENT ON COLUMN GS_REPLY.replyStatus IS '삭제여부';

CREATE UNIQUE INDEX PK_GS_REPLY
	ON GS_REPLY (
		replySeq ASC
	);

ALTER TABLE USERS
	ADD
		CONSTRAINT PK_USERS
		PRIMARY KEY (
			userId
		);

ALTER TABLE GS_REPLY
	ADD
		CONSTRAINT PK_GS_REPLY
		PRIMARY KEY (
			replySeq
		);

ALTER TABLE GS_BOARD
	ADD
		CONSTRAINT FK_USERS_TO_GS_BOARD
		FOREIGN KEY (
			userId
		)
		REFERENCES USERS (
			userId
		);

ALTER TABLE GS_POST
	ADD
		CONSTRAINT FK_USERS_TO_GS_POST
		FOREIGN KEY (
			userId
		)
		REFERENCES USERS (
			userId
		);

ALTER TABLE GS_POST
	ADD
		CONSTRAINT FK_GS_BOARD_TO_GS_POST
		FOREIGN KEY (
			boardSeq
		)
		REFERENCES GS_BOARD (
			boardSeq
		);

ALTER TABLE GS_POST
	ADD
		CONSTRAINT FK_GS_POST_TO_GS_POST
		FOREIGN KEY (
			parentSeq
		)
		REFERENCES GS_POST (
			postSeq
		);

ALTER TABLE GS_FILE
	ADD
		CONSTRAINT FK_GS_POST_TO_GS_FILE
		FOREIGN KEY (
			postSeq
		)
		REFERENCES GS_POST (
			postSeq
		);

ALTER TABLE GS_REPLY
	ADD
		CONSTRAINT FK_GS_POST_TO_GS_REPLY
		FOREIGN KEY (
			postSeq
		)
		REFERENCES GS_POST (
			postSeq
		);

ALTER TABLE GS_REPLY
	ADD
		CONSTRAINT FK_USERS_TO_GS_REPLY
		FOREIGN KEY (
			userId
		)
		REFERENCES USERS (
			userId
		);

-- 유저 컬럼 추가
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('brown','브라운','c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44',to_date('2019/08/12','YYYY/MM/DD'),'곰',null,null,null,'moon.png','e:\upload\2019\08\acb2dc22-77a1-41fc-999a-4c0a46907547.png',null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('cony','코니','de1153428acef7fd7b999f1227d4882146bd7ea16b595b43bf5090d41d3637',to_date('2019/08/12','YYYY/MM/DD'),'토끼',null,null,null,'cony.png','e:\upload\2019\08\1bf53b7d-075c-4699-9a08-9e458447a88c.png',null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('sally','샐리','4aeaaaccd26ed685e4e3c563bcdb1f9d1dabd77f1b7b819625679936648d49c9',to_date('2019/08/12','YYYY/MM/DD'),'병아리',null,null,null,'sally.png','e:\upload\2019\08\84badab6-57cc-484d-9db1-5a21daad1704.png','/upload/2019/08/sally.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('james','제임스','6765c531920463d7bb44112f8f21abcea85913e3197978828fbd076d1d36f4d',to_date('2019/08/12','YYYY/MM/DD'),'사람',null,null,null,'james.png','e:\upload\2019\08\064d8899-a364-415b-9d29-886a293349fc.png',null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('moon','문','7a37a8623ff85b19476f7d052349e1d57e5bded313cccdea99b9601e1ace5d',to_date('2019/08/12','YYYY/MM/DD'),'달',null,null,null,'moon.png','e:\upload\2019\08\507e7def-9120-443c-9ffe-1c9f58503159.png',null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid1','xusernm1','xuserid1pass',to_date('2019/08/02','YYYY/MM/DD'),'xuseralias1',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid2','xusernm2','xuserid2pass',to_date('2019/07/23','YYYY/MM/DD'),'xuseralias2',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid3','xusernm3','xuserid3pass',to_date('2019/07/13','YYYY/MM/DD'),'xuseralias3',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid4','xusernm4','xuserid4pass',to_date('2019/07/03','YYYY/MM/DD'),'xuseralias4',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid5','xusernm5','xuserid5pass',to_date('2019/06/23','YYYY/MM/DD'),'xuseralias5',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid6','xusernm6','xuserid6pass',to_date('2019/06/13','YYYY/MM/DD'),'xuseralias6',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid7','xusernm7','xuserid7pass',to_date('2019/06/03','YYYY/MM/DD'),'xuseralias7',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid8','xusernm8','xuserid8pass',to_date('2019/05/24','YYYY/MM/DD'),'xuseralias8',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid9','xusernm9','xuserid9pass',to_date('2019/05/14','YYYY/MM/DD'),'xuseralias9',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid10','xusernm10','xuserid10pass',to_date('2019/05/04','YYYY/MM/DD'),'xuseralias10',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid11','xusernm11','xuserid11pass',to_date('2019/04/24','YYYY/MM/DD'),'xuseralias11',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid12','xusernm12','xuserid12pass',to_date('2019/04/14','YYYY/MM/DD'),'xuseralias12',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid13','xusernm13','xuserid13pass',to_date('2019/04/04','YYYY/MM/DD'),'xuseralias13',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid14','xusernm14','xuserid14pass',to_date('2019/03/25','YYYY/MM/DD'),'xuseralias14',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid15','xusernm15','xuserid15pass',to_date('2019/03/15','YYYY/MM/DD'),'xuseralias15',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid16','xusernm16','xuserid16pass',to_date('2019/03/05','YYYY/MM/DD'),'xuseralias16',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid17','xusernm17','xuserid17pass',to_date('2019/02/23','YYYY/MM/DD'),'xuseralias17',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid18','xusernm18','xuserid18pass',to_date('2019/02/13','YYYY/MM/DD'),'xuseralias18',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid19','xusernm19','xuserid19pass',to_date('2019/02/03','YYYY/MM/DD'),'xuseralias19',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid20','xusernm20','xuserid20pass',to_date('2019/01/24','YYYY/MM/DD'),'xuseralias20',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid21','xusernm21','xuserid21pass',to_date('2019/01/14','YYYY/MM/DD'),'xuseralias21',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid22','xusernm22','xuserid22pass',to_date('2019/01/04','YYYY/MM/DD'),'xuseralias22',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid23','xusernm23','xuserid23pass',to_date('2018/12/25','YYYY/MM/DD'),'xuseralias23',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid24','xusernm24','xuserid24pass',to_date('2018/12/15','YYYY/MM/DD'),'xuseralias24',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid25','xusernm25','xuserid25pass',to_date('2018/12/05','YYYY/MM/DD'),'xuseralias25',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid26','xusernm26','xuserid26pass',to_date('2018/11/25','YYYY/MM/DD'),'xuseralias26',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid27','xusernm27','xuserid27pass',to_date('2018/11/15','YYYY/MM/DD'),'xuseralias27',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid28','xusernm28','xuserid28pass',to_date('2018/11/05','YYYY/MM/DD'),'xuseralias28',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid29','xusernm29','xuserid29pass',to_date('2018/10/26','YYYY/MM/DD'),'xuseralias29',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid30','xusernm30','xuserid30pass',to_date('2018/10/16','YYYY/MM/DD'),'xuseralias30',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid31','xusernm31','xuserid31pass',to_date('2018/10/06','YYYY/MM/DD'),'xuseralias31',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid32','xusernm32','xuserid32pass',to_date('2018/09/26','YYYY/MM/DD'),'xuseralias32',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid33','xusernm33','xuserid33pass',to_date('2018/09/16','YYYY/MM/DD'),'xuseralias33',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid34','xusernm34','xuserid34pass',to_date('2018/09/06','YYYY/MM/DD'),'xuseralias34',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid35','xusernm35','xuserid35pass',to_date('2018/08/27','YYYY/MM/DD'),'xuseralias35',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid36','xusernm36','xuserid36pass',to_date('2018/08/17','YYYY/MM/DD'),'xuseralias36',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid37','xusernm37','xuserid37pass',to_date('2018/08/07','YYYY/MM/DD'),'xuseralias37',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid38','xusernm38','xuserid38pass',to_date('2018/07/28','YYYY/MM/DD'),'xuseralias38',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid39','xusernm39','xuserid39pass',to_date('2018/07/18','YYYY/MM/DD'),'xuseralias39',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid40','xusernm40','xuserid40pass',to_date('2018/07/08','YYYY/MM/DD'),'xuseralias40',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid41','xusernm41','xuserid41pass',to_date('2018/06/28','YYYY/MM/DD'),'xuseralias41',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid42','xusernm42','xuserid42pass',to_date('2018/06/18','YYYY/MM/DD'),'xuseralias42',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid43','xusernm43','xuserid43pass',to_date('2018/06/08','YYYY/MM/DD'),'xuseralias43',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid44','xusernm44','xuserid44pass',to_date('2018/05/29','YYYY/MM/DD'),'xuseralias44',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid45','xusernm45','xuserid45pass',to_date('2018/05/19','YYYY/MM/DD'),'xuseralias45',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid46','xusernm46','xuserid46pass',to_date('2018/05/09','YYYY/MM/DD'),'xuseralias46',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid47','xusernm47','xuserid47pass',to_date('2018/04/29','YYYY/MM/DD'),'xuseralias47',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid48','xusernm48','xuserid48pass',to_date('2018/04/19','YYYY/MM/DD'),'xuseralias48',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid49','xusernm49','xuserid49pass',to_date('2018/04/09','YYYY/MM/DD'),'xuseralias49',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid50','xusernm50','xuserid50pass',to_date('2018/03/30','YYYY/MM/DD'),'xuseralias50',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid51','xusernm51','xuserid51pass',to_date('2018/03/20','YYYY/MM/DD'),'xuseralias51',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid52','xusernm52','xuserid52pass',to_date('2018/03/10','YYYY/MM/DD'),'xuseralias52',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid53','xusernm53','xuserid53pass',to_date('2018/02/28','YYYY/MM/DD'),'xuseralias53',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid54','xusernm54','xuserid54pass',to_date('2018/02/18','YYYY/MM/DD'),'xuseralias54',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid55','xusernm55','xuserid55pass',to_date('2018/02/08','YYYY/MM/DD'),'xuseralias55',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid56','xusernm56','xuserid56pass',to_date('2018/01/29','YYYY/MM/DD'),'xuseralias56',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid57','xusernm57','xuserid57pass',to_date('2018/01/19','YYYY/MM/DD'),'xuseralias57',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid58','xusernm58','xuserid58pass',to_date('2018/01/09','YYYY/MM/DD'),'xuseralias58',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid59','xusernm59','xuserid59pass',to_date('2017/12/30','YYYY/MM/DD'),'xuseralias59',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid60','xusernm60','xuserid60pass',to_date('2017/12/20','YYYY/MM/DD'),'xuseralias60',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid61','xusernm61','xuserid61pass',to_date('2017/12/10','YYYY/MM/DD'),'xuseralias61',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid62','xusernm62','xuserid62pass',to_date('2017/11/30','YYYY/MM/DD'),'xuseralias62',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid63','xusernm63','xuserid63pass',to_date('2017/11/20','YYYY/MM/DD'),'xuseralias63',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid64','xusernm64','xuserid64pass',to_date('2017/11/10','YYYY/MM/DD'),'xuseralias64',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid65','xusernm65','xuserid65pass',to_date('2017/10/31','YYYY/MM/DD'),'xuseralias65',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid66','xusernm66','xuserid66pass',to_date('2017/10/21','YYYY/MM/DD'),'xuseralias66',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid67','xusernm67','xuserid67pass',to_date('2017/10/11','YYYY/MM/DD'),'xuseralias67',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid68','xusernm68','xuserid68pass',to_date('2017/10/01','YYYY/MM/DD'),'xuseralias68',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid69','xusernm69','xuserid69pass',to_date('2017/09/21','YYYY/MM/DD'),'xuseralias69',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid70','xusernm70','xuserid70pass',to_date('2017/09/11','YYYY/MM/DD'),'xuseralias70',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid71','xusernm71','xuserid71pass',to_date('2017/09/01','YYYY/MM/DD'),'xuseralias71',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid72','xusernm72','xuserid72pass',to_date('2017/08/22','YYYY/MM/DD'),'xuseralias72',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid73','xusernm73','xuserid73pass',to_date('2017/08/12','YYYY/MM/DD'),'xuseralias73',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid74','xusernm74','xuserid74pass',to_date('2017/08/02','YYYY/MM/DD'),'xuseralias74',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid75','xusernm75','xuserid75pass',to_date('2017/07/23','YYYY/MM/DD'),'xuseralias75',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid76','xusernm76','xuserid76pass',to_date('2017/07/13','YYYY/MM/DD'),'xuseralias76',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid77','xusernm77','xuserid77pass',to_date('2017/07/03','YYYY/MM/DD'),'xuseralias77',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid78','xusernm78','xuserid78pass',to_date('2017/06/23','YYYY/MM/DD'),'xuseralias78',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid79','xusernm79','xuserid79pass',to_date('2017/06/13','YYYY/MM/DD'),'xuseralias79',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid80','xusernm80','xuserid80pass',to_date('2017/06/03','YYYY/MM/DD'),'xuseralias80',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid81','xusernm81','xuserid81pass',to_date('2017/05/24','YYYY/MM/DD'),'xuseralias81',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid82','xusernm82','xuserid82pass',to_date('2017/05/14','YYYY/MM/DD'),'xuseralias82',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid83','xusernm83','xuserid83pass',to_date('2017/05/04','YYYY/MM/DD'),'xuseralias83',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid84','xusernm84','xuserid84pass',to_date('2017/04/24','YYYY/MM/DD'),'xuseralias84',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid85','xusernm85','xuserid85pass',to_date('2017/04/14','YYYY/MM/DD'),'xuseralias85',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid86','xusernm86','xuserid86pass',to_date('2017/04/04','YYYY/MM/DD'),'xuseralias86',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid87','xusernm87','xuserid87pass',to_date('2017/03/25','YYYY/MM/DD'),'xuseralias87',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid88','xusernm88','xuserid88pass',to_date('2017/03/15','YYYY/MM/DD'),'xuseralias88',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid89','xusernm89','xuserid89pass',to_date('2017/03/05','YYYY/MM/DD'),'xuseralias89',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid90','xusernm90','xuserid90pass',to_date('2017/02/23','YYYY/MM/DD'),'xuseralias90',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid91','xusernm91','xuserid91pass',to_date('2017/02/13','YYYY/MM/DD'),'xuseralias91',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid92','xusernm92','xuserid92pass',to_date('2017/02/03','YYYY/MM/DD'),'xuseralias92',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid93','xusernm93','xuserid93pass',to_date('2017/01/24','YYYY/MM/DD'),'xuseralias93',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid94','xusernm94','xuserid94pass',to_date('2017/01/14','YYYY/MM/DD'),'xuseralias94',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid95','xusernm95','xuserid95pass',to_date('2017/01/04','YYYY/MM/DD'),'xuseralias95',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid96','xusernm96','xuserid96pass',to_date('2016/12/25','YYYY/MM/DD'),'xuseralias96',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid97','xusernm97','xuserid97pass',to_date('2016/12/15','YYYY/MM/DD'),'xuseralias97',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid98','xusernm98','xuserid98pass',to_date('2016/12/05','YYYY/MM/DD'),'xuseralias98',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid99','xusernm99','xuserid99pass',to_date('2016/11/25','YYYY/MM/DD'),'xuseralias99',null,null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME,REALFILENAME2) values ('xuserid100','xusernm100','xuserid100pass',to_date('2016/11/15','YYYY/MM/DD'),'xuseralias100',null,null,null,null,null,null);

-- 게시판 컬럼 추가     
insert into gs_board values (seq_board.nextval, '게시판1', 'brown', '1', '1');
insert into gs_board values (seq_board.nextval, '게시판22', 'brown', '2', '1');
insert into gs_board values (seq_board.nextval, '게시판333', 'brown', '3', '0');
insert into gs_board values (seq_board.nextval, '게시판4444', 'brown', '4', '1');
insert into gs_board values (seq_board.nextval, '게시판55555', 'brown', '5', '1');

-- 게시글 추가
insert into gs_post values (seq_post.nextval, 1, '첫번째 글입니다', '내용1', 'brown', sysdate, null, 1, 1, null);
insert into gs_post values (seq_post.nextval, 1, '두번째 글입니다', '내용2', 'brown', sysdate, null, 1, 2, null);
insert into gs_post values (seq_post.nextval, 1, '세번째 글입니다', '내용3', 'brown', sysdate, null, 1, 3, null);
insert into gs_post values (seq_post.nextval, 1, '네번째 글은 두번째 글의 답글입니다', '내용4', 'brown', sysdate, null, 1, 2, 2);
insert into gs_post values (seq_post.nextval, 1, '다섯번째 글입니다', '내용5', 'brown', sysdate, null, 1, 4, null);
insert into gs_post values (seq_post.nextval, 1, '여섯번째 글은 네번째 글의 답글입니다', '내용6','brown', sysdate, null, 1, 2, 4);
insert into gs_post values (seq_post.nextval, 1, '일곱번째 글입니다', '내용7', 'brown', sysdate, null, 1, 5, null);
insert into gs_post values (seq_post.nextval, 1, '여덟번째 글입니다', '내용8', 'brown', sysdate, null, 1, 6, null);
insert into gs_post values (seq_post.nextval, 1, '아홉번째 글은 다섯번째 글의 답글입니다', '내용9','brown', sysdate, null, 1, 4, 5);
insert into gs_post values (seq_post.nextval, 1, '열번째 글은 여섯번째 글의 답글입니다', '내용10','brown', sysdate, null, 1, 2, 6);
insert into gs_post values (seq_post.nextval, 1, '열한번째 글입니다', '내용11', 'brown', sysdate, null, 1, 7, null);
insert into gs_post values (seq_post.nextval, 1, '열두번째 글은 다섯번째 글의 답글입니다', '내용12','brown', sysdate, null, 1, 4, 5);
insert into gs_post values (seq_post.nextval, 1, '열세번째 글입니다', '내용13', 'brown', sysdate, null, 1, 8, null);
insert into gs_post values (seq_post.nextval, 1, '열네번째 글입니다', '내용14', 'brown', sysdate, null, 1, 9, null);
insert into gs_post values (seq_post.nextval, 1, '열다섯번째 글은 첫번째 글의 답글입니다', '내용15','brown', sysdate, null, 1, 1, 1);
insert into gs_post values (seq_post.nextval, 1, '열여섯번째 글은 네번째 글의 답글입니다', '내용16','brown', sysdate, null, 1, 2, 4);
insert into gs_post values (seq_post.nextval, 1, '열일곱번째 글은 열번째 글의 답글입니다', '내용17','brown', sysdate, null, 1, 2, 10);
insert into gs_post values (seq_post.nextval, 1, '열여덟번째 글입니다', '내용18', 'brown', sysdate, null, 0, 9, 14);
insert into gs_post values (seq_post.nextval, 1, '열아홉번째 글입니다', '내용19', 'brown', sysdate, null, 1, 10, null);
insert into gs_post values (seq_post.nextval, 1, '스무번째 글입니다', '내용20', 'brown', sysdate, null, 1, 9, 18);
insert into gs_post values (seq_post.nextval, 1, '스물한번째 글입니다', '내용21', 'brown', sysdate, null, 1, 11, null);
insert into gs_post values (seq_post.nextval, 1, '스물두번째 글입니다', '내용22', 'brown', sysdate, null, 1, 12, null);
insert into gs_post values (seq_post.nextval, 2, '첫번째 글입니다', '내용1', 'brown', sysdate, null, 1, 1, null);
insert into gs_post values (seq_post.nextval, 2, '두번째 글입니다', '내용2', 'brown', sysdate, null, 1, 2, null);
insert into gs_post values (seq_post.nextval, 2, '세번째 글입니다', '내용3', 'brown', sysdate, null, 0, 3, null);
insert into gs_post values (seq_post.nextval, 2, '네번째 글입니다', '내용4', 'brown', sysdate, null, 1, 1, 1);
insert into gs_post values (seq_post.nextval, 2, '다섯번째 글입니다', '내용5', 'brown', sysdate, null, 1, 2, 2);

-- 댓글 추가
insert into gs_reply values(seq_reply.nextval, 1, 'brown', '댓글하나', sysdate, null, 1);
insert into gs_reply values(seq_reply.nextval, 1, 'brown', '댓글둘', sysdate, null, 1);
insert into gs_reply values(seq_reply.nextval, 1, 'brown', '댓글셋', sysdate, null, 0);
insert into gs_reply values(seq_reply.nextval, 1, 'brown', '댓글넷', sysdate, null, 1);
insert into gs_reply values(seq_reply.nextval, 1, 'brown', '댓글다섯', sysdate, null, 0);
insert into gs_reply values(seq_reply.nextval, 1, 'brown', '댓글여섯', sysdate, null, 1);
insert into gs_reply values(seq_reply.nextval, 1, 'sally', '댓글일곱', sysdate, null, 1);
insert into gs_reply values(seq_reply.nextval, 1, 'sally', '댓글여덟', sysdate, null, 1);

-- 파일 추가
insert into gs_file values(seq_file.nextval, 'brown.png', 'e:\springBoardUpload\2019\09\d5826f50-4269-4d63-992e-64654d32fb90.png', 1);
insert into gs_file values(seq_file.nextval, 'cony.png', 'e:\springBoardUpload\2019\09\cd3c90af-370c-4b38-8640-49f7c5dad583.png', 1);
insert into gs_file values(seq_file.nextval, 'james.png', 'e:\springBoardUpload\2019\09\c9b7a6b2-c052-44a0-b2f4-f09b427a5a1a.png', 1);
insert into gs_file values(seq_file.nextval, 'moon.png', 'e:\springBoardUpload\2019\09\4b9365ee-8b82-448d-adf4-3d97cec552d7.png', 1);
insert into gs_file values(seq_file.nextval, 'sally.png', 'e:\springBoardUpload\2019\09\c4fa0579-8c96-4dac-af76-1658f53144bd.png', 1);
insert into gs_file values(seq_file.nextval, 'ryan.png', 'e:\springBoardUpload\2019\09\0053a63f-8b80-4638-ab40-db8c58fe6020.png', 15);

-- 커밋
commit;