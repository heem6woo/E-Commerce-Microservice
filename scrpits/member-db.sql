DROP SCHEMA IF EXISTS `member_directory`;

CREATE SCHEMA `member_directory`;

use `member_directory`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(68) NOT NULL,
  `role` ENUM('ADMIN' , 'CUSTOMER', 'SELLER'),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

insert into `member` (id, name, email, password, role) values 
(1, '우희민', 'heem6woo@gmail.com', '$2a$10$/So2kYEFDhOdCZmu/D7xiO1qR1Dp8JQPHfKrU2JXNQUOUBLALnQCe', 'ADMIN'),
(2, '김도현', 'rkawkaos77@naver.com', '$2a$10$zh0jF1Je28jUpPX1v/.GJejWH.Z5uS4QZzmMY4MrnvlYd1Q1EVGha', 'ADMIN');

CREATE TABLE `customer_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `age` int DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `address` varchar(100) default null,
  PRIMARY KEY (`id`),
  KEY `FK_CUSTOMER_DETAIL_idx` (`member_id`),
  CONSTRAINT `FK_CUSTOMER_DETAIL` FOREIGN KEY (`member_id`)
  REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `seller_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `bn` long DEFAULT NULL,
  `address` varchar(100) default null,
  `domain` varchar(60) default null,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SELLER_DETAIL_idx` (`member_id`),
  CONSTRAINT `FK_SELLER_DETAIL` FOREIGN KEY (`member_id`) 
  REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


SET FOREIGN_KEY_CHECKS = 1;