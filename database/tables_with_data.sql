CREATE DATABASE  IF NOT EXISTS `userdata` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `userdata`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: userdata
-- ------------------------------------------------------
-- Server version	5.5.39

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `CityId` int(5) NOT NULL AUTO_INCREMENT,
  `CityName` varchar(30) NOT NULL,
  `CityCountry` varchar(30) NOT NULL,
  PRIMARY KEY (`CityId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Colombo','Sri Lanka'),(2,'Kaluthara','Sri Lanka'),(3,'Galle','Sri Lanka'),(4,'Sydney','Australia'),(5,'Brisbane','Aus'),(6,'Tokyo','Japan'),(7,'Koobe','Japan'),(8,'Shanghai','China'),(9,'Beijing','China'),(10,'California','USA'),(11,'New York','USA'),(12,'Perth','Australia'),(13,'Adelaide','Australia'),(14,'Boston','USA');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `GroupId` int(5) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(30) NOT NULL,
  PRIMARY KEY (`GroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'Administration'),(2,'CustomerCare'),(3,'Translator');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_permission`
--

DROP TABLE IF EXISTS `group_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_permission` (
  `GroupId` int(5) NOT NULL,
  `PermissionId` int(5) NOT NULL,
  PRIMARY KEY (`GroupId`,`PermissionId`),
  KEY `fk_grouppermission$permissionid_idx` (`PermissionId`),
  CONSTRAINT `fk_grouppermission$groupid` FOREIGN KEY (`GroupId`) REFERENCES `group` (`GroupId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_grouppermission$permissionid` FOREIGN KEY (`PermissionId`) REFERENCES `permission` (`PermissionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_permission`
--

LOCK TABLES `group_permission` WRITE;
/*!40000 ALTER TABLE `group_permission` DISABLE KEYS */;
INSERT INTO `group_permission` VALUES (1,1),(1,2),(1,3),(2,3),(1,4),(2,5),(3,5),(1,6),(3,6);
/*!40000 ALTER TABLE `group_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `PermissionId` int(5) NOT NULL AUTO_INCREMENT,
  `PermissionName` varchar(30) NOT NULL,
  PRIMARY KEY (`PermissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'AddUser'),(2,'EditUser'),(3,'SearchUser'),(4,'DeleteUser'),(5,'Translate'),(6,'Login');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_cred`
--

DROP TABLE IF EXISTS `user_cred`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_cred` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(10) NOT NULL,
  `Pass` varchar(60) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(60) DEFAULT NULL,
  `DOB` date NOT NULL,
  `Country` enum('Sri Lanka','Australia','China','Japan','USA') NOT NULL,
  `CityId` int(5) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `ContactNo` bigint(11) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`),
  KEY `fk_cityid_idx` (`CityId`),
  CONSTRAINT `fk_cityid` FOREIGN KEY (`CityId`) REFERENCES `city` (`CityId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_cred`
--

LOCK TABLES `user_cred` WRITE;
/*!40000 ALTER TABLE `user_cred` DISABLE KEYS */;
INSERT INTO `user_cred` VALUES (1,'kdm','202cb962ac59075b964b07152d234b70','Kasun','Madusanke','1990-10-10','Sri Lanka',1,'asdasdas@asd.com',94778587663),(3,'abc','17a196c811f636f47e7f80c4599e72e4','abc','def','1991-03-07','Japan',7,'awer@dgf.vv',94123456711),(4,'peter123','17a196c811f636f47e7f80c4599e72e4','Peter','','1995-03-02','China',8,'sfd@fik.lk',94123456711),(5,'johncarter','17a196c811f636f47e7f80c4599e72e4','John','Carter','1980-02-13','USA',10,'sfd@fik.lk',94123456715),(6,'adasd','17a196c811f636f47e7f80c4599e72e4','fgh','','1981-02-25','China',9,'sfd@fik.lk',94123456711),(7,'carl345','17a196c811f636f47e7f80c4599e72e4','Carl','','1983-01-04','Australia',13,'awer@dgf.vv',94123456711),(8,'dgf','17a196c811f636f47e7f80c4599e72e4','dsgf','','1985-06-13','Japan',6,'sfd@fik.lk',94123456711),(9,'ee','17a196c811f636f47e7f80c4599e72e4','ff','ty','1987-12-31','Sri Lanka',2,'awer@dgf.vv',94123455676),(10,'df','17a196c811f636f47e7f80c4599e72e4','hhjjhjh','try','1980-01-30','Australia',12,'sfd@fik.lk',94123456716),(11,'fdxg','17a196c811f636f47e7f80c4599e72e4','gh','','1987-06-10','Sri Lanka',2,'sfd@fik.lk',94123456711),(12,'jeanss','17a196c811f636f47e7f80c4599e72e4','Jean','','1980-04-18','China',9,'sfd@fik.lk',94123456766),(13,'dg','17a196c811f636f47e7f80c4599e72e4','hy','gg','1985-07-12','China',9,'sfd@fik.lk',94123456716),(14,'dvc','17a196c811f636f47e7f80c4599e72e4','ffgh','','1985-06-04','Japan',7,'sfd@fik.lk',94123456711),(15,'dv','17a196c811f636f47e7f80c4599e72e4','dfg','dd','1986-08-13','Australia',13,'awer@dgf.vv',94123456711),(17,'mytt','6176124a9c0108b7731f55bde88f4eac','MYT','','1995-06-14','Sri Lanka',3,'h639046@trbvm.com',94778521746);
/*!40000 ALTER TABLE `user_cred` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `UserId` int(5) NOT NULL,
  `GrpId` int(5) NOT NULL,
  PRIMARY KEY (`UserId`,`GrpId`),
  KEY `fk_usergroup$groupid_idx` (`GrpId`),
  CONSTRAINT `fk_usergroup$userid` FOREIGN KEY (`UserId`) REFERENCES `user_cred` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usergroup$groupid` FOREIGN KEY (`GrpId`) REFERENCES `group` (`GroupId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,1),(5,1),(8,1),(11,1),(15,1),(3,2),(7,2),(9,2),(13,2),(4,3),(6,3),(10,3),(12,3),(14,3),(17,3);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-11  9:47:23
