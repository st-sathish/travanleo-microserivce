create table if not exists users(
    id int(11) not null AUTO_INCREMENT,
    username varchar(256) not null,
    password varchar(256) not null,
    enabled tinyint(1) not null default 1,
    PRIMARY KEY (id)
)ENGINE=InnoDB CHARACTER SET=utf8;

-- original password john@123 and kelly@123 --
INSERT INTO users (username, password, enabled)
    VALUES ('john', '$2a$04$xqJH/AWpC89pBBFb7i9VU.zoWbOrE2gvdFcfTAOE1bCF5.tNvVXXu', 1);
INSERT INTO users (username, password, enabled)
    VALUES ('kelly','$2a$04$IpZnGqXXgNvvMbqlg/tc7uJUM.1nj/5KtqnFlxRpRN2RqWUFV4lg6', 1);


CREATE TABLE if not exists `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB CHARACTER SET=utf8;

INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

CREATE TABLE if not exists `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `user_fk_idx` (`user_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
)ENGINE=InnoDB CHARACTER SET=utf8;

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); -- user john has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1); -- user kelly has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2); -- user kelly has role ADMIN
