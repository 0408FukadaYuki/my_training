CREATE TABLE
    `like` (
        `user_id` VARCHAR(15) NOT NULL,
        `post_id` int NOT NULL,
        `created_at` DATETIME,
        PRIMARY KEY (user_id, post_id),
        FOREIGN KEY (user_id) REFERENCES user (id),
        FOREIGN KEY (post_id) REFERENCES post (id),
    );
