create database weatherDB;

use weatherDB;

create table weather (
    id varchar(64) not null,
    city varchar(128) PRIMARY KEY not null,
    -- primary key(userId);
);