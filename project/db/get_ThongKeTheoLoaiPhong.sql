DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThongKeTheoLoaiPhong`()
BEGIN
SELECT
LoaiPhongThue,
SUM(ThanhTien) AS TienPhong,
month(NgayTra) AS Thang
FROM pt_phong
GROUP BY LoaiPhongThue, month(NgayTra)
ORDER BY month(NgayTra) ASC;
END$$
DELIMITER ;
