-- lab7
-- Q1. Show the 10 customers with the highest single transaction amount.
SELECT
    customer.ssn,
    customer.customerName,
    max(transaction.amount) AS highestSingleTransaction
FROM
    customer NATURAL
    JOIN transaction
GROUP BY
    customer.ssn,
    customer.customerName
ORDER BY
    highestSingleTransaction DESC
LIMIT
    10;

-- Q2. Show all customers ordered by the total amount of all their transactions. Show the name of the customer and the total. Start with the customer with the highest total.
SELECT
    customer.ssn,
    customer.customerName,
    sum(transaction.amount) AS totalAmount
FROM
    customer NATURAL
    JOIN transaction
GROUP BY
    customer.ssn,
    customer.customerName
ORDER BY
    totalAmount DESC;

-- Q3. Show vendors that have transactions with every single credit card.
DROP VIEW IF EXISTS everyCard;

CREATE VIEW everyCard AS (
    SELECT
        card.cardNumber
    FROM
        card
);

SELECT
    vender.venderId,
    vender.venderName,
    count(DISTINCT transaction.cardNumber) AS cards
FROM
    vender NATURAL
    JOIN transaction
GROUP BY
    vender.venderId,
    vender.venderName
HAVING
    cards = (
        SELECT
            count(*) AS cards
        FROM
            everyCard
    );

-- Q4. Show vendors that have transactions with every single credit card type.
-- ASK PROFESSOR HOW TO JOIN THE 2 TABLES
DROP VIEW IF EXISTS everyCardType;

CREATE VIEW everyCardType AS (
    SELECT
        DISTINCT card.cardType
    FROM
        card
);

SELECT
    *
FROM
    everyCardType;

SELECT
    transaction.venderId,
    count(DISTINCT card.cardType) AS cards
FROM
    transaction NATURAL
    JOIN card
GROUP BY
    transaction.venderId
HAVING
    cards = (
        SELECT
            count(*) AS cards
        FROM
            everyCardType
    );

-- Q5. Print the 5 richest vendors. These are the vendors that have made the most money from transactions. Start with the richest vendor.
SELECT
    vender.venderId,
    vender.venderName,
    sum(transaction.amount) AS totalAmount
FROM
    vender NATURAL
    JOIN transaction
GROUP BY
    vender.venderId,
    vender.venderName
ORDER BY
    totalAmount DESC
LIMIT
    5;

-- Q6. Print the names of vendors that have more than 1000 transactions in a single month. Remember that you call the methods month and year on a date and you can group by these values.
DROP VIEW IF EXISTS T1;

CREATE VIEW T1 AS(
    SELECT
        transaction.venderId,
        YEAR(transaction.date),
        MONTH(transaction.date),
        count(*) AS transactionCount
    FROM
        transaction
    GROUP BY
        transaction.venderId,
        YEAR(transaction.date),
        MONTH(transaction.date)
    HAVING
        transactionCount >= 1000
);

SELECT
    T1.venderId
FROM
    T1;

-- Q7. Print the names of customers that own more than 10 credit cards.
SELECT
    customer.ssn,
    customer.customerName,
    count(*) AS cards
FROM
    customer NATURAL
    JOIN ownership NATURAL
    JOIN card
GROUP BY
    customer.ssn,
    customer.customerName
HAVING
    cards >= 10;

-- Q8. Print the names of customers that have posted a payment every single month in 2014. You may need to create a table that contains all the month numbers.
-- ASK PROFESSOR HOW TO JOIN THE 2 TABLES OR ASK ABOUT HIS SOLUTION
DROP TABLE IF EXISTS months;

CREATE TABLE months (months int UNIQUE, PRIMARY KEY (months));

INSERT INTO
    months (months)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10),
    (11),
    (12);

CREATE VIEW temp AS
SELECT
    DISTINCT customer.ssn,
    customer.customerName,
    MONTH(payment.payDate) AS months
FROM
    customer NATURAL
    JOIN ownership NATURAL
    JOIN payment
WHERE
    year(payment.payDate) = 2014;

SELECT
    customerName,
    ssn AS total
FROM
    temp
GROUP BY
    temp.customerName,
    temp.ssn
HAVING
    count(months) = 12;

-- Q9. Print the names of customers that have posted more than one payment in a single month.
SELECT
    customer.ssn,
    customer.customerName,
    year(payment.payDate) AS years,
    MONTH(payment.payDate) AS months,
    count(payment.paymentId) AS numberOfPayment
FROM
    customer NATURAL
    JOIN ownership NATURAL
    JOIN payment
GROUP BY
    customer.ssn,
    customer.customerName,
    year(payment.payDate),
    MONTH(payment.payDate)
HAVING
    numberOfPayment > 1;

-- Q10. Print the names of customer that have a month without a payment in 2014.
DROP VIEW IF EXISTS T2;

SELECT
    customer.ssn,
    customer.customerName,
    YEAR(payment.payDate) AS years,
    count(DISTINCT MONTH(payment.payDate)) AS months
FROM
    customer NATURAL
    JOIN ownership NATURAL
    JOIN payment
WHERE
    year(payment.payDate) = 2014
GROUP BY
    customer.ssn,
    customer.customerName,
    YEAR(payment.payDate)
HAVING
    months < 12;

-- ask about natural join and dot product
-- ask about 2pl and s2pl
-- it still legal but ...
