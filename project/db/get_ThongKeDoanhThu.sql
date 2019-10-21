DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThongKeDoanhThu`()
BEGIN
select month(NgayHoaDon) AS Thang
,SUM(TongTienPhong) AS TienPhong
,SUM(TongTienPTCK) AS TienPTCK
,SUM(TongTienDichVu) AS TienDichVu
FROM hoadon
GROUP BY month(NgayHoaDon)
ORDER BY month(NgayHoaDon) ASC;
END$$
DELIMITER ;
