CREATE DATABASE demo_db;

USE demo_db;

CREATE TABLE userinfo(
	id int(11) NOT NULL,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;