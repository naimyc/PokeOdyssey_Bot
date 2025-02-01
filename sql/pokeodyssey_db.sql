CREATE DATABASE pokeodyssey_db;

CREATE TABLE alliances {
	allianceID INT UNIQUE,
	allianceName VARCHAR(30),
	
	mcServerIP VARCHAR(30),
	mcServerPort VARCHAR(30),
	
	membersCount INT,
	
	newsChannel VARCHAR(30),
	botChannel VARCHAR(30), 
	
	ownerID INT UNIQUE,
	ownerName VARCHAR(30),
	
	hasMinecraft TINYINT (1) DEFAULT 0,
	
	allianceDate DATETIME DEFAULT NOW()
}

CREATE TABLE pokeMembers (
        userID int NOT NULL UNIQUE,
        discordID VARCHAR(35) NOT NULL UNIQUE,
        xuuid VARCHAR(35) UNIQUE,
        mcEdition int NOT NULL,
        
        balance DECIMAL DEFAULT 0.0,
        
        name varchar(35),
        surname varchar(35),
        discordName varchar(35) NOT NULL,
        mcpeName varchar(35),
        mcName varchar(35),
        
        email varchar(50) UNIQUE,
        password varchar(50),
        
        isBanned TINYINT (1) DEFAULT 0,
        isMuted TINYINT (1) DEFAULT 0,
        isRegistered TINYINT (1) DEFAULT 0,         
        isActive TINYINT (1) DEFAULT 0,
        
        registerDate DATETIME DEFAULT NOW(),

        PRIMARY KEY (userID)
    );

INSERT INTO mc_users 
(userID, xuuid, mcEdition, discordName, discordID)
SELECT IFNULL(MAX(userID), 0) + 1, 545645644, 0, "Byakko", 9 FROM mc_users;
