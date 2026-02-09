drop database gogz;

create database gogz;
use gogz;
CREATE TABLE gogz.Persons (
                         PersonID int PRIMARY KEY,
                         LastName varchar(255),
                         FirstName varchar(255),
                         Address varchar(255),
                         City varchar(255)
);

CREATE TABLE gogz.Addresses (
                         AddressID int AUTO_INCREMENT PRIMARY KEY,
                         Street varchar(255),
                         City varchar(255),
                         State varchar(255),
                         ZipCode varchar(20),
                         PersonID int NOT NULL,
                         FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
);
