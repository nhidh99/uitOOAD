-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlikhachsan
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
-- Table structure for table `pt_phong`
--

DROP TABLE IF EXISTS `pt_phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pt_phong` (
  `MaPTPhong` int(11) NOT NULL AUTO_INCREMENT,
  `MaPhieuThue` int(11) DEFAULT NULL,
  `MaPhong` varchar(10) NOT NULL,
  `MaLoaiPhong` int(11) NOT NULL,
  `SoKhachToiDa` int(11) NOT NULL,
  `NgayNhan` datetime NOT NULL,
  `NgayTra` datetime NOT NULL,
  `DonGiaThue` decimal(15,0) unsigned NOT NULL,
  `TienCoc` decimal(15,0) unsigned NOT NULL,
  `MaHoaDon` int(11) DEFAULT NULL,
  `ThanhTien` decimal(15,0) unsigned DEFAULT NULL,
  `KhaDung` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaPTPhong`),
  KEY `fk_ptp_hd_idx` (`MaHoaDon`,`MaPhieuThue`),
  KEY `fk_ptp_pt_idx` (`MaPhieuThue`),
  CONSTRAINT `fk_ptp_hd` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`) ON DELETE CASCADE,
  CONSTRAINT `fk_ptp_pt` FOREIGN KEY (`MaPhieuThue`) REFERENCES `phieuthue` (`MaPhieuThue`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pt_phong`
--

LOCK TABLES `pt_phong` WRITE;
/*!40000 ALTER TABLE `pt_phong` DISABLE KEYS */;
INSERT INTO `pt_phong` VALUES (14125,16063,'101',10001,1,'2019-11-01 08:00:00','2019-11-02 12:00:00',150000,0,13005,150000,1),(14141,16076,'101',10001,1,'2019-11-01 08:00:00','2019-11-02 12:00:00',150000,0,13014,365000,1),(14145,16077,'101',10001,1,'2019-11-13 08:00:00','2019-11-14 12:00:00',150000,0,NULL,165000,1),(14154,16082,'104',10001,1,'2019-11-13 08:00:00','2019-11-14 12:00:00',150000,0,NULL,150000,0);
/*!40000 ALTER TABLE `pt_phong` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pt_phong_AFTER_UPDATE` AFTER UPDATE ON `pt_phong` FOR EACH ROW BEGIN
	IF (NEW.MaHoaDon IS NOT NULL)
    THEN
		UPDATE Phong
        SET MaTinhTrang = 11005, MaPTPHienTai = NULL
        WHERE MaPhong = NEW.MaPhong;
    END IF;
    
    IF (OLD.MaPhong != NEW.MaPhong)
    THEN
		UPDATE Phong
        SET MaTinhTrang = 11005, MaPTPHienTai = NULL
        WHERE MaPhong = OLD.MaPhong;
        
        UPDATE Phong
        SET MaTinhTrang = 11001, MaPTPHienTai = NEW.MaPTPhong
        WHERE MaPhong = NEW.MaPhong;
    END IF;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pt_phong_AFTER_DELETE` AFTER DELETE ON `pt_phong` FOR EACH ROW BEGIN
	IF NOT EXISTS (
		SELECT *
		FROM PT_Phong
        WHERE MaPhieuThue = OLD.MaPhieuThue
        LIMIT 1)
	THEN
		DELETE FROM PhieuThue
        WHERE MaPhieuThue = OLD.MaPhieuThue;
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

-- Dump completed on 2019-11-13 20:20:18
