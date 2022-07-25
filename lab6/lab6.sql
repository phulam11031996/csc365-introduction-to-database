-- lab 6. write the following queries in sql. 
-- q1. print the number of credit cards that john smith owns.
SELECT
    card.cardnumber
FROM
    card NATURAL
    JOIN customer NATURAL
    JOIN ownership
WHERE
    customer.customernameon = 'taylor hudson';

-- q2. for every customer, print their name and the total (in dollars) of all their purchases.
SELECT
    customer.customername,
    customer.ssn,
    sum(transaction.amount)
FROM
    customer NATURAL
    JOIN transaction
GROUP BY
    customer.ssn,
    customer.customername;

-- q3. print the name of the vendor with the highest single transaction.
SELECT
    vender.venderid,
    vender.vendername,
    transaction.amount
FROM
    vender NATURAL
    JOIN transaction
WHERE
    transaction.amount >= any (
        SELECT
            transaction.amount
        FROM
            transaction
    );

-- q4. print the name of vendor with the highest total proceeds from transactions.
-- ask why is this invalid
-- select
--     vender.venderid,
--     vender.vendername,
--     count(amount) as numbertransaction
-- from
--     vender natural
--     join transaction
-- where
--     numbertransaction >= (
--         select
--             count(amount) as numbertransaction
--         from
--             vender natural
--             join transaction
--         group by
--             vender.venderid,
--             vender.vendername
--     )
-- group by
--     vender.venderid,
--     vender.vendername;
SELECT
    vender.venderid,
    vender.vendername,
    count(amount) AS numbertransaction
FROM
    vender NATURAL
    JOIN transaction
GROUP BY
    vender.venderid,
    vender.vendername
HAVING
    numbertransaction >= ALL (
        SELECT
            count(*) AS numbertransaction
        FROM
            vender NATURAL
            JOIN transaction
        GROUP BY
            vender.venderid,
            vender.vendername
    );

-- q5. create an additional table that stores the different credit card types (e.g., visa, mc, discovery, etc.). then write a query that prints the names of all customers that own every single credit card type.
CREATE VIEW alltypeofcard AS (
    SELECT
        DISTINCT card.cardtype
    FROM
        card
);

SELECT
    customer.ssn,
    customer.customername,
    count(DISTINCT card.cardtype) AS differentcardtype
FROM
    customer NATURAL
    JOIN ownership NATURAL
    JOIN card
GROUP BY
    customer.ssn,
    customer.customername
HAVING
    differentcardtype >= (
        SELECT
            count(*) AS differentcardtype
        FROM
            alltypeofcard
    );

DROP VIEW alltypeofcard;

-- q6. what is the name of the customers that has the highest credit card balance (sum the credit card balances of all their cards)?
-- ask professor how to update the balance
SELECT
    customer.ssn,
    customer.customername,
    sum(card.balance) totalbalance
FROM
    customer NATURAL
    JOIN ownership NATURAL
    JOIN card
GROUP BY
    customer.ssn,
    customer.customername
HAVING
    totalbalance >= ALL (
        SELECT
            sum(card.balance)
        FROM
            customer NATURAL
            JOIN ownership NATURAL
            JOIN card
        GROUP BY
            customer.ssn,
            customer.customername
    );

-- q7. print the transaction with the highest amount.
SELECT
    *
FROM
    transaction
WHERE
    transaction.amount = (
        SELECT
            max(transaction.amount) AS amount
        FROM
            transaction
    );

-- q8. for every customer, print the amount of their latest payment.
DROP VIEW IF EXISTS latestPaymentDate;

CREATE VIEW latestPaymentDate AS (
    SELECT
        customer.ssn,
        customer.customerName,
        max(payment.payDate) AS payDate
    FROM
        customer NATURAL
        JOIN payment NATURAL
        JOIN transaction
    GROUP BY
        customer.ssn,
        customer.customerName
);

SELECT
    DISTINCT customer.ssn,
    customer.customerName,
    payment.payDate
FROM
    customer NATURAL
    JOIN payment NATURAL
    JOIN transaction
WHERE
    EXISTS (
        SELECT
            *
        FROM
            latestPaymentDate
        WHERE
            customer.ssn = latestPaymentDate.ssn
            AND payment.payDate = latestPaymentDate.payDate
    );

-- Q9. WHAT ARE THE TOTAL SALES FOR EACH CARD TYPE (I.E., TOTAL SALES FOR VISA, MC, ETC.)
SELECT
    card.cardType,
    sum(amount) AS totalSale
FROM
    card NATURAL
    JOIN transaction
GROUP BY
    card.cardType;

-- Q10. FOR 2014, PRINT THE TOTAL SALES FOR EACH MONTH. THE FUNCTION YEAR(DATE) WILL GIVE YOU THE YEAR OF A DATE AND THE FUNCTION MONTH(DATE) WILL GIVE YOU THE MONTH.  YOU CAN USE BOTH FUNCTIONS INSIDE SQL, E.G., YOU CAN GROUP BY MONTH(DATE).
SELECT
    YEAR(transaction.date),
    MONTH(transaction.date),
    sum(transaction.amount)
FROM
    transaction
GROUP BY
    YEAR(transaction.date),
    MONTH(transaction.date);

-- ask which one is faster cross product or join
-- ask how is trigger going to be tested on the final
-- ask about the presentation.