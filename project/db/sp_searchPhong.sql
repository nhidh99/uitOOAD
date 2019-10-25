DELIMITER $$
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
    AND (NOT EXISTS (
		SELECT *
		FROM PT_Phong PTP
		WHERE PTP.MaPhong = P.MaPhong
	) OR NOT EXISTS (
		SELECT *
        FROM PT_Phong
        WHERE (NgayNhan BETWEEN search_NgayNhan AND search_NgayTra)
        OR (NgayTra BETWEEN search_NgayNhan AND search_NgayTra)
	));
END$$
DELIMITER ;
