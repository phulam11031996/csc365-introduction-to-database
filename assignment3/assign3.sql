-- 1. On what date(s) did Cal Poly score the most goals?
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.hTeam AS team,
    Game.hGoals AS goals,
    Game.date
FROM
    Game
WHERE
    Game.hTeam = 'Cal Poly'
UNION
SELECT
    Game.aTeam AS team,
    Game.aGoals AS goals,
    Game.date
FROM
    Game
WHERE
    Game.aTeam = 'Cal Poly';

SELECT
    T1.date
FROM
    T1
WHERE
    T1.team = 'Cal Poly'
    AND T1.goals >= (
        SELECT
            max(T1.goals)
        FROM
            T1
    );

-- 2. Which team has scored the most total goals?
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.hTeam AS team,
    Game.hGoals AS goal
FROM
    Game
UNION
SELECT
    Game.aTeam AS team,
    Game.aGoals AS goal
FROM
    Game;

SELECT
    T1.team
FROM
    T1
WHERE
    T1.goal >= (
        SELECT
            T1.goal AS goal
        FROM
            T1
    );

-- 3. How many wins does Cal Poly have?
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.hTeam AS team
FROM
    Game
WHERE
    Game.hGoals > Game.aGoals
    AND Game.hTeam = 'Cal Poly'
UNION
ALL
SELECT
    Game.aTeam AS team
FROM
    Game
WHERE
    Game.aGoals > Game.hGoals
    AND Game.aTeam = 'Cal Poly';

SELECT
    count(*)
FROM
    T1;

-- 4. Who is the top scorer for Cal Poly?
SELECT
    Participate.spID
FROM
    Participate
WHERE
    Participate.spTeam = 'Cal Poly'
    AND Participate.goals >= ALL (
        SELECT
            Participate.goals AS goals
        FROM
            Participate
        WHERE
            Participate.spTeam = 'Cal Poly'
    );

-- 5. In which games (print all information from Game table) did Cal Poly use exactly 14 players?
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.id AS gameID
FROM
    Game
WHERE
    Game.hTeam = 'Cal Poly'
    OR Game.aTeam = 'Cal Poly';

DROP VIEW IF EXISTS T2;

CREATE VIEW T2 AS
SELECT
    Participate.gameID,
    count(*) AS playerParticipated
FROM
    Participate NATURAL
    JOIN T1
WHERE
    Participate.spTeam = 'Cal Poly'
GROUP BY
    Participate.gameID
HAVING
    playerParticipated = 14;

SELECT
    *
FROM
    T2 NATURAL
    JOIN Game
WHERE
    Game.hTeam = 'Cal Poly'
    OR Game.aTeam = 'Cal Poly';

-- 6. What are the names of Cal Poly soccer players that have played in every single Cal Poly game?
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.id AS gameID
FROM
    Game
WHERE
    Game.hTeam = 'Cal Poly'
    OR Game.aTeam = 'Cal Poly';

SELECT
    Participate.spID,
    count(DISTINCT Participate.gameID) AS gameParticipated
FROM
    Participate
WHERE
    Participate.spTeam = 'Cal Poly'
GROUP BY
    Participate.spID
HAVING
    gameParticipated = (
        SELECT
            count(*) AS gameParticipated
        FROM
            T1
    );

-- 7. Print the names of all Cal Poly players sorted by the number of goals that they have scored.
SELECT
    SoccerPlayer.id,
    SoccerPlayer.name,
    sum(goals) AS goalScores
FROM
    SoccerPlayer NATURAL
    JOIN Participate
WHERE
    SoccerPlayer.tName = 'Cal Poly'
GROUP BY
    SoccerPlayer.id,
    SoccerPlayer.name
ORDER BY
    goalScores DESC;

-- 8. Print the names of Cal Poly soccer players who have played only in games where Cal Poly has not allowed goals.
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.id AS gameID
FROM
    Game
WHERE
    Game.hTeam = 'Cal Poly'
    AND hGoals = 0
UNION
SELECT
    Game.id AS gameID
FROM
    Game
WHERE
    Game.aTeam = 'Cal Poly'
    AND aGoals = 0;

SELECT
    Participate.spID,
    SoccerPlayer.name
FROM
    Participate NATURAL
    JOIN T1,
    SoccerPlayer
WHERE
    Participate.spTeam = 'Cal Poly'
    AND Participate.spID = SoccerPlayer.id;

-- 9. For every team, print the number of games that they have played. Include teams that have played no games.
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS
SELECT
    Game.hTeam AS name,
    Game.id
FROM
    Game
UNION
ALL
SELECT
    Game.aTeam AS name,
    Game.id
FROM
    Game;

SELECT
    Team.name,
    count(T1.id)
FROM
    T1
    RIGHT OUTER JOIN Team ON T1.name = Team.name
GROUP BY
    Team.name;