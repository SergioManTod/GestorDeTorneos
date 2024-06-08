-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: gestortorneos
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `responsable` int DEFAULT NULL,
  `arbitro` int DEFAULT NULL,
  `torneo` int DEFAULT NULL,
  `puntos` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `email` (`email`),
  KEY `responsable` (`responsable`),
  KEY `arbitro` (`arbitro`),
  KEY `torneo` (`torneo`),
  CONSTRAINT `equipos_ibfk_1` FOREIGN KEY (`responsable`) REFERENCES `jugadores` (`id`),
  CONSTRAINT `equipos_ibfk_2` FOREIGN KEY (`arbitro`) REFERENCES `jugadores` (`id`),
  CONSTRAINT `equipos_ibfk_3` FOREIGN KEY (`torneo`) REFERENCES `torneos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goles`
--

DROP TABLE IF EXISTS `goles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goles` (
  `partido` int NOT NULL,
  `jugador` int NOT NULL,
  `gol` int DEFAULT '0',
  PRIMARY KEY (`partido`,`jugador`),
  KEY `jugador` (`jugador`),
  CONSTRAINT `goles_ibfk_1` FOREIGN KEY (`partido`) REFERENCES `partidos` (`id`),
  CONSTRAINT `goles_ibfk_2` FOREIGN KEY (`jugador`) REFERENCES `jugadores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goles`
--

LOCK TABLES `goles` WRITE;
/*!40000 ALTER TABLE `goles` DISABLE KEYS */;
/*!40000 ALTER TABLE `goles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido1` varchar(20) DEFAULT NULL,
  `apellido2` varchar(20) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `equipo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `equipo` (`equipo`),
  CONSTRAINT `jugadores_ibfk_1` FOREIGN KEY (`equipo`) REFERENCES `equipos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partidos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `e_visitante` int DEFAULT NULL,
  `e_local` int DEFAULT NULL,
  `arbitro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `e_visitante` (`e_visitante`),
  KEY `e_local` (`e_local`),
  KEY `arbitro` (`arbitro`),
  CONSTRAINT `partidos_ibfk_1` FOREIGN KEY (`e_visitante`) REFERENCES `equipos` (`id`),
  CONSTRAINT `partidos_ibfk_2` FOREIGN KEY (`e_local`) REFERENCES `equipos` (`id`),
  CONSTRAINT `partidos_ibfk_3` FOREIGN KEY (`arbitro`) REFERENCES `jugadores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneos`
--

DROP TABLE IF EXISTS `torneos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `torneos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `numero_jugadores` int DEFAULT NULL,
  `numero_equipos` int DEFAULT NULL,
  `estaActivo` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneos`
--

LOCK TABLES `torneos` WRITE;
/*!40000 ALTER TABLE `torneos` DISABLE KEYS */;
INSERT INTO `torneos` VALUES (1,'sergio',10,3,0),(2,'dedo',14,4,0),(3,'marianico el corto',15,5,0),(4,'mmmmm',10,3,0),(5,'fin de año',10,3,0),(6,'kjkjk',10,3,0),(7,'ñlñl',10,3,0),(8,'ñlollklkl',10,3,0),(9,'Torneo de fin de curso',20,10,0),(10,'bienvenido',10,3,0),(11,'sergiommmm',5,3,0),(12,'nuevo torneo a dehora',5,3,0),(13,'este',15,3,0),(14,'Chunguitos',5,3,0),(15,'Chamberi',5,3,0),(16,'maravillas',5,3,0),(17,'piratas',5,3,0),(18,'piratillas',5,3,0),(19,'eunucos',5,3,0),(20,'jun',5,3,0),(21,'juno',5,3,0),(22,'martin',5,3,0),(23,'ko',5,3,0),(24,'ok',5,3,0),(25,'sergioi',5,3,0),(26,'h',5,3,0),(27,'la piara dulce del verano azul',5,3,0),(28,'sergiob',5,3,0),(29,'MARISTAS',5,3,0),(30,'hjgjkhghghg',5,3,0),(31,'kkkkkkkkkkkkkkkk',5,3,0),(32,'sergiolklklklklk',5,3,0),(33,'gjgfhfhgfh',5,3,0),(34,'sergio3',5,3,0),(35,'maicol',5,3,0);
/*!40000 ALTER TABLE `torneos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-08  9:52:03
