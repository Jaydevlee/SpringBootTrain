INSERT INTO MEMBER (id, name, email, age)
VALUES (MEM_SEQ.NEXTVAL, '윤서준', 'SeojunYoon@hanbit.co.kr', 10);
INSERT INTO MEMBER (id, name, email, age)
VALUES (MEM_SEQ.NEXTVAL, '윤광철', 'Kwangcheol@hanbit.co.kr', 43);
INSERT INTO MEMBER (id, name, email, age)
VALUES (MEM_SEQ.NEXTVAL, '공미영', 'MiyeongKong@hanbit.co.kr', 23);
INSERT INTO MEMBER (id, name, email, age)
VALUES (MEM_SEQ.NEXTVAL, '김도윤', 'DoyoonKim@hanbit.co.kr', 10);
COMMIT;
INSERT INTO ARTICLE(id, title, description, created, updated, member_id) VALUES (art_seq.NEXTVAL, '첫 번째 제목', '첫 번째 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO ARTICLE(id, title, description, created, updated, member_id) VALUES (art_seq.NEXTVAL, '두 번째 제목', '두 번째 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
INSERT INTO ARTICLE(id, title, description, created, updated, member_id) VALUES (art_seq.NEXTVAL, '세 번째 제목', '세 번째 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);
INSERT INTO ARTICLE(id, title, description, created, updated, member_id) VALUES (art_seq.NEXTVAL, '네 번째 제목', '네 번째 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);
COMMIT;