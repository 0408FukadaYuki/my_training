INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'suzuki_tarou', '鈴木 太郎', 'tanaka@example.com', 'おはよう', '1985-05-15', '', 'ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('223e4567-e89b-12d3-a456-426614174001', 'matuda_hanako', '松田 花子', 'suzuki@example.com', 'こんにちは', '1990-08-10', '', 'ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('323e4567-e89b-12d3-a456-426614174002', 'toyota_jirou', '豊田 次郎', 'sato@example.com', 'こんばんわ', '1988-03-20', '', 'ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046');

INSERT INTO posts (id, user_id, content, reply_to, created_at)
VALUES (1, '123e4567-e89b-12d3-a456-426614174000', '初めての投稿です。よろしくお願いします。', NULL, '2025-09-08 13:00:00');

INSERT INTO posts (id, user_id, content, reply_to, created_at)
VALUES (2, '223e4567-e89b-12d3-a456-426614174001', '初めての投稿です。よろしくお願いします。', NULL, '2025-09-08 13:05:00');

INSERT INTO posts (id, user_id, content, reply_to, created_at)
VALUES (3, '123e4567-e89b-12d3-a456-426614174000', '2回目の投稿です。', NULL, '2025-09-08 13:10:00');

INSERT INTO favorites (user_id, post_id, created_at) 
VALUES('123e4567-e89b-12d3-a456-426614174000', 2, '2025-09-08 14:10:00' )