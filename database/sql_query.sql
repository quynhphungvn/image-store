create database image_store;

use image_store;

create table if not exists image_group (
	id int not null auto_increment,
    name varchar(100),
    primary key (id)
);

create table if not exists image_file (
	id int not null auto_increment,
    uploaded_name varchar(100),
    format_type varchar(5),
    data mediumblob,
    thumbnail blob,
    created_time datetime,
    group_id int,
    primary key (id),
    foreign key (group_id) references image_group(id)
);