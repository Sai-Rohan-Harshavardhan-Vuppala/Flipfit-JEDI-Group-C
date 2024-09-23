DROP DATABASE FlipFitDatabase;

CREATE DATABASE FlipFitDatabase;

USE FlipFitDatabase;

CREATE TABLE FlipFitRoles (
    roleId INT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE FlipFitUsers (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    roleId INT,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (roleId) REFERENCES FlipFitRoles(roleId) ON DELETE SET NULL
);

CREATE TABLE FlipFitCustomers (
    customerId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT,
    phone VARCHAR(15),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES FlipFitUsers(userId) ON DELETE CASCADE
);

CREATE TABLE FlipFitAdmins (
    adminId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT,
    FOREIGN KEY (userId) REFERENCES FlipFitUsers(userId) ON DELETE CASCADE
);

CREATE TABLE FlipFitGymOwners (
    gymOwnerId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT,
    accountNumber VARCHAR(50) NOT NULL,
    FOREIGN KEY (userId) REFERENCES FlipFitUsers(userId) ON DELETE CASCADE
);

CREATE TABLE FlipFitGyms (
    gymId INT PRIMARY KEY AUTO_INCREMENT,
    gymOwnerId INT,
    gymName VARCHAR(100) NOT NULL,
    gymCity VARCHAR(100) NOT NULL,
    gymArea VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (gymOwnerId) REFERENCES FlipFitGymOwners(gymOwnerId) ON DELETE CASCADE
);

CREATE TABLE FlipFitSlots (
    slotId INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL,
    gymId INT,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL,
    seatsAvailable INT NOT NULL,
    totalSeats INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,  
    slotDate DATE NOT NULL, 
    FOREIGN KEY (gymId) REFERENCES FlipFitGyms(gymId) ON DELETE CASCADE 
);

create table FlipFitPayments (
	paymentId int primary key auto_increment,
    transactionId varchar(20) UNIQUE not null,
	customerId int not null,
	foreign key (customerId) references FlipFitCustomers(customerId)
);

CREATE TABLE FlipFitBookings(
    bookingId INT PRIMARY KEY AUTO_INCREMENT,
    customerId INT,
    slotId INT,
    paymentId INT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES FlipFitCustomers(customerId) ON DELETE CASCADE,
    FOREIGN KEY (slotId) REFERENCES FlipFitSlots(slotId) ON DELETE CASCADE,
    FOREIGN KEY (paymentId) REFERENCES FlipFitPayments(paymentId) ON DELETE CASCADE
);

CREATE TABLE FlipFitNotifications (
    notificationId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT,
    message TEXT NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES FlipFitUsers(userId) ON DELETE CASCADE
);

INSERT INTO FlipFitRoles (roleName) VALUES ("Admin");
INSERT INTO FlipFitRoles (roleName) VALUES ("Customer");
INSERT INTO FlipFitRoles (roleName) VALUES ("GymOwner");

INSERT INTO FlipFitUsers (username, password, email, name, roleId, status) VALUES ("harshavardhan", "harsh@123", "harsha@gmail.com", "Harshavardhan", 1, "whitelisted");
INSERT INTO FlipFitAdmins (userId) VALUES(1);
INSERT INTO FlipFitUsers (username, password, email, name, roleId, status) VALUES ("sairohan", "mypass", "sairohan2812@gmail.com", "Sai Rohan Harshavardhan Vuppala", 2, "whitelisted");
INSERT INTO FlipFitCustomers (userId, phone) VALUES (2, "7075014903");
INSERT INTO FlipFitUsers (username, password, email, name, roleId, status) VALUES ("sankalp", "sankalp@123", "sankalpg38@gmail.com", "Sankalp Garg", 3, "whitelisted");
INSERT INTO FlipFitGymOwners (userId, accountNumber) VALUES (3, "IDBI012345600K023");
INSERT INTO FlipFitUsers (username, password, email, name, roleId, status) VALUES ("sai", "pass", "sai@gmail.com", "Sai", 2, "whitelisted");
INSERT INTO FlipFitCustomers (userId, phone) VALUES (4, "8522938243");

INSERT INTO FlipFitGyms (gymName, gymOwnerId, gymCity, gymArea, status) VALUES ("Sankalp Gym 1", 1, "Bangalore", "Bellandur", "approved");
INSERT INTO FlipFitGyms (gymName, gymOwnerId, gymCity, gymArea, status) VALUES ("Sankalp Gym 2", 1, "Bangalore", "HSR Layout", "approved");
INSERT INTO FlipFitSlots (gymId, slotDate, startTime, endTime, seatsAvailable, totalSeats, price, status) VALUES (1, "2024-09-24", "14:30:00", "16:29:00", 30, 30, 100, "approved");
INSERT INTO FlipFitSlots (gymId, slotDate, startTime, endTime, seatsAvailable, totalSeats, price, status) VALUES (1, "2024-09-24", "12:30:00", "14:29:00", 30, 30, 100, "approved");
INSERT INTO FlipFitSlots (gymId, slotDate, startTime, endTime, seatsAvailable, totalSeats, price, status) VALUES (2, "2024-09-24", "14:30:00", "16:29:00", 30, 30, 100, "approved");
INSERT INTO FlipFitSlots (gymId, slotDate, startTime, endTime, seatsAvailable, totalSeats, price, status) VALUES (2, "2024-09-24", "16:30:00", "18:29:00", 1, 1, 100, "approved");
INSERT INTO FlipFitPayments (transactionId, customerId) VALUES ("T07123456", 1);
INSERT INTO FlipFitBookings (customerId, slotId, paymentId, status) VALUES (1, 4, 1, "confirmed");
UPDATE FlipFitSlots SET seatsAvailable = seatsAvailable - 1 WHERE slotId = 4;
INSERT INTO FlipFitPayments (transactionId, customerId) VALUES ("T07123458", 2);
INSERT INTO FlipFitBookings (customerId, slotId, paymentId, status) VALUES (2, 4, 2, "waitlisted");