CREATE DATABASE  IF NOT EXISTS `forecasting` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `forecasting`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: 10.60.36.74    Database: forecasting
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.10.1

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
-- Table structure for table `economy`
--

DROP TABLE IF EXISTS `economy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `economy` (
  `year` int(11) NOT NULL,
  `city_gdp` double NOT NULL,
  `energy_consume_per_gdp` double NOT NULL,
  `population` double NOT NULL,
  `foreign_investment` double NOT NULL,
  `export_trade` double NOT NULL,
  `retail_sale` double NOT NULL,
  `import_export_trade` double NOT NULL,
  `industry_increment` double NOT NULL,
  `month` varchar(45) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tax` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `economy`
--

LOCK TABLES `economy` WRITE;
/*!40000 ALTER TABLE `economy` DISABLE KEYS */;
INSERT INTO `economy` VALUES (1980,311.89,6.34,1162.8,0.6307,3.65,80.43,45.06,230.87,'1',1,100.62530964495782),(1981,324.76,6.12,1163,0.1808,2.33,88.73,41.5,237.12,'2',2,111.3947128524476),(1982,337.07,6.03,1181,0.1433,3.11,89.8,38.93,240.75,'3',3,118.86884620292382),(1983,351.81,5.76,1194,0.1566,8.31,100.68,41.4,246.26,'4',4,126.42146664611592),(1984,390.85,5.88,1205,0.4189,10.93,123.72,44,263.19,'5',5,134.34453781304066),(1985,466.75,5.47,1217,1.1523,12.44,173.39,51.74,311.12,'6',6,144.49328525179422),(1986,490.83,5.67,1232,2.8584,15.43,196.84,52.04,318.89,'',7,153.11888988095924),(1987,545.46,5.4,1250,4.8568,20.13,225.25,59.96,336.54,'',8,157.8243671332669),(1988,648.3,4.6,1262,13.22,22.11,295.83,72.45,399.53,'',9,167.0471777140899),(1989,696.54,4.47,1276.44,11.8954,24.23,331.38,78.48,432.92,'',10,172.66254274731324),(1990,893.77,4.08,1283.35,35.46,29.03,333.86,74.31,446.9,'',11,186.18798560001875),(1991,893.77,3.88,1287.2,37.77,38.37,382.06,80.44,514.79,'',12,193.35018482978646),(1992,1114.32,3.28,1289.37,30.13,53.07,464.82,97.57,636.68,'',13,200.9057618029711),(1993,1519.23,2.6,1294.74,16.68,53.03,675.92,127.32,846.71,'',14,208.6749215530927),(1994,2499.43,2.12,1298.81,81.48,55.77,834.76,158.67,1074.37,'',15,216.67959686224359),(1995,2499.43,1.81,1301.37,169.88,53.49,1050.96,190.25,1298.97,'',16,223.9285898848661),(1996,2957.55,1.59,1304.43,318.77,54.14,1258,222.63,1452.79,'',17,232.60653708872783),(1997,3438.79,1.384,1305.46,296.84,56.1,1435.38,247.64,1598.91,'',18,242.87782195910236),(1998,3801.09,1.28,1306.58,296.84,60.47,1593.27,313.44,1670.19,'',19,254.0521494135236),(1999,4188.73,1.222,1474,287.28,69.72,1722.27,386.04,1787.98,'',20,260.6887428844377),(2000,4771.17,1.153,1609,161.83,101.72,1865.28,547.1,1956.7,'',21,268.8858519826578),(2001,5210.12,1.131,1614,202.63,110.71,2016.37,608.98,1924.3,'',22,274.26373588606816),(2002,5741.03,1.09,1625,215.12,137.14,2203.89,726.64,2196.2,'',23,283.2785763576267),(2003,6694.2,1.015,1711,210.53,194.17,2404.45,1123.97,2767.1,'',24,294.38065858445333),(2004,8072.83,0.917,1742,234.05,273.46,2656.91,1600.26,3427,'',25,300.8925628417957),(2005,9164.1,0.889,1778,235.61,339.11,2979.5,1863.4,3994.7,'',26,310.71296599599384),(2006,10366.37,0.873,1815,271.2,433.04,3375.2,2274.89,4575.3,'',27,317.2801879845474),(2007,12188.85,0.833,1858,262.41,539.27,3873.3,2829.73,5154.42,'',28,325.3389970254817),(2008,13698.15,0.801,1888,264.67,642.16,4577.23,3221.38,5784.99,'',29,335.78613806797665),(2009,14900.9,0.727,1921,176.15,488.62,5173.24,2777.51,7230.57,'',30,342.46506726566105),(2010,16872.4,0.712,2302.66,239.18,632.74,6070.5,3688.69,6536.21,'',31,351.3022171111917),(2011,19195.69,0.618,2347.46,158.31,771.55,6814.8,4374.36,7208.59,'',32,359.8498110781285),(2012,20181.72,0.618,2380,164.5,821.31,7412.3,4365.4,7159.4,'',33,369.500108265658),(2013,20643.23,0.655,2403,167.3,823.4,7923.1,4793.6,7243.5,'6',34,377.7358561244051);
/*!40000 ALTER TABLE `economy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-21 20:45:02
