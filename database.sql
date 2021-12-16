create table user
(
    username varchar(20),
    pass varchar(20),
    primary key(username)
);

create table check_out
(
    username varchar(20),
    order_number numeric(20),
    order_id varchar(20),
    billing_info varchar(20),
    shipping_info varchar(20),
    primary key(username, order_number),
    foreign key(order_number) references user,
    foreign key(username) references user
);

create table bookstore
(
    order_number numeric(20),
    warehouse_name varchar(20),
    primary key(order_number)
);

create table update_order
(
    order_number numeric(20),
    warehouse_name varchar(20),
    primary key(order_number,warehouse_name),
    foreign key(order_number) references check_out,
    foreign key(email) references store_owner
);

create table warehouse
(
    warehouse_name varchar(20),
    primary key(warehouse_name)
);

create table update_stock
(
    order_number numeric(20),
    email varchar(20) ,
    primary key(order_number, email),
    foreign key(order_number) references check_out,
    foreign key(email) references store_owner
);

create table request_stock
(
    warehouse_name varchar(20),
    email varchar(20),
    primary key(warehouse_name,email),
    foreign key(warehouse_name) references warehouse,
    foreign key(email) references store_owner
);

create table store_owner
(
    email varchar(20),
    username varchar(20),
    pass varchar(20),
    primary key(email)
);

create table store_pub
(
    publisher_address varchar(20),
    order_number numeric(20),
    primary key(store_address,order_number),
    foreign key(publisher_address) references publisher,
    foreign key(order_number) references bookstore
);

create table publisher
(
    publisher_address varchar(20),
    book varchar(20),
    publisher_name varchar(20),
    phone_number numeric(20),
    primary key(publisher_address)
);

create table bank_account
(
    publisher_address varchar(20),
    id varchar(20),
    primary key(bank_address, id),
    foreign key(id) references bank,
    foreign key(publisher_address) references publisher
);

create table store_book
(
    ISBN varchar(20),
    order_number numeric(20),
    primary key(ISBN, order_number),
    foreign key(ISBN) references book,
    foreign key(order_number) references bookstore
);

create table book
(
    ISBN varchar(20)
    publisher varchar(20)
    genre varchar(20)
    title varchar(20)
    pages numeric(20)
    stock numeric(20)
    author varchar(20)
    prices numeric(5,2)
    unit_cost numeric(5,2)
    primary key(ISBN)
);

create table bank
(
    id varchar(20)
    primary key(id)
);
