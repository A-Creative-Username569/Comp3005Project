CREATE TABLE person
(
  username VARCHAR(20) NOT NULL,
  pass VARCHAR(20) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE bookstore
(
  order_number VARCHAR(20) NOT NULL,
  book_name VARCHAR(20) NOT NULL,
  PRIMARY KEY (order_number)
);
CREATE TABLE warehouse
(
  warehouse_name VARCHAR(20) NOT NULL,
  PRIMARY KEY (warehouse_name)
);
CREATE TABLE store_owner
(
  email VARCHAR(20) NOT NULL,
  username VARCHAR(20) NOT NULL,
  pass VARCHAR(20) NOT NULL,
  PRIMARY KEY (email)
);
CREATE TABLE book
(
  ISBN VARCHAR(20) NOT NULL,
  publisher VARCHAR(20) NOT NULL,
  genre VARCHAR(20) NOT NULL,
  title VARCHAR(20) NOT NULL,
  pages INT NOT NULL,
  stock FLOAT NOT NULL,
  author VARCHAR(20) NOT NULL,
  prices FLOAT NOT NULL,
  unit_cost FLOAT NOT NULL,
  units_sold FLOAT NOT NULL,
  PRIMARY KEY (ISBN)
);

CREATE TABLE check_out
(
  billing_info VARCHAR(20) NOT NULL,
  shipping_info VARCHAR(20) NOT NULL,
  username VARCHAR(20) NOT NULL,
  order_number VARCHAR(20) NOT NULL,
  FOREIGN KEY (username) REFERENCES person(username),
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number)
);

CREATE TABLE update_order
(
  order_number VARCHAR(20) NOT NULL,
  warehouse_name VARCHAR(20) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (warehouse_name) REFERENCES warehouse(warehouse_name)
);
CREATE TABLE request_stock
(
  warehouse_name VARCHAR(20) NOT NULL,
  email VARCHAR(20) NOT NULL,
  FOREIGN KEY (warehouse_name) REFERENCES warehouse(warehouse_name),
  FOREIGN KEY (email) REFERENCES store_owner(email)
);

CREATE TABLE update_stock
(
  order_number VARCHAR(20) NOT NULL,
  email VARCHAR(20) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (email) REFERENCES store_owner(email)
);
CREATE TABLE publisher
(
  publisher_address VARCHAR(20) NOT NULL,
  book VARCHAR(20) NOT NULL,
  publisher_name VARCHAR(20) NOT NULL,
  phone_number INT NOT NULL,
  PRIMARY KEY (publisher_address)
);

CREATE TABLE store_book
(
  order_number VARCHAR(20) NOT NULL,
  ISBN VARCHAR(20) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (ISBN) REFERENCES book(ISBN)
);

CREATE TABLE book_pub
(
  ISBN VARCHAR(20) NOT NULL,
  publisher_address VARCHAR(20) NOT NULL,
  FOREIGN KEY (ISBN) REFERENCES book(ISBN),
  FOREIGN KEY (publisher_address) REFERENCES publisher(publisher_address)
);

CREATE TABLE bank
(
  id VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE order_book
(
  ISBN VARCHAR(20) NOT NULL,
  BIlling VARCHAR(20) NOT NULL,
  PRIMARY KEY (ISBN)
);
CREATE TABLE store_pub
(
  order_number VARCHAR(20) NOT NULL,
  publisher_address VARCHAR(20) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (publisher_address) REFERENCES publisher(publisher_address)
);

CREATE TABLE bank_account
(
  id VARCHAR(20) NOT NULL,
  publisher_address VARCHAR(20) NOT NULL,
  FOREIGN KEY (id) REFERENCES bank(id),
  FOREIGN KEY (publisher_address) REFERENCES publisher(publisher_address)
);

CREATE TABLE ordering
(
  order_number VARCHAR(20) NOT NULL,
  ISBN VARCHAR(20) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (ISBN) REFERENCES order_book(ISBN)
);


