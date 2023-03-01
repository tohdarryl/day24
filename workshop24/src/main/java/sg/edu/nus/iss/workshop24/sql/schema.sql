drop database if exists shensang;

create database shensang;

CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `customer_name` varchar(128) NOT NULL,
  `ship_address` varchar(128) DEFAULT NULL,
  `notes` text,
  `tax` decimal(2,2) DEFAULT '0.05',
  PRIMARY KEY (`order_id`)
) ;

CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product` varchar(64) NOT NULL,
  `unit_price` decimal(3,2) NOT NULL,
  `discount` decimal(2,1) DEFAULT '1.0',
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`id`) REFERENCES `orders` (`order_id`)
) ;