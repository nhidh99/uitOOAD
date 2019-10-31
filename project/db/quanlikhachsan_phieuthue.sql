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
-- Table structure for table `phieuthue`
--

DROP TABLE IF EXISTS `phieuthue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuthue` (
  `MaPhieuThue` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVien` int(11) NOT NULL,
  `NgayLapPhieu` date NOT NULL,
  `TenKhachThue` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CMND` varchar(45) NOT NULL,
  `SoDienThoai` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `ThanhToanCoc` tinyint(1) NOT NULL DEFAULT '0',
  `TongTienCoc` decimal(15,0) unsigned NOT NULL DEFAULT '0',
  `GhiChu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaPhieuThue`),
  KEY `fk_pt_nv_idx` (`MaNhanVien`),
  CONSTRAINT `fk_pt_nv` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB AUTO_INCREMENT=16077 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuthue`
--

LOCK TABLES `phieuthue` WRITE;
/*!40000 ALTER TABLE `phieuthue` DISABLE KEYS */;
INSERT INTO `phieuthue` VALUES (16052,12001,'2019-10-25','Hồ Thanh Hải','17520853','0336887109','haiht@gmail.com',1,0,NULL),(16053,12001,'2019-10-26','Đinh Hoàng Nhung','28521427','0931931813','nhungdh@gmail.com',1,150000,NULL),(16058,12001,'2019-10-29','dg','fdg','fdgfd','fdgfdg',1,0,NULL),(16063,12001,'2019-10-31','Hồ Hoàng Hải','036115478','09528738','haihh@gmail.com',1,0,NULL),(16065,12001,'2019-11-01','sd','fdsfsd','fsd','fsd',1,75000,NULL),(16068,12001,'2019-11-01','absa','3213','12313','2131',1,0,NULL),(16069,12001,'2019-11-01','sad','12321','21321','dasdsa',1,0,NULL),(16076,12001,'2019-11-01','das','123','123','32',1,0,NULL);
/*!40000 ALTER TABLE `phieuthue` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `phieuthue_BEFORE_INSERT` BEFORE INSERT ON `phieuthue` FOR EACH ROW BEGIN
	SET NEW.TongTienCoc = (SELECT SUM(TienCoc) FROM PT_Phong WHERE MaPhieuThue IS NULL);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `phieuthue_AFTER_INSERT` AFTER INSERT ON `phieuthue` FOR EACH ROW BEGIN
	UPDATE PT_Phong
    SET MaPhieuThue = NEW.MaPhieuThue
    WHERE MaPhieuThue IS NULL;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `phieuthue_AFTER_DELETE` AFTER DELETE ON `phieuthue` FOR EACH ROW BEGIN
	IF (OLD.ThanhToanCoc = true AND OLD.TongTienCoc > 0)
    THEN
		INSERT INTO HoaDon (MaNhanVien, NgayHoaDon, TenKhachTra, CMND,
        SoDienThoai, Email, TongTienPhong, TongTienPTCK, TongTienCoc,
        GiaTri, TienNhan, TienThua, GhiChu)
        VALUES (OLD.MaNhanVien, OLD.NgayLapPhieu, OLD.TenKhachThue,
        OLD.CMND, OLD.SoDienThoai, OLD.Email, 0, 0, OLD.TongTienCoc,
        0, 0, 0, 'Huỷ đặt phòng');
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-01  4:55:26
