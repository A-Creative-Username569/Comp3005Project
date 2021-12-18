CREATE TABLE person
(
  username VARCHAR(8) NOT NULL,
  pass VARCHAR(8) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE bookstore
(
  order_number INT NOT NULL,
  book_name VARCHAR(8) NOT NULL,
  PRIMARY KEY (order_number)
);
CREATE TABLE warehouse
(
  warehouse_name INT NOT NULL,
  PRIMARY KEY (warehouse_name)
);
CREATE TABLE store_owner
(
  email INT NOT NULL,
  username INT NOT NULL,
  pass INT NOT NULL,
  PRIMARY KEY (email)
);
CREATE TABLE book
(
  ISBN VARCHAR(8) NOT NULL,
  publisher VARCHAR(8) NOT NULL,
  genre VARCHAR(8) NOT NULL,
  title VARCHAR(8) NOT NULL,
  pages INT NOT NULL,
  stock FLOAT NOT NULL,
  author VARCHAR(8) NOT NULL,
  prices FLOAT NOT NULL,
  unit_cost FLOAT NOT NULL,
  PRIMARY KEY (ISBN)
);

CREATE TABLE check_out
(
  order_id INT NOT NULL,
  billing_info FLOAT NOT NULL,
  shipping_info VARCHAR(8) NOT NULL,
  username VARCHAR(8) NOT NULL,
  order_number INT NOT NULL,
  FOREIGN KEY (username) REFERENCES person(username),
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number)
);

CREATE TABLE update_order
(
  order_number INT NOT NULL,
  warehouse_name INT NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (warehouse_name) REFERENCES warehouse(warehouse_name)
);
CREATE TABLE request_stock
(
  warehouse_name INT NOT NULL,
  email INT NOT NULL,
  FOREIGN KEY (warehouse_name) REFERENCES warehouse(warehouse_name),
  FOREIGN KEY (email) REFERENCES store_owner(email)
);

CREATE TABLE update_stock
(
  order_number INT NOT NULL,
  email INT NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (email) REFERENCES store_owner(email)
);
CREATE TABLE publisher
(
  publisher_address VARCHAR(8) NOT NULL,
  book VARCHAR(8) NOT NULL,
  publisher_name VARCHAR(8) NOT NULL,
  phone_number INT NOT NULL,
  PRIMARY KEY (publisher_address)
);

CREATE TABLE store_book
(
  order_number INT NOT NULL,
  ISBN VARCHAR(8) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (ISBN) REFERENCES book(ISBN)
);

CREATE TABLE book_pub
(
  ISBN VARCHAR(8) NOT NULL,
  publisher_address VARCHAR(8) NOT NULL,
  FOREIGN KEY (ISBN) REFERENCES book(ISBN),
  FOREIGN KEY (publisher_address) REFERENCES publisher(publisher_address)
);

CREATE TABLE bank
(
  id INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE order_book
(
  ISBN INT NOT NULL,
  BIlling INT NOT NULL,
  PRIMARY KEY (ISBN)
);
CREATE TABLE store_pub
(
  order_number INT NOT NULL,
  publisher_address VARCHAR(8) NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (publisher_address) REFERENCES publisher(publisher_address)
);

CREATE TABLE bank_account
(
  id INT NOT NULL,
  publisher_address VARCHAR(8) NOT NULL,
  FOREIGN KEY (id) REFERENCES bank(id),
  FOREIGN KEY (publisher_address) REFERENCES publisher(publisher_address)
);

CREATE TABLE ordering
(
  order_number INT NOT NULL,
  ISBN INT NOT NULL,
  FOREIGN KEY (order_number) REFERENCES bookstore(order_number),
  FOREIGN KEY (ISBN) REFERENCES order_book(ISBN)
);


