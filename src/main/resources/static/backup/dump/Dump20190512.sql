-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: bank-system
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access_levels`
--

DROP TABLE IF EXISTS `access_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `access_levels` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9lt2v43i3s8235emubg2iudef` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_levels`
--

LOCK TABLES `access_levels` WRITE;
/*!40000 ALTER TABLE `access_levels` DISABLE KEYS */;
INSERT INTO `access_levels` VALUES (1,'Admin'),(2,'Manager'),(3,'Plain worker');
/*!40000 ALTER TABLE `access_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_expire` datetime NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `value` double NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgymog7firrf8bnoiig61666ob` (`client_id`),
  KEY `FKs08d0ccyak63pou9tfk093dbk` (`currency_id`),
  KEY `FKfbnynipca7qqfa4v8n1pauqv7` (`worker_id`),
  CONSTRAINT `FKfbnynipca7qqfa4v8n1pauqv7` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKgymog7firrf8bnoiig61666ob` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKs08d0ccyak63pou9tfk093dbk` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (2,'2023-05-07 09:16:29','2019-05-07 09:16:29',0,1,4,1),(3,'2023-05-07 09:16:37','2019-05-07 09:16:37',0,1,2,1),(4,'2023-05-07 09:16:44','2019-05-07 09:16:44',0,1,3,1),(6,'2023-05-07 09:35:34','2019-05-07 09:35:34',0,2,4,1),(7,'2023-05-07 09:35:41','2019-05-07 09:35:41',0,2,2,1),(8,'2023-05-07 09:35:48','2019-05-07 09:35:48',0,2,3,1),(9,'2023-05-07 09:42:56','2019-05-07 09:42:56',0,3,1,1),(10,'2023-05-07 09:43:03','2019-05-07 09:43:03',0,3,4,1),(11,'2023-05-07 09:43:08','2019-05-07 09:43:08',0,3,2,1),(12,'2023-05-07 09:43:14','2019-05-07 09:43:14',0,3,3,1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apartment_number` smallint(6) NOT NULL,
  `building_number` smallint(6) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `is_apartment` bit(1) NOT NULL,
  `post_code` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `country_iso3code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgxseknlvm1hbc7dvrgjav8rii` (`country_iso3code`),
  CONSTRAINT `FKgxseknlvm1hbc7dvrgjav8rii` FOREIGN KEY (`country_iso3code`) REFERENCES `countries` (`iso3code`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,222,69,'Минск',_binary '',220019,'Горецкого','BLR'),(2,222,69,'Минск',_binary '',220019,'Горецкого','BLR'),(3,222,69,'Минск',_binary '',220019,'Горецкого','BLR'),(4,78,3,'Минск',_binary '',220023,'Олешева','BLR'),(5,78,3,'Минск',_binary '',220023,'Олешева','BLR'),(6,2,17,'Фаниполь',_binary '',223713,'Обойная','BLR'),(7,2,17,'Фаниполь',_binary '',223713,'Обойная','BLR'),(8,22,12,'Минск',_binary '',220032,'Ольшевского','BLR'),(9,22,12,'Минск',_binary '',220032,'Ольшевского','BLR'),(10,22,12,'Минск',_binary '',220032,'Ольшевского','BLR');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `home_phone_number` varchar(255) NOT NULL,
  `income_per_month` double NOT NULL,
  `is_bound_to_military_service` bit(1) NOT NULL,
  `is_disabled` bit(1) NOT NULL,
  `is_employed` bit(1) NOT NULL,
  `is_retiree` bit(1) NOT NULL,
  `mobile_phone_number` varchar(255) NOT NULL,
  `position` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  `country_iso3code` varchar(255) NOT NULL,
  `passport_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tbjymsby54u9l114xcwb8ee1u` (`passport_id`),
  UNIQUE KEY `UK_srv16ica2c1csub334bxjjb59` (`email`),
  UNIQUE KEY `UK_fh0ql5ncy54httt5f7gt5i9id` (`mobile_phone_number`),
  KEY `FK21gyuophuha3vq8t1os4x2jtl` (`address_id`),
  KEY `FK8d6bbl3yup7ngtvfb47eh02tm` (`country_iso3code`),
  CONSTRAINT `FK21gyuophuha3vq8t1os4x2jtl` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK8d6bbl3yup7ngtvfb47eh02tm` FOREIGN KEY (`country_iso3code`) REFERENCES `countries` (`iso3code`) ON DELETE CASCADE,
  CONSTRAINT `FKt1bl0t3m5aw25yqxwusroqtot` FOREIGN KEY (`passport_id`) REFERENCES `passports` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'ОАО Автохауз Атлант-М Сухарево','irina.remneva@gmail.com','375173158334',1100,_binary '\0',_binary '\0',_binary '',_binary '\0','375296958334','Специалист по работе с клиентами',2,'BLR','4330572C021PB3'),(2,'ЧП \"Алмаз\"','karen71@gmail.com','375173340032',2000,_binary '\0',_binary '\0',_binary '',_binary '\0','375291550055','Директор',4,'BLR','5129813BB36CO1'),(3,'ООО \"Электроприборы\"','leon.slavik@tut.by','375175073608',1200,_binary '\0',_binary '\0',_binary '',_binary '\0','375259245060','Инженер-конструктор',6,'BLR','4890922EM50PP1');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `countries` (
  `iso3code` varchar(3) NOT NULL,
  `country_name` varchar(255) NOT NULL,
  PRIMARY KEY (`iso3code`),
  UNIQUE KEY `UK_lx3r8cp4g7xkaqximbtxum74r` (`country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES ('BLR','Республика Беларусь'),('RUS','Российская Федерация'),('UKR','Украина');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencies`
--

DROP TABLE IF EXISTS `currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `currencies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a2yxotynwqjrmkq71won77vui` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'BYN'),(4,'EUR'),(2,'RUS'),(3,'USD');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_types`
--

DROP TABLE IF EXISTS `operation_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operation_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i6r7urfxyktqu788sm16pvwoq` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_types`
--

LOCK TABLES `operation_types` WRITE;
/*!40000 ALTER TABLE `operation_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operations`
--

DROP TABLE IF EXISTS `operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_expire` datetime NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `percentage` tinyint(4) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `operation_id` bigint(20) NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3lft1gkkuyvhih1cst48xvm7q` (`account_id`),
  KEY `FK3xuig2bpelsbyu8920nqtjxnq` (`operation_id`),
  KEY `FKf4osg9n82iidex8di2du74jy9` (`worker_id`),
  CONSTRAINT `FK3lft1gkkuyvhih1cst48xvm7q` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK3xuig2bpelsbyu8920nqtjxnq` FOREIGN KEY (`operation_id`) REFERENCES `operation_types` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKf4osg9n82iidex8di2du74jy9` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operations`
--

LOCK TABLES `operations` WRITE;
/*!40000 ALTER TABLE `operations` DISABLE KEYS */;
/*!40000 ALTER TABLE `operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passports`
--

DROP TABLE IF EXISTS `passports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `passports` (
  `id` varchar(14) NOT NULL,
  `birth_date` datetime NOT NULL,
  `date_of_expire` datetime NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `is_male` bit(1) NOT NULL,
  `is_married` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `passport_authority` varchar(255) NOT NULL,
  `series` varchar(255) NOT NULL,
  `country_iso3code` varchar(255) NOT NULL,
  `registration_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_og7ydb7gehe7fcy92j7lsxn1v` (`registration_id`),
  UNIQUE KEY `UK_31qkfr6f1adbr1qyyaohugv0` (`number`),
  KEY `FKcyj22thb1ch0qrynhqsjyh5r0` (`country_iso3code`),
  CONSTRAINT `FKcyj22thb1ch0qrynhqsjyh5r0` FOREIGN KEY (`country_iso3code`) REFERENCES `countries` (`iso3code`) ON DELETE CASCADE,
  CONSTRAINT `FKgwuv67nmhp1jsorxqpfblgmnp` FOREIGN KEY (`registration_id`) REFERENCES `registrations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passports`
--

LOCK TABLES `passports` WRITE;
/*!40000 ALTER TABLE `passports` DISABLE KEYS */;
INSERT INTO `passports` VALUES ('12345678912345','1997-07-18 00:00:00','2027-06-14 00:00:00','2017-06-14 00:00:00','Владислав',_binary '',_binary '','Серпинский','Анатольевич',1234567,'Фрунзенское РУВД г.Минска','MP','BLR',5),('3150988B112PB4','1999-08-14 00:00:00','2025-07-31 00:00:00','2015-07-31 00:00:00','Глеб',_binary '',_binary '\0','Ремнёв','Александрович',3707505,'Фрунзенское РУВД г. Минска','MP','BLR',1),('4330572C021PB3','1972-04-29 00:00:00','2027-05-11 00:00:00','2017-05-11 00:00:00','Ирина',_binary '\0',_binary '\0','Ремнева','Геннадьевна',3655921,'Фрунзенское РУВД г.Минска','MP','BLR',2),('4890922EM50PP1','1961-08-27 00:00:00','2028-01-17 00:00:00','2008-01-17 00:00:00','Вячеслав',_binary '',_binary '','Леонеко','Иванович',3210658,'Фанипольский ОВД','MP','BLR',4),('5129813BB36CO1','1971-03-09 00:00:00','2026-03-11 00:00:00','2016-03-11 00:00:00','Карен',_binary '',_binary '','Оганесян','Мирзоевич',1905360,'Первомайское РУВД г.Минска','MP','BLR',3);
/*!40000 ALTER TABLE `passports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registrations`
--

DROP TABLE IF EXISTS `registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `registrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_registration` datetime DEFAULT NULL,
  `registration_authority` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmjk0lhygnwfvsgw4ycl1o70oa` (`address_id`),
  CONSTRAINT `FKmjk0lhygnwfvsgw4ycl1o70oa` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrations`
--

LOCK TABLES `registrations` WRITE;
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
INSERT INTO `registrations` VALUES (1,'2007-08-14 00:00:00','Администрация Фрунзенского УВД г. Минска',1),(2,'2001-02-02 00:00:00','Администрация Фрунзенского УВД г. Минска',3),(3,'2010-01-03 00:00:00','Администарция Первомайского УВД г.Минска',5),(4,'1997-08-23 00:00:00','Фанипольский ОВД',7),(5,'2003-06-18 00:00:00','Фрунзенское РУВД г.Минска',10);
/*!40000 ALTER TABLE `registrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workers`
--

DROP TABLE IF EXISTS `workers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `workers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(14) NOT NULL,
  `username` varchar(255) NOT NULL,
  `access_level_id` smallint(6) NOT NULL,
  `passport_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tg9yme0enirbc2e0skwbt7e36` (`passport_id`),
  UNIQUE KEY `UK_mhnorkgbqplbpclkg5uv3uvin` (`username`),
  KEY `FKaepwg7ma26uipx6j81wbx6tgw` (`access_level_id`),
  CONSTRAINT `FK7g3aqx90x9g63p9lllco5651i` FOREIGN KEY (`passport_id`) REFERENCES `passports` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKaepwg7ma26uipx6j81wbx6tgw` FOREIGN KEY (`access_level_id`) REFERENCES `access_levels` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workers`
--

LOCK TABLES `workers` WRITE;
/*!40000 ALTER TABLE `workers` DISABLE KEYS */;
INSERT INTO `workers` VALUES (1,'root','admin@bank-system.by',1,'3150988B112PB4');
/*!40000 ALTER TABLE `workers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-12 11:57:19
