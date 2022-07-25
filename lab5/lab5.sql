# Lab 5.
# Write the following queries in SQL. Run them against the database from the previous labs.
# 1. Print the SSN of John Smith.
SELECT
    customer.ssn
FROM
    customer
WHERE
    customer.customerName = 'Taylor Hudson';

# 2. Print the numbers of all the credit cards of John Smith.
SELECT
    ownership.cardNumber
FROM
    owndership NATURAL
    JOIN customer
WHERE
    customer.customerName = 'Taylor Hudson';

# 3. Print all the transaction information from January 1st, 2015 for credit card number 1236666.
SELECT
    *
FROM
    transaction
WHERE
    transaction.ssn = 1085734
    AND date > '2015-01-01';

# 4. Print the credit limit for all credit cards of Maria Johnson.
SELECT
    card.cardNumber,
    card.creditLimit
FROM
    customer NATURAL
    JOIN card NATURAL
    JOIN ownership
WHERE
    customer.customerName = 'Taylor Hudson';

# 5. Print the names of vendors who have transaction on January 2nd, 2015.
SELECT
    vender.venderName
FROM
    vender,
    transaction
WHERE
    vender.venderId = transaction.transactionId
    AND transaction.date = '2013-10-26';