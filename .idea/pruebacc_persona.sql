CREATE DATABASE  IF NOT EXISTS `pruebacc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pruebacc`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pruebacc
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apPaterno` varchar(50) NOT NULL,
  `apMaterno` varchar(50) DEFAULT NULL,
  `calle` varchar(30) NOT NULL,
  `colonia` varchar(30) NOT NULL,
  `numero` varchar(5) NOT NULL,
  `cp` varchar(5) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `rfc` varchar(13) NOT NULL,
  `statusProspecto` varchar(10) DEFAULT 'Enviado',
  `observaciones` varchar(200) DEFAULT NULL,
  `status` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Aida','Rivera','Cota','Huertos','Platino','2296','81270','6681104024','No se','Enviado','',1),(2,'Dylan','Pineda','Rivera','Huertos','Platino','2296','81270','6682570536','no se','Enviado','AAAAAAA',1),(3,'Mildred','Lahara','Pineda','Huertas','Residencial Plat','2290','12312','91923','no se','Enviado',NULL,0),(4,'Danielon','Mendoza','','No se','Tampoco se','0000','0000','1','no se','Enviado','',1),(5,'looool','o','l','o','l','8','7','8','l','Enviado','',1),(6,'AAA','A','2','3','4','1','5','4','2','Enviado',NULL,1),(7,'a','s','d','q','w','2','1','2','3','Enviado',NULL,1),(8,'a','s','w','e','q1','1','2','3','4','Enviado',NULL,1),(9,'l','o','i','u','i','8','7','8','9','Enviado',NULL,1),(10,'a','s','d','f','q','1','2','3','1','Enviado',NULL,0),(11,'s','dw','q','e2','1','2','3','1','2','Enviado',NULL,0),(12,'1','1','1','1','1','1','1','1','1','Enviado',NULL,1);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-06 10:40:57
