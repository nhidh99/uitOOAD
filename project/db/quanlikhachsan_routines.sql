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
-- Temporary view structure for view `view_tkloaidichvuthang`
--

DROP TABLE IF EXISTS `view_tkloaidichvuthang`;
/*!50001 DROP VIEW IF EXISTS `view_tkloaidichvuthang`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_tkloaidichvuthang` AS SELECT 
 1 AS `TenLoaiDichVu`,
 1 AS `DoanhThu`,
 1 AS `Thang`,
 1 AS `Nam`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_dsphong`
--

DROP TABLE IF EXISTS `view_dsphong`;
/*!50001 DROP VIEW IF EXISTS `view_dsphong`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_dsphong` AS SELECT 
 1 AS `MaPhong`,
 1 AS `MaLoaiPhong`,
 1 AS `MaTinhTrang`,
 1 AS `GhiChu`,
 1 AS `TenLoaiPhong`,
 1 AS `DonGia`,
 1 AS `SoKhachToiDa`,
 1 AS `TenTinhTrang`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_dsptphong`
--

DROP TABLE IF EXISTS `view_dsptphong`;
/*!50001 DROP VIEW IF EXISTS `view_dsptphong`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_dsptphong` AS SELECT 
 1 AS `MaPTPhong`,
 1 AS `MaPhong`,
 1 AS `TenKhachThue`,
 1 AS `SoDienThoai`,
 1 AS `NgayNhan`,
 1 AS `NgayTra`,
 1 AS `DonGiaThue`,
 1 AS `TienCoc`,
 1 AS `ThanhTien`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_tkdoanhthunam`
--

DROP TABLE IF EXISTS `view_tkdoanhthunam`;
/*!50001 DROP VIEW IF EXISTS `view_tkdoanhthunam`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_tkdoanhthunam` AS SELECT 
 1 AS `DoanhThu`,
 1 AS `Thang`,
 1 AS `Nam`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_tkloaiphongthang`
--

DROP TABLE IF EXISTS `view_tkloaiphongthang`;
/*!50001 DROP VIEW IF EXISTS `view_tkloaiphongthang`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_tkloaiphongthang` AS SELECT 
 1 AS `LoaiPhongThue`,
 1 AS `TienPhong`,
 1 AS `Thang`,
 1 AS `Nam`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_dsphieuthue`
--

DROP TABLE IF EXISTS `view_dsphieuthue`;
/*!50001 DROP VIEW IF EXISTS `view_dsphieuthue`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_dsphieuthue` AS SELECT 
 1 AS `MaPhieuThue`,
 1 AS `MaNhanVien`,
 1 AS `TenNhanVien`,
 1 AS `NgayLapPhieu`,
 1 AS `TenKhachThue`,
 1 AS `SoDienThoai`,
 1 AS `CMND`,
 1 AS `Email`,
 1 AS `TongTienCoc`,
 1 AS `ThanhToanCoc`,
 1 AS `GhiChu`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_tkluongkhachnam`
--

DROP TABLE IF EXISTS `view_tkluongkhachnam`;
/*!50001 DROP VIEW IF EXISTS `view_tkluongkhachnam`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_tkluongkhachnam` AS SELECT 
 1 AS `LuongKhach`,
 1 AS `Thang`,
 1 AS `Nam`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `view_tkloaidichvuthang`
--

/*!50001 DROP VIEW IF EXISTS `view_tkloaidichvuthang`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tkloaidichvuthang` AS select `ldv`.`TenLoaiDichVu` AS `TenLoaiDichVu`,sum(`ptp_dv`.`ThanhTien`) AS `DoanhThu`,month(`hd`.`NgayHoaDon`) AS `Thang`,year(`hd`.`NgayHoaDon`) AS `Nam` from ((((`ptp_dv` join `pt_phong` `ptp` on((`ptp_dv`.`MaPTPhong` = `ptp`.`MaPTPhong`))) join `hoadon` `hd` on((`hd`.`MaHoaDon` = `ptp`.`MaHoaDon`))) join `dichvu` `dv` on((`dv`.`MaDichVu` = `ptp_dv`.`MaDichVu`))) join `loaidichvu` `ldv` on((`dv`.`MaLoaiDichVu` = `ldv`.`MaLoaiDichVu`))) group by `ldv`.`TenLoaiDichVu`,`Thang`,`Nam` order by `Nam`,`Thang` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_dsphong`
--

/*!50001 DROP VIEW IF EXISTS `view_dsphong`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_dsphong` AS select `phong`.`MaPhong` AS `MaPhong`,`phong`.`MaLoaiPhong` AS `MaLoaiPhong`,`phong`.`MaTinhTrang` AS `MaTinhTrang`,`phong`.`GhiChu` AS `GhiChu`,`loaiphong`.`TenLoaiPhong` AS `TenLoaiPhong`,`loaiphong`.`DonGia` AS `DonGia`,`loaiphong`.`SoKhachToiDa` AS `SoKhachToiDa`,`tinhtrang`.`TenTinhTrang` AS `TenTinhTrang` from ((`phong` join `loaiphong`) join `tinhtrang`) where ((`phong`.`MaLoaiPhong` = `loaiphong`.`MaLoaiPhong`) and (`phong`.`MaTinhTrang` = `tinhtrang`.`MaTinhTrang`) and (`phong`.`KhaDung` = true)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_dsptphong`
--

/*!50001 DROP VIEW IF EXISTS `view_dsptphong`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_dsptphong` AS select `ptp`.`MaPTPhong` AS `MaPTPhong`,`ptp`.`MaPhong` AS `MaPhong`,`pt`.`TenKhachThue` AS `TenKhachThue`,`pt`.`SoDienThoai` AS `SoDienThoai`,`ptp`.`NgayNhan` AS `NgayNhan`,`ptp`.`NgayTra` AS `NgayTra`,`ptp`.`DonGiaThue` AS `DonGiaThue`,`ptp`.`TienCoc` AS `TienCoc`,`ptp`.`ThanhTien` AS `ThanhTien` from (`pt_phong` `ptp` join `phieuthue` `pt` on((`pt`.`MaPhieuThue` = `ptp`.`MaPhieuThue`))) where ((`ptp`.`MaHoaDon` is null) and (`pt`.`ThanhToanCoc` = true)) order by `ptp`.`NgayNhan` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_tkdoanhthunam`
--

/*!50001 DROP VIEW IF EXISTS `view_tkdoanhthunam`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tkdoanhthunam` AS select (sum(`hoadon`.`GiaTri`) + sum(`hoadon`.`TongTienCoc`)) AS `DoanhThu`,month(`hoadon`.`NgayHoaDon`) AS `Thang`,year(`hoadon`.`NgayHoaDon`) AS `Nam` from `hoadon` group by `Thang`,`Nam` order by `Nam`,`Thang` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_tkloaiphongthang`
--

/*!50001 DROP VIEW IF EXISTS `view_tkloaiphongthang`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tkloaiphongthang` AS select `pt_phong`.`LoaiPhongThue` AS `LoaiPhongThue`,(sum(`pt_phong`.`ThanhTien`) + sum(`pt_phong`.`TienCoc`)) AS `TienPhong`,month(`pt_phong`.`NgayTra`) AS `Thang`,year(`pt_phong`.`NgayTra`) AS `Nam` from `pt_phong` where (`pt_phong`.`MaHoaDon` is not null) group by `pt_phong`.`LoaiPhongThue`,`Thang`,`Nam` order by `Nam`,`Thang` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_dsphieuthue`
--

/*!50001 DROP VIEW IF EXISTS `view_dsphieuthue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_dsphieuthue` AS select `pt`.`MaPhieuThue` AS `MaPhieuThue`,`pt`.`MaNhanVien` AS `MaNhanVien`,`nv`.`TenNhanVien` AS `TenNhanVien`,`pt`.`NgayLapPhieu` AS `NgayLapPhieu`,`pt`.`TenKhachThue` AS `TenKhachThue`,`pt`.`SoDienThoai` AS `SoDienThoai`,`pt`.`CMND` AS `CMND`,`pt`.`Email` AS `Email`,`pt`.`TongTienCoc` AS `TongTienCoc`,`pt`.`ThanhToanCoc` AS `ThanhToanCoc`,`pt`.`GhiChu` AS `GhiChu` from ((`phieuthue` `pt` join `nhanvien` `nv` on((`pt`.`MaNhanVien` = `nv`.`MaNhanVien`))) join `pt_phong` `ptp` on((`pt`.`MaPhieuThue` = `ptp`.`MaPhieuThue`))) group by `pt`.`MaPhieuThue` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_tkluongkhachnam`
--

/*!50001 DROP VIEW IF EXISTS `view_tkluongkhachnam`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tkluongkhachnam` AS select count(0) AS `LuongKhach`,month(`ptp`.`NgayNhan`) AS `Thang`,year(`ptp`.`NgayNhan`) AS `Nam` from (`pt_phong` `ptp` join `khachhang` `kh` on((`ptp`.`MaPTPhong` = `kh`.`MaPTPhong`))) group by `Thang`,`Nam` order by `Nam`,`Thang` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping events for database 'quanlikhachsan'
--

--
-- Dumping routines for database 'quanlikhachsan'
--
/*!50003 DROP PROCEDURE IF EXISTS `check_PhieuThue` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_PhieuThue`(
	IN check_MaPhieuThue INT
)
BEGIN
	SELECT EXISTS (
		SELECT *
		FROM PT_Phong PTP
        LEFT JOIN Phong P 
        ON P.MaPTPHienTai = PTP.MaPTPhong
		WHERE PTP.MaPhieuThue = check_MaPhieuThue
        AND ((EXISTS (
			SELECT * 
			FROM Phong P
            WHERE MaPTPHienTai IS NOT NULL
            LIMIT 1) 
		) OR PTP.MaHoaDon IS NOT NULL))
	LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `check_PTPhong` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_PTPhong`(
	IN check_MaPTPhong INT,
    IN check_MaPhong VARCHAR(15)
)
BEGIN
SELECT EXISTS (
	SELECT * FROM PT_Phong PTP 
	LEFT JOIN PhieuThue PT 
	ON PTP.MaPhieuThue = PT.MaPhieuThue
	WHERE MaPTPhong = check_MaPTPhong
    AND MaPhong = check_MaPhong
	AND MaHoaDon IS NULL
	AND NOT EXISTS (
		SELECT *
		FROM Phong P
		WHERE P.MaPTPHienTai = check_MaPTPhong LIMIT 1)
);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `del_DichVu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_DichVu`(
	IN del_MaDichVu INT
)
BEGIN
	IF EXISTS (
		SELECT 1 
		FROM PTP_DV
        WHERE MaDichVu = del_MaDichVu
	)
    THEN
 		UPDATE DichVu
		SET KhaDung = false
		WHERE MaDichVu = del_MaDichVu;
    ELSE
		DELETE FROM DichVu
		WHERE MaDichVu = del_MaDichVu;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `del_LoaiDichVu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_LoaiDichVu`(
	IN del_MaLoaiDichVu INT
)
BEGIN
	IF EXISTS (
		SELECT 1 
		FROM PTP_DV 
        JOIN DichVu
        ON PTP_DV.MaDichVu = DichVu.MaDichVu
        WHERE DichVu.MaLoaiDichVu = del_MaLoaiDichVu
	)
    THEN
 		UPDATE LoaiDichVu
		SET KhaDung = false
		WHERE MaLoaiDichVu = del_MaLoaiDichVu;
    ELSE
		DELETE FROM LoaiDichVu
		WHERE MaLoaiDichVu = del_MaLoaiDichVu;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `del_NhanVien` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_NhanVien`(
	IN del_MaNhanVien INT
)
BEGIN
	IF (EXISTS (
		SELECT 1
        FROM PhieuThue
        WHERE PhieuThue.MaNhanVien = del_MaNhanVien
	) OR (EXISTS (
		SELECT 1
        FROM HoaDon
        WHERE HoaDon.MaNhanVien = del_MaNhanVien)
    ))
    THEN
 		UPDATE NhanVien
		SET ChucVu = NULL
        WHERE MaNhanVien = del_MaNhanVien;
    ELSE
		DELETE FROM NhanVien
		WHERE MaNhanVien = del_MaNhanVien;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `del_Phong` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_Phong`(
	IN del_MaPhong VARCHAR(10)
)
BEGIN
	IF (EXISTS (SELECT 1
		FROM PT_Phong PTP
		WHERE PTP.MaPhong = 101 AND MaHoaDon IS NOT NULL)
		AND NOT EXISTS (SELECT 1
		FROM PT_Phong PTP
		WHERE PTP.MaPhong = 101 AND MaHoaDon IS NULL))
    THEN
		UPDATE Phong
        SET KhaDung = false
        WHERE MaPhong = del_MaPhong;
	ELSEIF NOT EXISTS (SELECT 1
		FROM PT_Phong PTP
		WHERE PTP.MaPhong = del_MaPhong)
	THEN	
		DELETE FROM Phong WHERE MaPhong = del_MaPhong;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ins_Phong` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ins_Phong`(
	IN ins_MaPhong VARCHAR(10),
    IN ins_MaLoaiPhong INT,
    IN ins_MaTinhTrang INT,
    IN ins_GhiChu VARCHAR(45)
)
BEGIN
	IF EXISTS (SELECT * FROM Phong WHERE MaPhong = ins_MaPhong AND KhaDung = false)
    THEN
		UPDATE Phong
        SET MaLoaiPhong = ins_MaLoaiPhong,
			MaTinhTrang = ins_MaTinhTrang,
			GhiChu = ins_GhiChu,
			KhaDung = true
		WHERE MaPhong = ins_MaPhong;
	ELSE
		INSERT INTO Phong (MaPhong, MaLoaiPhong, MaTinhTrang, GhiChu)
        VALUES (ins_MaPhong, ins_MaLoaiPhong, ins_MaTinhTrang, ins_GhiChu);
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login_NhanVien` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login_NhanVien`(
	IN login_TenTaiKhoan VARCHAR(45),
    IN login_MatKhau VARCHAR(45)
)
BEGIN
	SELECT EXISTS (SELECT 1
	FROM NhanVien
	WHERE TenTaiKhoan = login_TenTaiKhoan
	AND BINARY MatKhau = login_MatKhau);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `search_DSPhong` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_DSPhong`(
	IN search_NgayNhan DATETIME,
    IN search_NgayTra DATETIME,
    IN search_MaLoaiPhong INT
)
BEGIN
	SELECT 
		MaPhong, P.MaLoaiPhong, P.MaTinhTrang,
		GhiChu, TenLoaiPhong, DonGia, SoKhachToiDa, TenTinhTrang
	FROM Phong P
	JOIN LoaiPhong LP ON LP.MaLoaiPhong = P.MaLoaiPhong
	JOIN TinhTrang TT ON TT.MaTinhTrang = P.MaTinhTrang
	WHERE LP.MaLoaiPhong = search_MaLoaiPhong
    AND P.KhaDung = true
    AND (NOT EXISTS (
		SELECT *
		FROM PT_Phong PTP
		WHERE PTP.MaPhong = P.MaPhong
        AND MaHoaDon IS NULL
	) OR NOT EXISTS (
		SELECT *
        FROM PT_Phong
        WHERE ((NgayNhan BETWEEN search_NgayNhan AND search_NgayTra)
        OR (NgayTra BETWEEN search_NgayNhan AND search_NgayTra))
        AND MaPhong = P.MaPhong
	));
END ;;
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

-- Dump completed on 2019-11-01  4:55:29
