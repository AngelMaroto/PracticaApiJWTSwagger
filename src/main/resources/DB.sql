drop database if exists ejemplojwt;
Create database ejemplojwt;
use ejemplojwt;
CREATE TABLE  users (
                       id integer auto_increment PRIMARY KEY,
                       username VARCHAR(50) NOT NULL ,
                       password VARCHAR(64) NOT NULL
);

INSERT INTO users  VALUES (1, 'juan@gmail.com', SHA2('juan',256));
