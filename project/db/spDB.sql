DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_PhieuThue`(
	IN check_MaPhieuThue INT
)
BEGIN
	SELECT EXISTS (
		SELECT *
		FROM PT_Phong PTP
		WHERE PTP.MaPhieuThue = check_MaPhieuThue
		AND (EXISTS (
			SELECT * 
			FROM Phong P
			WHERE P.MaPTPHienTai = PTP.MaPTPhong
            LIMIT 1) 
		) OR PTP.MaHoaDon IS NOT NULL)
	LIMIT 1;
END$$
DELIMITER ;

DELIMITER $$
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
END$$
DELIMITER ;

DELIMITER $$
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
END$$
DELIMITER ;

DELIMITER $$
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
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login_NhanVien`(
	IN login_TenTaiKhoan VARCHAR(45),
    IN login_MatKhau VARCHAR(45)
)
BEGIN
	SELECT EXISTS (SELECT 1
	FROM NhanVien
	WHERE TenTaiKhoan = login_TenTaiKhoan
	AND BINARY MatKhau = login_MatKhau);
END$$
DELIMITER ;

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
