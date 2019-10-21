DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThongKeTheoLoaiDichVu`()
BEGIN
SELECT SUM(ptp_dv.ThanhTien) AS DoanhThu,
month(NgayTra) AS Thang,
TenLoaiDichVu
FROM ptp_dv
INNER JOIN dichvu USING (MaDichVu)
INNER JOIN pt_phong USING (MaPTPhong)
INNER JOIN loaidichvu USING (MaLoaiDichVu)
GROUP BY TenLoaiDichVu;
END$$
DELIMITER ;
