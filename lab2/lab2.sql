CREATE TABLE customer (
    ssn VARCHAR(7), 
    customerName VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (ssn)
);

CREATE TABLE card (
    cardNumber VARCHAR(16) UNIQUE,
    creditLimit DOUBLE(15, 2) DEFAULT 1000.00,
    balance DOUBLE(15, 2) DEFAULT 0,
    isActive BOOL DEFAULT 0,
    cardType ENUM("VISA", "MC", "AMERICAN", "EXPRESS", "DISCOVER"),
    PRIMARY KEY (cardNumber)
);

CREATE TABLE vender (
    venderId INTEGER AUTO_INCREMENT,
    venderName VARCHAR(255),
    location VARCHAR(255),
    PRIMARY KEY (venderId)
);

CREATE TABLE ownership (
    ssn VARCHAR(7),
    cardNumber VARCHAR(16),
    PRIMARY KEY (ssn, cardNumber),
    FOREIGN KEY (ssn) REFERENCES customer(ssn),
    FOREIGN KEY (cardNumber) REFERENCES card(cardNumber)
);

CREATE TABLE payment (
    paymentId INTEGER AUTO_INCREMENT,
    payDate DATE,
    cardNumber VARCHAR(16),
    PRIMARY KEY (paymentId),
    FOREIGN KEY (cardNumber) REFERENCES card(cardNumber)
);

CREATE TABLE transaction (
    transactionId INTEGER AUTO_INCREMENT,
	venderId INTEGER,
    ssn VARCHAR(7),
    cardNumber VARCHAR(16),
    amount DOUBLE UNSIGNED NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY(transactionId),
	FOREIGN KEY (venderId) REFERENCES vender(venderId),
    FOREIGN KEY (ssn, cardNumber) REFERENCES ownership(ssn, cardNumber)
);
