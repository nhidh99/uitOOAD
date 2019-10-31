DELIMITER $$
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
END$$
DELIMITER ;

DELIMITER $$
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
END$$
DELIMITER ;

DELIMITER $$
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
END$$
DELIMITER ;
