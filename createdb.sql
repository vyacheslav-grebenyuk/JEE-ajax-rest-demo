CREATE DATABASE  IF NOT EXISTS `jeepetshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jeepetshop`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jeepetshop
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `good_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKACC7B9C692D22DA5` (`user_id`),
  KEY `FKACC7B9C6A72724C1` (`good_id`),
  CONSTRAINT `FKACC7B9C692D22DA5` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`),
  CONSTRAINT `FKACC7B9C6A72724C1` FOREIGN KEY (`good_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `weight` float DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK300C5E9AD055FF` (`id`),
  CONSTRAINT `FK300C5E9AD055FF` FOREIGN KEY (`id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1000,1),(700,2),(500,3),(350,4),(900,5),(750,6);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_pet`
--

DROP TABLE IF EXISTS `food_pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_pet` (
  `id_pet` int(11) NOT NULL,
  `id_food` int(11) NOT NULL,
  KEY `FK16A38DBED9C85628` (`id_pet`),
  KEY `FK16A38DBE5F39A212` (`id_food`),
  CONSTRAINT `FK16A38DBE5F39A212` FOREIGN KEY (`id_food`) REFERENCES `food` (`id`),
  CONSTRAINT `FK16A38DBED9C85628` FOREIGN KEY (`id_pet`) REFERENCES `pets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_pet`
--

LOCK TABLES `food_pet` WRITE;
/*!40000 ALTER TABLE `food_pet` DISABLE KEYS */;
INSERT INTO `food_pet` VALUES (12,1),(12,3),(12,5),(14,1),(13,3),(11,1),(11,2),(11,4),(11,6);
/*!40000 ALTER TABLE `food_pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'meat',45,25),(2,'Pedigree',95,5),(3,'Kitkat',80,10),(4,'Royal Canin',90,3),(5,'JosiCat',60,15),(6,'Chappi',80,10),(7,'Trixie Мяч Denta Fun, каучук',50,15),(8,'Trixie Игрушка \"Свинка со щетиной\", латекс',60,10),(9,'Trixie Мячик меховой, звенящий',35,5),(10,'Camon Мышка меховая с перьями',45,8),(11,'dog',100,1),(12,'cat',120,1),(13,'bird',320,1),(14,'lizard',150,1);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pets`
--

DROP TABLE IF EXISTS `pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pets` (
  `age` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3473349AD055FF` (`id`),
  CONSTRAINT `FK3473349AD055FF` FOREIGN KEY (`id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
INSERT INTO `pets` VALUES (5,11),(10,12),(5,13),(15,14);
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool_pet`
--

DROP TABLE IF EXISTS `tool_pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tool_pet` (
  `id_pet` int(11) NOT NULL,
  `id_tool` int(11) NOT NULL,
  KEY `FKC564BDF8D9C85628` (`id_pet`),
  KEY `FKC564BDF8FE0B89A5` (`id_tool`),
  CONSTRAINT `FKC564BDF8D9C85628` FOREIGN KEY (`id_pet`) REFERENCES `pets` (`id`),
  CONSTRAINT `FKC564BDF8FE0B89A5` FOREIGN KEY (`id_tool`) REFERENCES `tools` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_pet`
--

LOCK TABLES `tool_pet` WRITE;
/*!40000 ALTER TABLE `tool_pet` DISABLE KEYS */;
INSERT INTO `tool_pet` VALUES (12,8),(12,10),(14,7),(13,10),(11,7),(11,9);
/*!40000 ALTER TABLE `tool_pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tools`
--

DROP TABLE IF EXISTS `tools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tools` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK696C9DB9AD055FF` (`id`),
  CONSTRAINT `FK696C9DB9AD055FF` FOREIGN KEY (`id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tools`
--

LOCK TABLES `tools` WRITE;
/*!40000 ALTER TABLE `tools` DISABLE KEYS */;
INSERT INTO `tools` VALUES (7),(8),(9),(10);
/*!40000 ALTER TABLE `tools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,0),(2,1),(3,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','','$2a$10$0Hx7LUP6LXuPeFaH7rr1/.utvRDnjlAySGB.KyMZYKHxc0l6Qt69W'),('cust','','$2a$10$dXEnOW6j2dXoZKJDoV0DvuQHTl6ogWrtNoTbWbDB3QOeKHt7lz2.2'),('user','','$2a$10$QsTKWUwBOu/ovPDeeY7Id.GWi4eSKMxh8QaMCkdmXstzRpYMFS.B2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_user_roles`
--

DROP TABLE IF EXISTS `users_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_user_roles` (
  `USERS_ID` varchar(255) NOT NULL,
  `USER_ROLES_ID` int(11) NOT NULL,
  PRIMARY KEY (`USERS_ID`,`USER_ROLES_ID`),
  KEY `FKD49017E08BC30648` (`USERS_ID`),
  KEY `FKD49017E069B1FC11` (`USER_ROLES_ID`),
  CONSTRAINT `FKD49017E069B1FC11` FOREIGN KEY (`USER_ROLES_ID`) REFERENCES `user_roles` (`id`),
  CONSTRAINT `FKD49017E08BC30648` FOREIGN KEY (`USERS_ID`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_user_roles`
--

LOCK TABLES `users_user_roles` WRITE;
/*!40000 ALTER TABLE `users_user_roles` DISABLE KEYS */;
INSERT INTO `users_user_roles` VALUES ('admin',1),('user',1),('admin',2),('cust',2),('user',2),('admin',3);
/*!40000 ALTER TABLE `users_user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-06 11:41:53
