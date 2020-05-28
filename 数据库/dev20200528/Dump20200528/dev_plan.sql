-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: 39.106.116.63    Database: dev
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `planid` int(11) NOT NULL AUTO_INCREMENT,
  `year` year(4) NOT NULL,
  `regionid` int(11) NOT NULL,
  `classid` int(11) NOT NULL,
  `universityid` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `is_approved` tinyint(4) NOT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`planid`),
  KEY `regionid4_idx` (`regionid`),
  KEY `classid_idx` (`classid`),
  KEY `universityid2_idx` (`universityid`),
  CONSTRAINT `classid2` FOREIGN KEY (`classid`) REFERENCES `major` (`classid`),
  CONSTRAINT `regionid4` FOREIGN KEY (`regionid`) REFERENCES `region` (`regionid`),
  CONSTRAINT `universityid2` FOREIGN KEY (`universityid`) REFERENCES `university` (`universityid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,2019,7,5,6,63,0,NULL),(2,2019,3,2,10,30,0,NULL),(3,2019,10,3,9,40,0,NULL),(4,2019,3,2,10,25,0,NULL),(5,2019,1,1,1,23,0,NULL),(6,2019,1,3,3,65,0,NULL),(7,2019,8,5,2,32,0,NULL);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-28 15:14:21
