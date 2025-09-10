INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'test_tarou', 'テスト 太郎', 'tarou@example.com', 'テストです', '1985-05-15', '', 'ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('223e4567-e89b-12d3-a456-426614174001', 'test_hanako', 'テスト 花子', 'hanako@example.com', 'テストです', '1990-08-10', '', 'ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('323e4567-e89b-12d3-a456-426614174002', 'test_jirou', 'テスト 次郎', 'jirou@example.com', 'テストです', '1988-03-20', '', 'ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'これはJUnitのテストです', NULL, '2025-09-08 13:00:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('223e4567-e89b-12d3-a456-426614174001', 'これはJUnitのテストです。', NULL, '2025-09-08 13:05:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'これはJUnitのテストです。2回目の投稿です。', NULL, '2025-09-08 13:10:00');

INSERT INTO favorites (user_id, post_id, created_at) 
VALUES('123e4567-e89b-12d3-a456-426614174000', 2, '2025-09-08 14:10:00' )