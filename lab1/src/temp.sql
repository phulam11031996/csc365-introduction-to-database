
CREATE TABLE Team (
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (name)
);

CREATE TABLE SoccerPlayer (
    spid INTEGER AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    teamName VARCHAR(255) NOT NULL,
    PRIMARY KEY (spid),
    FOREIGN KEY (teamName) REFERENCES Team(name)
);

CREATE TABLE Game (
    gid INTEGER AUTO_INCREMENT,
    firstTeam VARCHAR(255) NOT NULL,
    secondTeam VARCHAR(255) NOT NULL,
    firstScore INTEGER UNSIGNED NOT NULL,
    secondScore INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (gid),
    FOREIGN KEY (firstTeam) REFERENCES Team(name),
    FOREIGN KEY (secondTeam) REFERENCES Team(name)
);

CREATE TABLE Participate (
    pid INTEGER AUTO_INCREMENT,
    gid INTEGER NOT NULL,
    spid INTEGER NOT NULL,
    playerName VARCHAR(255) NOT NULL,
    goalsScored INTEGER DEFAULT 0,
    PRIMARY KEY (pid),
	FOREIGN KEY (gid) REFERENCES Game(gid),
	FOREIGN KEY (spid) REFERENCES SoccerPlayer(spid)
);

INSERT INTO Team (name) VALUES ("Spain");
INSERT INTO Team (name) VALUES ("Mexico");
INSERT INTO Team (name) VALUES ("USA");
INSERT INTO Team (name) VALUES ("Cal Poly");

INSERT INTO SoccerPlayer (name, teamName) VALUES ("Sergio Ramos", "Spain");
INSERT INTO SoccerPlayer (name, teamName) VALUES ("David de Gea", "Spain");
INSERT INTO SoccerPlayer (name, teamName) VALUES ("Gerard Moreno", "Spain");

INSERT INTO Game (firstTeam, secondTeam, firstScore, secondScore) VALUES ("Spain", "Mexico", 3, 2);
INSERT INTO Game (firstTeam, secondTeam, firstScore, secondScore) VALUES ("Spain", "USA", 3, 1);
INSERT INTO Game (firstTeam, secondTeam, firstScore, secondScore) VALUES ("Mexico", "USA", 1, 2);
INSERT INTO Game (firstTeam, secondTeam, firstScore, secondScore) VALUES ("Cal Poly", "USA", 4, 2);
INSERT INTO Game (firstTeam, secondTeam, firstScore, secondScore) VALUES ("Spain", "Cal Poly", 1, 2);

INSERT INTO Participate (gid, spid, playerName, goalsScored) VALUES (1, 3, "Gerard Moreno", 3);
INSERT INTO Participate (gid, spid, playerName, goalsScored) VALUES (2, 3, "Sergio Ramos", 1);
INSERT INTO Participate (gid, spid, playerName, goalsScored) VALUES (3, 3, "David de Gea", 3);

SELECT
    distinct playerName
FROM
    Participate
WHERE
    goalsScored >= 3;


SELECT
    distinct teamName
FROM
    Participate,
    SoccerPlayer
WHERE
    SoccerPlayer.name = Participate.playerName and goalsScored >= 3;

SELECT
    distinct Team.name
FROM
    Team,
    Game
WHERE
    (Game.firstTeam = Team.name and Game.firstScore > 3) or
    (Game.secondTeam = Team.name and Game.secondScore > 3);

SELECT
    distinct Team.name
FROM
    Game,
    Team
WHERE
    (Game.firstTeam = "Cal Poly" and Game.firstScore > Game.secondScore and Team.name = Game.secondTeam) or
    (Game.secondTeam = "Cal Poly" and Game.secondScore > Game.firstScore and Team.name = Game.firstTeam);


