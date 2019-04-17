-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: localhost    Database: SchoolBus
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus` (
  `busID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '校车id',
  `routeID` int(11) DEFAULT NULL COMMENT '线路id ',
  `driverID` int(11) DEFAULT NULL COMMENT '司机id',
  `position` varchar(50) DEFAULT NULL COMMENT '坐标',
  `licence` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `isDepart` tinyint(1) DEFAULT NULL COMMENT '是否发车 0: 没发车 1:发车',
  `currentStopIndex` int(11) DEFAULT NULL COMMENT '当前到站序号',
  `currentStopTime` varchar(50) DEFAULT NULL COMMENT '当前到站时间',
  `currentPositionTime` varchar(50) DEFAULT NULL COMMENT '获取坐标时时间',
  `directionName` varchar(50) DEFAULT NULL COMMENT '方向名',
  `direction` int(11) DEFAULT NULL COMMENT '0或者1',
  `lat` varchar(50) DEFAULT NULL,
  `lng` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`busID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,3,1,NULL,'浙A88888',1,NULL,'2018-12-11 14:50:01',NULL,NULL,0,NULL,NULL),(2,4,2,NULL,'浙A66666',1,NULL,NULL,NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `collect` (
  `collectID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `routeID` int(11) DEFAULT NULL,
  `direction` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`collectID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `driver` (
  `dirverID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '司机ID',
  `driverTelephone` varchar(50) DEFAULT NULL COMMENT '司机联系方式',
  `dirverAccount` varchar(50) DEFAULT NULL COMMENT '司机账号',
  `dirverPassword` varchar(50) DEFAULT NULL COMMENT '司机密码',
  `busID` int(11) DEFAULT NULL,
  `driverName` varchar(50) DEFAULT NULL COMMENT '司机名',
  `licence` varchar(50) DEFAULT NULL COMMENT '司机车牌',
  `driverSex` int(1) DEFAULT NULL COMMENT '司机性别 0:女性 1:男性',
  `driverBirthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dirverID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'201626811302','201626811302','123456',1,'陈冠州','浙A88888',1,NULL),(2,'201626810302','201626810302','123456',2,'柴嘉明','浙A66666',1,NULL);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presentDriver`
--

DROP TABLE IF EXISTS `presentDriver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `presentDriver` (
  `dirverID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`dirverID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentDriver`
--

LOCK TABLES `presentDriver` WRITE;
/*!40000 ALTER TABLE `presentDriver` DISABLE KEYS */;
/*!40000 ALTER TABLE `presentDriver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `route` (
  `routeID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '线路id',
  `routeName` varchar(50) DEFAULT NULL COMMENT '线路名',
  `byName` varchar(50) DEFAULT NULL COMMENT '别名',
  `startStop` varchar(50) DEFAULT NULL COMMENT '起点站',
  `endStop` varchar(50) DEFAULT NULL COMMENT '终点站',
  `stopList` varchar(500) DEFAULT NULL COMMENT '站牌列表',
  `startTime` varchar(50) DEFAULT NULL COMMENT '首班车',
  `endTime` varchar(50) DEFAULT NULL COMMENT '末班车',
  `price` varchar(50) DEFAULT NULL COMMENT '票价',
  `direction` tinyint(1) DEFAULT NULL COMMENT '方向. 0:forward 1:reserve',
  PRIMARY KEY (`routeID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (0,'1号线','物美线','{forward: \'邵科馆\', reverse: \'语林楼\'}','{ forward: \'语林楼\', reverse: \'邵科馆\'}','{ forward: [\"邵科馆\", \"德胜路东粮泊巷口公交站\", \"白荡海公交站\", \"文一路学院路口公交站\", \"物美大卖场\", \"益乐新村公交站\", \"雅仕苑公交站\", \"高教新村\", \"屏峰东门\", \"语林楼\"],reverse: [\'语林楼\', \'西溪医院横街公交站\', \'府苑新村公交站\', \'高教新村\', \'雅仕苑公交站\', \'益乐新村公交站\', \'物美大卖场\', \'文一路学院路口公交站\', \'白荡海公交站\', \'德胜路东粮泊巷口公交站\', \'朝晖校区南大门\', \'邵科馆\']}\n','8:20','17:10','4',0),(1,'2号线','丰潭路线','{forward: \'邵科馆\', reverse: \'语林楼\'}','{ forward: \'语林楼\', reverse: \'邵科馆\'}','{ forward: [\'邵科馆\', \'市交警支队公交站\', \'文三路马腾路口公交站\', \'上宁桥公交站\', \'古荡新村西公交站\', \'文三西路丰潭路口公交站\', \'金欣公寓\', \'平安桥公交站\',\'留和路北口\',\'屏峰东门\', \'语林楼\'], reverse: [\'语林楼\', \'西溪医院横街公交站\', \'留和路北口\', \'府苑新村公交站\', \'金欣公寓\', \'文三西路丰潭路口公交站\', \'文二西路丰潭路口公交站\', \'文二西路东口公交站\', \'节能公司公交站\',\'下宁桥公交站\',\'文二路马腾路口公交站\',\'朝晖九区公交站\', \'朝晖校区南大门\',\'邵科馆\']}','6:50','17:10','4',0),(2,'3号线','文二路线','{forward: \'邵科馆\', reverse: \'语林楼\'}','{ forward: \'语林楼\', reverse: \'邵科馆\'}','{ forward: [\'邵科馆\', \'德胜路东粮泊巷口公交站\', \'白荡海公交站\', \'文一路学院路口公交站\', \'文二西路东口公交站\', \'文二西路丰潭路口公交站\', \'雅仕苑公交站\', \'高教新村\', \'屏峰东门\', \'语林楼\'], reverse: [\'语林楼\', \'西溪医院横街公交站\', \'府苑新村公交站\', \'高教新村\', \'文二西路丰潭路口公交站\', \'文二西路东口公交站\', \'下宁桥公交站\', \'文二路马腾路口公交站\', \'朝晖九区公交站\', \'朝晖校区南大门\',\'邵科馆\']}','7:00','18:00','4',0),(3,'4号线','城西线','{forward: \'白荡海公交站\', reverse: \'\'}','{ forward: \'语林楼\', reverse: \'\'}','{ forward: [\'白荡海公交站\', \'文一路学院路口公交站\', \'物美大卖场\', \'嘉绿农贸市场\', \'益乐新村公交站\', \'雅仕苑公交站\',\'高教新村\', \'屏峰东门\', \'语林楼\'], reverse: [] }','8:10','18:45','4',0),(4,'5号线','东新园线','{forward: \'东新园\', reverse: \'语林楼\'}','{ forward: \'语林楼\', reverse: \'东新路口\'}','{ forward: [\'东新园公交站\', \'三塘小区公交站\', \'大关小区公交站\', \'德胜路东粮泊巷口公交站\', \'屏峰东门\', \'语林楼\'], reverse: [\'语林楼\', \'西溪医院横街公交站\', \'高教新村\', \'雅仕苑公交站\', \'益乐新村公交站\', \'物美大卖场\', \'文一路学院路口公交站\', \'白荡海公交站\', \'德胜路东粮泊巷口公交站\', \'大关小区公交站\', \'三塘小区公交站\',\'东新园公交站\']}','9:00','19:50','4',0),(5,'6号线','翰墨香林线','{forward: \'邵科馆\', reverse: \'翰墨香林\'}','{ forward: \'翰墨香林\', reverse: \'邵科馆\'}','{ forward: [\'邵科馆\', \'屏峰东门\', \'语林楼\', \'翰墨香林\'], reverse: [\'翰墨香林\', \'语林楼\', \'西溪医院横街公交站\', \'邵科馆\']}','7:00','19:25','4',0),(6,'直达','直达车','{forward: \'邵科馆\', reverse: \'语林楼\'}','{ forward: \'语林楼\', reverse: \'邵科馆\'}','{ forward: [\'邵科馆\',\'语林楼\'], reverse: [\'语林楼\',\'邵科馆\'] }','6:50','20:30','4',0);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stop`
--

DROP TABLE IF EXISTS `stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stop` (
  `stopID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stopName` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `relateRouteID` varchar(50) DEFAULT NULL,
  `lng` varchar(50) DEFAULT NULL,
  `lat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stopID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stop`
--

LOCK TABLES `stop` WRITE;
/*!40000 ALTER TABLE `stop` DISABLE KEYS */;
INSERT INTO `stop` VALUES (1,'邵科馆','[120.1481145913093,30.279039856972628]','[0,1,2,5]','120.1481145913093','30.279039856972627'),(2,'德胜路东粮泊巷口公交站','[120.16661001186111,30.301120805878268]','[0,2,4]','120.16661001186111','30.301120805878266'),(3,'白荡海公交站','[120.14904716717447,30.29510438997569]','[0,2,3,4]','120.14904716717447','30.29510438997569'),(4,'文一路学院路口公交站','[120.131903,30.2882]','[0,2,3,4]','120.131903','30.2882'),(5,'物美大卖场','[120.12902797973188,30.294267008770409]','[0,3,4]','120.12902797973187','30.294267008770408'),(6,'益乐新村公交站','[120.11650344678617,30.29512407386264]','[0,3,4]','120.11650344678617','30.29512407386264'),(7,'雅仕苑公交站','[120.106031,30.286849]','[0,2,3,4]','120.106031','30.286849'),(8,'高教新村','[120.095741,30.286633]','[0,2,3,4]','120.095741','30.286633'),(9,'屏峰东门','[120.041359,30.225431]','[0,1,2,3,4,5]','120.041359','30.225431'),(10,'语林楼','[120.037721,30.224073]','[0,1,2,3,4,5]','120.037721','30.22407'),(11,'西溪医院横街公交站','[120.049612,30.230968]','[0,1,2,4,5]','120.049612','30.230968'),(12,'府苑新村公交站','[120.096764,30.266903]','[0,1,2]','120.096764','30.266903'),(13,'朝晖校区南大门','[120.167051,30.290214]','[0,1,2]','120.167051','30.290214'),(14,'市交警支队公交站','[120.16381,30.280726]','[1]','120.16381','30.280726'),(15,'文三路马腾路口公交站','[120.145999,30.277691]','[1]','120.145999','30.277691'),(16,'上宁桥公交站','[120.137448,30.277227]','[1]','120.137448','30.277227'),(17,'古荡新村西公交站','[120.112578,30.276225]','[1]','120.112578','30.276225'),(18,'文三西路丰潭路口公交站','[120.108622,30.276087]','[1]','120.108622','30.276087'),(19,'金欣公寓','[120.100863,30.274959]','[1]','120.100863','30.274959'),(20,'平安桥公交站','[120.101592,30.268992]','[1]','120.101592','30.268992'),(21,'留和路北口','[120.053546,30.246358]','[1]','120.053546','30.246358'),(22,'文二西路丰潭路口公交站','[120.110046,30.279046]','[1,2]','120.110046','30.279046'),(23,'文二西路东口公交站','[120.114884,30.281945]','[1,2]','120.114884','30.281945'),(24,'节能公司公交站','[120.122733,30.282409]','[1]','120.122733','30.282409'),(25,'文二路马腾路口公交站','[120.14535,30.283546]','[1,2]','120.14535','30.283546'),(26,'朝晖九区公交站','[120.15752,30.287753]','[1,2]','120.15752','30.287753'),(27,'嘉绿农贸市场','[120.114208,30.279538]','[3]','120.114208','30.279538'),(28,'东新园公交站','[120.18306,30.309588]','[4]','120.18306','30.309588'),(29,'三塘小区公交站','[120.168929,30.307072]','[4]','120.168929','30.307072'),(30,'大关小区公交站','[120.158409,30.303413]','[4]','120.158409','30.303413'),(31,'翰墨香林','[120.019239,30.210142]','[5]','120.019239','30.210142'),(32,'下宁桥公交站','[120.137398,30.283178]','[1,2]','120.137398','30.283178');
/*!40000 ALTER TABLE `stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subscribe` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `busID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `RouteID` int(11) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `StopName` varchar(50) DEFAULT NULL,
  `StopIndex` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userAccount` varchar(50) DEFAULT NULL COMMENT '用户名',
  `userPassword` varchar(50) DEFAULT NULL COMMENT '密码',
  `userTelephone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `userEmail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `userName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `userPosition` varchar(50) DEFAULT NULL COMMENT '用户坐标',
  `collectRouteID` varchar(50) DEFAULT NULL COMMENT '用户收藏ID',
  `userSex` int(1) DEFAULT NULL COMMENT '用户性别 0:女性 1:男性',
  `userBirthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'201626811302','123456','201626811031','763483718@qq.com','郑安琪',NULL,NULL,1,NULL),(6,'201526811300,15869003586','123456','201526811300,15869003586',NULL,NULL,NULL,NULL,NULL,NULL);
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

-- Dump completed on 2018-12-30 19:14:54
