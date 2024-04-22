CREATE TABLE customer(
    id int NOT NULL AUTO_INCREMENT,
    email varchar(45)NOT NULL,
    pwd varchar(200)NOT NULL,
    role varchar(45)NOT NULL,
    primary key (id)
);

insert into customer (email, pwd, role) values ('johndoe@example.com','54321','admin');