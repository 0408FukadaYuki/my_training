CREATE TABLE
    'post' (
        'id' int NOT NULL AUTO_INCREMENT PRIMARY KEY,
        'user_id' VARCHAR(15) NOT NULL,
        'content' VARCHAR(140) NOT NULL,
        'reply_to' int ,
        'created_at' DATETIME,
        FOREIGN KEY(user_id) REFERENCES user(id) 
    );