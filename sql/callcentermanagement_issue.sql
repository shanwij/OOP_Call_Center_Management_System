-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: callcentermanagement
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `issue` (
  `issueCode` char(50) NOT NULL,
  `customerCode` char(10) NOT NULL,
  `agentCode` char(50) NOT NULL,
  `issueDate` date NOT NULL,
  `issueDescription` text,
  `issueStatus` char(25) NOT NULL,
  PRIMARY KEY (`issueCode`),
  KEY `agent_fk_idx` (`agentCode`),
  KEY `customer_fk_idx` (`customerCode`),
  CONSTRAINT `agent_fk` FOREIGN KEY (`agentCode`) REFERENCES `agent` (`agentCode`),
  CONSTRAINT `customer_fk` FOREIGN KEY (`customerCode`) REFERENCES `customer` (`customerCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES ('ISS_001','0123456789','AGT_001','2019-05-19','Issue Description #1','CLOSED'),('ISS_002','0123456789','AGT_003','1995-02-03','Something Not','CLOSED'),('ISS_003','0123456789','AGT_001','2019-05-18','Issue Description #3','CLOSED'),('ISS_004','0123456789','AGT_001','2019-05-19','Issue Description #4','CLOSED'),('ISS_005','0123456789','AGT_001','2019-05-19','Issue Description #5','CLOSED'),('ISS_006','0123456789','AGT_001','2019-05-19','Issue Description #6','CLOSED'),('ISS_007','0123456789','AGT_001','2019-05-19','Issue Description #7','CLOSED'),('ISS_010','0112233445','AGT_001','2019-05-21','Issue Description #10','CLOSED'),('ISS_011','0987654321','AGT_001','2019-05-21','Issue Description #11','CLOSED'),('ISS_012','0987654321','AGT_001','2019-05-21','Issue Description #12','CLOSED');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-21 21:43:03
