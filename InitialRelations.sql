delete from user;
delete from check_out;
delete from bookstore;
delete from update_order;
delete from warehouse;
delete from update_stock;
delete from request_stock;
delete from store_owner;
delete from store_pub;
delete from publisher;
delete from bank_account;
delete from store_book;
delete from book;
delete from bank;

insert into user values ('user1','pass1');
insert into user values ('user2','pass2');
insert into check_out values ('','','','','');//check these two, i think i am wrong
insert into check_out values ('','','','','');
insert into bookstore values ('1','Amazon');
insert into bookstore values ('2','Amazon');
insert into update_order values ('x1','Amazon');
insert into update_order values ('x2','Amazon');
insert into warehouse values ('Amazon');
insert into warehouse values ('Coles');
insert into update_stock values ('z1','Amazon@amazon.com','John');
insert into update_stock values ('z2','Amazon@amazon.com','John');
insert into request_stock values ('Amazon','Amazon@amazon.com','John');
insert into request_stock values ('Coles','Coles@coles.com','John');
insert into store_owner values ('John@gmail.com','MC117','Autumn');
insert into store_owner values ('Thel@gmail.com','Vadamee','Arbiter');
insert into store_pub values ('72 grant ave','2');
insert into store_pub values ('61 storm dr', '1');
insert into publisher values ('21 jump st', 'book1','pub1','1-800-267-2001');
insert into publisher values ('21 jump st', 'book2','pub1','1-800-267-2001');
insert into bank_account values ('30 Merivale road','0343','','');//might be wrong
insert into bank_account values ('1415 Rainbow road','3005','','');
insert into store_book values ('','','','');//put something here
insert into store_book values ('','','','');
insert into book values ('ISBN1','pub1','Horrer','book1','123','20','auth1','32','10');
insert into book values ('ISBN2','pub1','scifi','book2','430','24','auth1','40','15');
insert into bank values ('TD');
insert into bank values ('ScotiaBank');
