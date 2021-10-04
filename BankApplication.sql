-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_no` int NOT NULL AUTO_INCREMENT,
  `account_type` enum('savings','current') NOT NULL,
  `user_name` varchar(40) NOT NULL,
  `balance` float NOT NULL,
  `ifsc_code` varchar(15) NOT NULL,
  PRIMARY KEY (`account_no`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `user_id_idx` (`user_name`),
  CONSTRAINT `user_name` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=100000114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (100000111,'savings','DeepakBhuyan2697',2400,'BANK0004567'),(100000113,'savings','JayshreeChaure2778',700,'BANK0005943');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `transaction_type` enum('credit','debit') NOT NULL,
  `account_no` int NOT NULL,
  `beneficiary_accno` int DEFAULT NULL,
  `transaction_amount` float NOT NULL,
  `closing_balance` float NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `account_no_idx` (`account_no`),
  CONSTRAINT `account_no` FOREIGN KEY (`account_no`) REFERENCES `account` (`account_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (5,'debit',100000111,100000113,50,1000),(7,'debit',100000111,100000113,50,1950),(8,'debit',100000111,100000113,50,1900),(11,'debit',100000111,100000113,50,1850),(12,'debit',100000113,100000111,500,750),(13,'debit',100000113,100000111,50,700);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_name` varchar(40) NOT NULL,
  `f_name` varchar(20) NOT NULL,
  `m_name` varchar(20) DEFAULT NULL,
  `l_name` varchar(20) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_pan` varchar(10) NOT NULL,
  `date_of_birth` date NOT NULL,
  `address` varchar(30) NOT NULL,
  `mobileno` bigint NOT NULL,
  `user_uid` varchar(15) NOT NULL,
  `password` varchar(16) NOT NULL,
  `isactive` bit(1) DEFAULT NULL,
  `otp` int DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('DeepakBhuyan2697','Deepak','Kumar','Bhuyan','deepakkumarbhuyan98@gmail.com','DWOPB1236H','1998-05-22','Odisha',7377373114,'8665 8375 9876','Dk@!asa1',_binary '',NULL),('JayshreeChaure2778','Jayshree','H','Chaure','jayshric91@gmail.com','AMCGH1236H','1998-09-11','Ulashnagar',7798763678,'8665 5331 9876','Js@!asa1',_binary '',NULL),('SoumyaSau8857','Soumya','S','Sau','soumya11.kol@gmail.com','ABCGH1234F','1998-09-10','Kolkata',7798763423,'8665 5331 8765','Sa@!asa1',_binary '',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-29 11:53:03
