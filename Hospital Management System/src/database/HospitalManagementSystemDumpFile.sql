CREATE DATABASE  IF NOT EXISTS `hospitalmanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hospitalmanagementsystem`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitalmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `AppointmentID` int NOT NULL AUTO_INCREMENT,
  `DoctorID` int NOT NULL,
  `PatientID` int NOT NULL,
  `Date & Time` datetime DEFAULT CURRENT_TIMESTAMP,
  `PatientName` varchar(100) NOT NULL,
  `DoctorName` varchar(100) NOT NULL,
  PRIMARY KEY (`AppointmentID`),
  UNIQUE KEY `unique_appointment` (`PatientID`,`DoctorID`,`Date & Time`),
  KEY `fk_patient_name` (`PatientName`),
  KEY `fk_doctor_name` (`DoctorName`),
  KEY `appointments_ibfk_1` (`DoctorID`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`DoctorID`) REFERENCES `doctors` (`DoctorID`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`PatientID`) REFERENCES `patients` (`PatientID`),
  CONSTRAINT `fk_doctor_name` FOREIGN KEY (`DoctorName`) REFERENCES `doctors` (`Name`),
  CONSTRAINT `fk_patient_name` FOREIGN KEY (`PatientName`) REFERENCES `patients` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (3,1001,101,'2001-01-02 00:00:00','kendal','kim'),(8,1001,105,'2002-02-02 00:00:00','Tom','kim'),(9,1001,105,'2002-02-12 00:00:00','Tom','Newton'),(10,1,102,'1991-12-11 00:00:00','kendal','Albert Einstein'),(102,1,101,'2024-08-18 18:42:31','Killiey','Albert Einstein'),(106,1,101,'2024-08-18 18:45:15','killiey','Albert Einstein');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billings`
--

DROP TABLE IF EXISTS `billings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billings` (
  `BillNo` int NOT NULL AUTO_INCREMENT,
  `PatientID` int NOT NULL,
  `Date & Time` datetime DEFAULT CURRENT_TIMESTAMP,
  `TotalAmount` decimal(10,2) NOT NULL,
  `PatientName` varchar(20) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`BillNo`),
  KEY `fk_name` (`PatientName`),
  KEY `billings_ibfk_1` (`PatientID`),
  CONSTRAINT `billings_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `patients` (`PatientID`),
  CONSTRAINT `fk_name` FOREIGN KEY (`PatientName`) REFERENCES `patients` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billings`
--

LOCK TABLES `billings` WRITE;
/*!40000 ALTER TABLE `billings` DISABLE KEYS */;
INSERT INTO `billings` VALUES (1001,101,'2004-10-20 00:00:00',100000.00,'killiey',NULL),(1002,102,'2004-12-02 00:00:00',1000.00,'kendal',NULL),(1003,103,'2001-12-13 00:00:00',200000.00,'Tommithee',NULL),(1004,101,'1999-12-12 00:00:00',34000.00,'killiey',NULL),(1006,101,'2024-08-31 16:53:32',1121.00,'Killiey',NULL),(1007,101,'2024-08-31 16:54:32',121212.00,'killiey',NULL),(1008,101,'2024-08-31 16:54:34',121212.00,'killiey',NULL);
/*!40000 ALTER TABLE `billings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `DoctorID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Speciality` varchar(100) NOT NULL,
  PRIMARY KEY (`DoctorID`),
  KEY `idx_doctors_name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'Albert Einstein',99,'male','theoryofrelativity'),(2,'Newton',99,'male','cardiovascularity'),(3,'Manikshaw',29,'mal','cardio'),(1001,'kim',21,'female','ererer'),(1002,'Sarukh khan',55,'Male','Cardiovasularity'),(1003,'Subodh',54,'Male','80085'),(1006,'Rabdan',44,'Male','Cardiovascular');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `PatientID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(100) DEFAULT NULL,
  `Illness` varchar(255) NOT NULL,
  PRIMARY KEY (`PatientID`),
  KEY `idx_patients_name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (101,'killiey',19,'female','hotness'),(102,'kendal',19,'female','special'),(103,'Tommithee',19,'male','sdf'),(104,'Zendeya ',19,'female','challengers'),(105,'Tom',34,'male','sfagag'),(106,'Radha',23,'javax.swing.JToggleButton$ToggleButtonModel@2be49c72','ram'),(108,'Rajeev',12,'Rather not say','Heartbrokened'),(109,'Rajeev',12,'Rather not say','heartbreakingfkd'),(110,'demo',12,NULL,'gag'),(112,'Sabudin Miya',45,'Male','Honesty');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receptionists`
--

DROP TABLE IF EXISTS `receptionists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receptionists` (
  `receptionist_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `hire_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `address` text,
  PRIMARY KEY (`receptionist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receptionists`
--

LOCK TABLES `receptionists` WRITE;
/*!40000 ALTER TABLE `receptionists` DISABLE KEYS */;
INSERT INTO `receptionists` VALUES (1,'Bailey','9898','agag@gmail.com','2024-08-31 18:07:26','kurintar');
/*!40000 ALTER TABLE `receptionists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `role` enum('receptionist','patient','doctor','admin') NOT NULL DEFAULT 'patient',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Raheem','fuckoff','2024-08-16 16:29:58','receptionist'),(2,'Doe','Doe123','2024-08-23 14:18:44','admin'),(3,'John','John123','2024-08-23 14:19:09','doctor'),(4,'Cena','Cena123','2024-09-05 05:29:27','patient');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hospitalmanagementsystem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-07 21:22:21
