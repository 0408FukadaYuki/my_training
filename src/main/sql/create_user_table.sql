CREATE TABLE
    'user' (
        'id' int NOT NULL AUTO_INCREMENT PRIMARY KEY,
        'user_id' VARCHAR(15) NOT NULL,
        'name' VARCHAR(30) NOT NULL,
        'mail' VARCHAR(50) NOT NULL,
        'profile' VARCHAR(100),
        'birth_date' DATE,
        'icon_image' BLOB,
        'password' CHAR(64),
    );