create database if not exists gabandb01 character set utf8;
create user 'chat_user'@'localhost' identified by 'chat_user';
grant all on gabandb01.* to 'chat_user'@'localhost';
create database if not exists gabandb01_test character set utf8;
create user 'chat_user_test'@'localhost' identified by 'chat_user_test';
grant all on gabandb01_test.* to 'chat_user_test'@'localhost';
