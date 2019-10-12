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
