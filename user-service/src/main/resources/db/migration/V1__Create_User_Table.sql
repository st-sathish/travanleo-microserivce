create table if not exists `user` (
    id int(11) NOT NULL AUTO_INCREMENT,
    first_name varchar(256),
    last_name varchar(256),
    age int(11),
    email varchar(256),
    mobile BIGINT,
    primary key(`id`)
) ENGINE=InnoDB CHARACTER SET=utf8;