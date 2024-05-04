create table authorities(
    id int not null auto_increment,
    customer_id int not null,
    name varchar(50) not null,
    primary key (id),
    key customer_id (customer_id),
    constraint authorities_ibfk_1 foreign key (customer_id) references customer (customer_id)
);

-- Old
insert into authorities (customer_id, name) values (1, 'VIEWACCOUNT');
insert into authorities (customer_id, name) values (1, 'VIEWCARDS');
insert into authorities (customer_id, name) values (1, 'VIEWBALANCE');
insert into authorities (customer_id, name) values (1, 'VIEWLOAN');

-- New
insert into authorities (customer_id, name) values (1, 'ROLE_USER');
insert into authorities (customer_id, name) values (1, 'ROLE_ADMIN');
