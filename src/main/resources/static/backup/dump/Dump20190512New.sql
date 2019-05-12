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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_levels`
--

LOCK TABLES `access_levels` WRITE;
/*!40000 ALTER TABLE `access_levels` DISABLE KEYS */;
INSERT INTO `access_levels` VALUES (1,'Admin');
/*!40000 ALTER TABLE `access_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_types`
--

DROP TABLE IF EXISTS `account_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_types`
--

LOCK TABLES `account_types` WRITE;
/*!40000 ALTER TABLE `account_types` DISABLE KEYS */;
INSERT INTO `account_types` VALUES (0,'Активный'),(1,'Пассивный');
/*!40000 ALTER TABLE `account_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accounts` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` bigint(20) NOT NULL,
  `value` decimal(19,2) NOT NULL,
  `account_type_id` bigint(20) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8n01exlilvgdahg56bagoqcj6` (`account_type_id`),
  KEY `FKs08d0ccyak63pou9tfk093dbk` (`currency_id`),
  CONSTRAINT `FK8n01exlilvgdahg56bagoqcj6` FOREIGN KEY (`account_type_id`) REFERENCES `account_types` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKs08d0ccyak63pou9tfk093dbk` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,222,69,'Минск',_binary '',220019,'Горецкого','BLR'),(2,222,69,'Минск',_binary '',220019,'Горецкого','BLR'),(3,222,69,'Минск',_binary '',220019,'Горецкого','BLR');
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
  `income_per_month` decimal(19,2) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'COOO \"Атлант-М Фарцойгхандель\"','irina.remneva29@yandex.by','375173158334',1500.00,_binary '\0',_binary '\0',_binary '',_binary '\0','375296958334','Специалист по работе с клиентами',2,'BLR','12345678912345');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contracts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_expire` datetime NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `percentage` tinyint(4) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `current_account_id` varchar(255) NOT NULL,
  `percent_account_id` varchar(255) NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrqssit79jdlx2ch8ubajt6w4y` (`client_id`),
  KEY `FK1obirx127nr6krsd7a36bqmn7` (`current_account_id`),
  KEY `FK4bg9ihc6uujhrtfo8rdpvjyx1` (`percent_account_id`),
  KEY `FKsd2at9os52ugelg72eo7oun6n` (`worker_id`),
  CONSTRAINT `FK1obirx127nr6krsd7a36bqmn7` FOREIGN KEY (`current_account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK4bg9ihc6uujhrtfo8rdpvjyx1` FOREIGN KEY (`percent_account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKrqssit79jdlx2ch8ubajt6w4y` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKsd2at9os52ugelg72eo7oun6n` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
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
INSERT INTO `countries` VALUES ('BLR','Республика Беларусь');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'BYN');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
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
INSERT INTO `passports` VALUES ('12345678912345','1972-04-29 00:00:00','2021-03-12 00:00:00','2001-03-12 00:00:00','Ирина',_binary '\0',_binary '\0','Ремнева','Геннадьевна',1234567,'Фрунзенское РУВД г.Минска','MP','BLR',2),('3150988B112PB4','1999-08-14 00:00:00','2025-07-31 00:00:00','2015-07-31 00:00:00','Глеб',_binary '',_binary '\0','Ремнёв','Александрович',3707505,'Фрунзенское РУВД г. Минска','MP','BLR',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrations`
--

LOCK TABLES `registrations` WRITE;
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
INSERT INTO `registrations` VALUES (1,'2007-08-14 00:00:00','Администрация Фрунзенского УВД г. Минска',1),(2,'2002-09-02 00:00:00','Фрунзенское РУВД г.Минска',3);
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

-- Dump completed on 2019-05-12 15:45:05
