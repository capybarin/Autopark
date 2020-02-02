CREATE DATABASE  IF NOT EXISTS `motorpoll` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `motorpoll`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: motorpoll
-- ------------------------------------------------------
-- Server version	5.5.62

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
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus` (
  `idBus` int(11) NOT NULL AUTO_INCREMENT,
  `BusName` varchar(45) DEFAULT NULL,
  `Activity` enum('free','busy') DEFAULT NULL,
  PRIMARY KEY (`idBus`),
  UNIQUE KEY `idBus_UNIQUE` (`idBus`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,'1','free'),(2,'2','busy'),(3,'5','free'),(4,'6','free'),(5,'7','free'),(6,'8','free'),(7,'11','free'),(8,'14','free'),(9,'16','free'),(10,'17','free'),(11,'19','free'),(12,'20','free'),(13,'21','free'),(14,'22','free'),(15,'24','free'),(16,'27','free'),(17,'30','free');
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `idRoute` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idRoute`),
  UNIQUE KEY `idRoute_UNIQUE` (`idRoute`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Педучилище - Железнодорожный вокзал'),(2,'пл. Школьная - ВПЗ'),(3,'ул. Комарова - м/н Пятничаны'),(4,'ОАО \"Масложиркомбинат\" - пл. Победы'),(5,'ул. Якова Шепеля - м/н Пирогово'),(6,'ул. Бучмы - Железнодорожный вокзал'),(7,'ул. Ботаническая - м/н Сабаров'),(8,'Железнодорожный вокзал - Дом отдыха'),(9,'Мемориал освобождения - Аграрный университет'),(10,'Музей Коцюбинского - м/н Тяжилов'),(11,'м/н Вишенка - ш. Немировское'),(12,'Мемориал освобождения - Хутор Шевченко'),(13,'Педучилище - ш. Барское'),(14,'м/н Академический - Железнодорожный вокзал'),(15,'м/н Вишенка - ул. Бучмы'),(16,'Железнодорожный вокзал - с. Лука Мелешковская'),(17,'Дом отдыха - пгт Десна'),(18,'пл. Победы - Педучилище'),(19,'ул. Якова Шепеля - пл. Победы'),(20,'ВПЗ - пл. Школьная'),(21,'пер. 1-й Киевский - ул. Гонты'),(22,'ул. Луговая - пл. Наливайко'),(23,'м/н Пятничаны - с. Шкуринцы'),(24,'ул. Якова Шепеля - рынок \"Урожай\"'),(25,'с. Винницкие Хутора - ул. Замостянская'),(26,'ул. Бучмы - ул. Ольги Кобылянской'),(27,'Железнодорожный вокзал - станция Грузовая'),(28,'ул. Ботаническая - с. Агрономическое'),(29,'ул. Ботаническая - ш. Барское'),(31,'ш. Барское - Сабаров');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idUsers` int(11) NOT NULL AUTO_INCREMENT,
  `Name_` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `Role` enum('A','U') DEFAULT NULL,
  `Activity` enum('free','busy') DEFAULT NULL,
  `Password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idUsers`),
  UNIQUE KEY `idUsers_UNIQUE` (`idUsers`),
  UNIQUE KEY `Name__UNIQUE` (`Name_`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Vlad','Bezdushyi','U','busy','hello'),(2,'Igor','Pupkin','U','busy','bye'),(3,'Christian','Abusov','U','free','fdhfhsd'),(4,'Andrey','Bobrov','A','free','admin'),(8,'Oleg','Volkov','U','free','giggy'),(9,'Ivan','Nezuev','U','free','fggfdjgil'),(18,'Vlyd','Myravei','U','free','qwerty'),(19,'Klioma','228','U','busy','228'),(20,'Bober','Capybarov','U','free','123'),(21,'Hello','Hello','U','free','1'),(22,'Yamero','cat','U','free','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work` (
  `idWork` int(11) NOT NULL AUTO_INCREMENT,
  `Users_id` int(11) DEFAULT NULL,
  `Route_id` int(11) DEFAULT NULL,
  `Bus_id` int(11) DEFAULT NULL,
  `Accepted` enum('Y','N') DEFAULT NULL,
  PRIMARY KEY (`idWork`),
  UNIQUE KEY `idWork_UNIQUE` (`idWork`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (1,1,1,1,'N'),(2,2,2,2,'N'),(8,6,6,6,'N'),(9,3,1,1,'N'),(10,1,2,2,'Y');
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-02 13:39:42
