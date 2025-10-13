CREATE TABLE
    `users` (
        `uuid` CHAR(36) NOT NULL PRIMARY KEY,
        `user_id` VARCHAR(15) NOT NULL UNIQUE,
        `name` VARCHAR(30) NOT NULL,
        `mail` VARCHAR(50) NOT NULL,
        `profile` VARCHAR(100),
        `birth_date` DATE,
        `icon_image` BLOB,
        `password` CHAR(64),
    );
