INSERT INTO member(name, email, password)
VALUES('홍길동', 'hong@gmail.com', '$2a$12$o74PfUxzFV9m.dquw7WgKOIgrOeUhb6g3IR23.tG3qrEvCCjzJr4u');

INSERT INTO member(name, email, password)
VALUES('이몽룡', 'lee@gmail.com', '$2a$12$o74PfUxzFV9m.dquw7WgKOIgrOeUhb6g3IR23.tG3qrEvCCjzJr4u');

INSERT INTO member(name, email, password)
VALUES('이순신', 'leeshin@gmail.com', '$2a$12$o74PfUxzFV9m.dquw7WgKOIgrOeUhb6g3IR23.tG3qrEvCCjzJr4u');

INSERT INTO member(name, email, password)
VALUES('성춘향', 'seong@gmail.com', '$2a$12$o74PfUxzFV9m.dquw7WgKOIgrOeUhb6g3IR23.tG3qrEvCCjzJr4u');

INSERT INTO authority(authority, member_id) VALUES('ROLE_ADMIN', 2);

INSERT INTO article(title, description, created, updated, member_id) VALUES
('첫 번째 게시글 제목', '첫 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO article(title, description, created, updated, member_id) VALUES
('두 번째 게시글 제목', '두 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO article(title, description, created, updated, member_id) VALUES
('세 번째 게시글 제목', '세 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

INSERT INTO article(title, description, created, updated, member_id) VALUES
('네 번째 게시글 제목', '네 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);
