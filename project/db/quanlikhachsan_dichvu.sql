CREATE DATABASE  IF NOT EXISTS `quanlikhachsan` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlikhachsan`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlikhachsan
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS `dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu` (
  `MaDichVu` int(11) NOT NULL AUTO_INCREMENT,
  `TenDichVu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DonViTinh` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoLuongTon` int(10) DEFAULT NULL,
  `DonGia` decimal(15,0) NOT NULL,
  `MaLoaiDichVu` int(11) NOT NULL,
  `MaNhaCungCap` int(11) DEFAULT NULL,
  `KhaDung` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaDichVu`),
  KEY `fk_dv_ldv_idx` (`MaLoaiDichVu`),
  KEY `fk_dv_ncc_idx` (`MaNhaCungCap`),
  CONSTRAINT `fk_dv_ldv` FOREIGN KEY (`MaLoaiDichVu`) REFERENCES `loaidichvu` (`MaLoaiDichVu`) ON DELETE RESTRICT,
  CONSTRAINT `fk_dv_ncc` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19025 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

LOCK TABLES `dichvu` WRITE;
/*!40000 ALTER TABLE `dichvu` DISABLE KEYS */;
INSERT INTO `dichvu` VALUES (19004,'Coca Cola','chai',8,15000,17013,18002,1),(19005,'Bia Sài Gòn','thùng',14,200000,17013,18003,1),(19007,'Thuê xe moto','giờ',-1,15000,17015,18001,1),(19015,'Giặt sấy','kg',-1,8000,17014,18004,1),(19016,'Giặt phơi','kg',-1,5000,17014,18003,1);
/*!40000 ALTER TABLE `dichvu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-01  4:55:28
