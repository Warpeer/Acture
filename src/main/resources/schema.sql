CREATE TABLE ContactMessage
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message LONGTEXT NOT NULL,
    received DATETIME NOT NULL DEFAULT(current_time),
    messenger INTEGER,
    messageStatus VARCHAR(10) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE Employee
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    hired DATE NOT NULL DEFAULT(current_date),
    PRIMARY KEY (id)
);
CREATE TABLE EmployeePFP
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    image_data BLOB NOT NULL,
    file_type VARCHAR(255) NOT NULL,
    eId INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (eId) REFERENCES Employee(id)
);
CREATE TABLE Customer
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    dateStarted DATETIME NOT NULL default(current_date),
    messenger INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (messenger) REFERENCES Employee(id)
);