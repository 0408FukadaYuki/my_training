INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'suzuki_tarou', '鈴木 太郎', 'tanaka@example.com', 'おはよう', '1985-05-15', '', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('223e4567-e89b-12d3-a456-426614174001', 'matuda_hanako', '松田 花子', 'suzuki@example.com', 'こんにちは', '1990-08-10', '', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('323e4567-e89b-12d3-a456-426614174002', 'toyota_jirou', '豊田 次郎', 'sato@example.com', 'こんばんわ', '1988-03-20', '', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

INSERT INTO users (uuid, user_id, name, mail, profile, birth_date, icon_image, pass_word)
VALUES ('423e4567-e89b-12d3-a456-426614174003', 'fukada_yuki', '深田 優希', 'fukada@example.com', '初めまして', '1997-04-08', '', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('323e4567-e89b-12d3-a456-426614174002', '初めての投稿です。よろしくお願いします。', NULL, '2025-09-08 13:15:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('223e4567-e89b-12d3-a456-426614174001', '2回目の投稿です。', NULL, '2025-09-08 13:20:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('123e4567-e89b-12d3-a456-426614174000', '3回目の投稿です。', NULL, '2025-09-08 13:25:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('423e4567-e89b-12d3-a456-426614174003', 'みなさん、はじめまして！', NULL, '2025-09-08 13:30:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('223e4567-e89b-12d3-a456-426614174001', '楽しい話題を投稿します。', NULL, '2025-09-08 13:35:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('323e4567-e89b-12d3-a456-426614174002', '質問です、教えてください。', NULL, '2025-09-08 13:40:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('123e4567-e89b-12d3-a456-426614174000', '投稿続けていきます。', NULL, '2025-09-08 13:45:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('423e4567-e89b-12d3-a456-426614174003', '皆さんの投稿楽しみにしています。', NULL, '2025-09-08 13:50:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('223e4567-e89b-12d3-a456-426614174001', '返信待ってます。', NULL, '2025-09-08 13:55:00');

INSERT INTO posts (user_id, content, reply_to, created_at)
VALUES ('323e4567-e89b-12d3-a456-426614174002', 'これからよろしくお願いします。', NULL, '2025-09-08 14:00:00');


INSERT INTO favorites (user_id, post_id, created_at) 
VALUES('123e4567-e89b-12d3-a456-426614174000', 2, '2025-09-08 14:10:00' )