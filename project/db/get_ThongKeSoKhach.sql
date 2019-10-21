DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThongKeSoKhach`()
BEGIN
SELECT COUNT(MaKhachhAng) AS SoKhach,
month(NgayTra) AS Thang
FROM
	khachhang
INNER JOIN
	pt_phong using(MaPTPhong)
group by month(NgayTra)
order by month(NgayTra) asc;
END$$
DELIMITER ;
