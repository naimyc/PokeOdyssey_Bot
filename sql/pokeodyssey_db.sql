create database pokeodyssey_db;

create table mc_users (
        user_id int NOT NULL UNIQUE,
        discord_id int NOT NULL UNIQUE,
        xuuid int NOT NULL UNIQUE,
        mc_edition int NOT NULL,
        
        balance DECIMAL DEFAULT 0.0,
        
        name varchar(25),
        surname varchar(25),
        discord_name varchar(25) NOT NULL,
        mcpe_name varchar(25),
        mc_name varchar(25),
        
        email varchar(50) UNIQUE,
        password varchar(50) UNIQUE,
        
        isBanned TINYINT (1) DEFAULT 0,
        isMuted TINYINT (1) DEFAULT 0,
        isRegistered TINYINT (1) DEFAULT 0,         
        isActive TINYINT (1) DEFAULT 1,
        
        registerDate DATETIME DEFAULT NOW(),

        PRIMARY KEY (user_id)
    );

INSERT INTO mc_users 
(user_id, xuuid, mc_edition, discord_name)
SELECT IFNULL(MAX(user_id), 0) + 1,
     545645644, 0, "Byakko"
FROM mc_users;
