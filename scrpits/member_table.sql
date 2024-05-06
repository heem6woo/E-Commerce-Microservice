CREATE SCHEMA `member_directory`;

use `member_directory`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(68) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



CREATE TABLE `customer_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `age` int DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `address` varchar(100) default null,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`membe_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`member_id`) 
  REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `seller_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `bn` long DEFAULT NULL,
  `address` varchar(100) default null,
  `domain` varchar(60) default null,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`membe_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`member_id`) 
  REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




SET FOREIGN_KEY_CHECKS = 1;