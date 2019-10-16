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
