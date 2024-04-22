CREATE TABLE users (
       username VARCHAR(50) COLLATE utf8mb4_general_ci NOT NULL PRIMARY KEY,
       password VARCHAR(500) COLLATE utf8mb4_general_ci NOT NULL,
       enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
     username VARCHAR(50) COLLATE utf8mb4_general_ci NOT NULL,
     authority VARCHAR(50) COLLATE utf8mb4_general_ci NOT NULL,
     CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

insert into users values ('happy','1234',1);
insert into authorities values ('happy','write');

