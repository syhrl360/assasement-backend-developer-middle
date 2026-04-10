CREATE DATABASE IF NOT EXISTS microservice_assesment;
USE microservice_assesment;

CREATE TABLE IF NOT EXISTS `loan_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `loan_amount` int NOT NULL,
  `loan_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `payment_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `bank_account` varchar(50) NOT NULL,
  `bank_name` varchar(50) NOT NULL,
  `repay_amount` int NOT NULL,
  `admin_fee` int NOT NULL,
  `due_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `user_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `bank_account` varchar(50) NOT NULL,
  `bank_name` varchar(50) NOT NULL,
  `balance` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `user_account` (`user_id`, `name`, `bank_account`, `bank_name`, `balance`) VALUES (1, 'Alpha', '1122334455', 'MANDIRI', 2200000), (2, 'Bravo', '225522', 'BCA', 2500000);