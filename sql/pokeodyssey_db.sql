create database pokeodyssey_db;

create table
    mc_users (
        user_id int AUTO_INCREMENT,
        xuuid int NOT NULL UNIQUE,
        mc_edition int NOT NULL,

        balance DECIMAL DEFAULT 0.0,
        
        name varchar(25),
        surname varchar(25),
        discord_name varchar(25) NOT NULL,
        mcpe_name varchar(25),
        mc_name varchar(25),
        email varchar(50) UNIQUE,
        
        isBanned TINYINT (1) DEFAULT 0,
        isMuted TINYINT (1) DEFAULT 0,
        isRegistered TINYINT (1) DEFAULT 0,
        isActive TINYINT (1) DEFAULT 1,
        
        registerDate DATETIME NOT NULL DEFAULT NOW (),
        PRIMARY KEY (user_id)
    );

INSERT INTO
    mc_users (xuuid, mc_edition, discord_name)
VALUES
    (545645644, 0, "Byakko");