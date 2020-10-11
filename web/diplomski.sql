-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: diplomski
-- ------------------------------------------------------
-- Server version	5.7.27-log

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
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikal` (
  `slika` varchar(150) NOT NULL,
  `kategorija` varchar(60) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `naziv` varchar(60) DEFAULT NULL,
  `cena` float DEFAULT NULL,
  PRIMARY KEY (`slika`),
  UNIQUE KEY `slika_UNIQUE` (`slika`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dostava`
--

DROP TABLE IF EXISTS `dostava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dostava` (
  `sifra` int(11) NOT NULL,
  `nacin` varchar(60) DEFAULT NULL,
  `ulica` varchar(60) DEFAULT NULL,
  `broj` varchar(60) DEFAULT NULL,
  `grad` varchar(60) DEFAULT NULL,
  `telefon` int(18) DEFAULT NULL,
  `cena` float DEFAULT NULL,
  PRIMARY KEY (`sifra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `username` varchar(60) NOT NULL,
  `ime` varchar(60) DEFAULT NULL,
  `prezime` varchar(60) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `pol` varchar(1) DEFAULT NULL,
  `pitanje` varchar(200) DEFAULT NULL,
  `odgovor` varchar(100) DEFAULT NULL,
  `tip` varchar(1) DEFAULT NULL,
  `prihvacen` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `korpa`
--

DROP TABLE IF EXISTS `korpa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korpa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artikal` varchar(160) DEFAULT NULL,
  `slika` varchar(180) DEFAULT NULL,
  `kupac` varchar(60) DEFAULT NULL,
  `stanje` varchar(1) DEFAULT NULL,
  `obradjeno` varchar(1) DEFAULT NULL,
  `cena` float DEFAULT NULL,
  `natpis` varchar(150) DEFAULT NULL,
  `boja` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `narudzbina`
--

DROP TABLE IF EXISTS `narudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `narudzbina` (
  `id` int(11) NOT NULL,
  `sifra` int(11) DEFAULT NULL,
  `korisnik` varchar(60) DEFAULT NULL,
  `suma` float DEFAULT NULL,
  `artikal` varchar(150) DEFAULT NULL,
  `slika` varchar(150) DEFAULT NULL,
  `natpis` varchar(150) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slika`
--

DROP TABLE IF EXISTS `slika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slika` (
  `slika` varchar(150) NOT NULL,
  `naziv` varchar(60) DEFAULT NULL,
  `umetnik` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`slika`),
  UNIQUE KEY `slika_UNIQUE` (`slika`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-22 15:23:36
