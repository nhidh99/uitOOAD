DROP TRIGGER IF EXISTS `quanlikhachsan`.`pt_phong_AFTER_UPDATE`;

DELIMITER $$
USE `quanlikhachsan`$$
CREATE DEFINER = CURRENT_USER TRIGGER `quanlikhachsan`.`pt_phong_AFTER_UPDATE` AFTER UPDATE ON `pt_phong` FOR EACH ROW
BEGIN
	DECLARE MaPTPhongHienTai INT;
	SELECT MaPTPhong INTO MaPTPhongHienTai FROM PT_Phong WHERE MaPhong = NEW.MaPhong;
    
	UPDATE Phong SET MaTinhTrang = '11002', MaPTPHienTai = null WHERE MaPhong = OLD.MaPhong; 
    UPDATE Phong SET MaTinhTrang = '11001', MaPTPHienTai = MaPTPhongHienTai WHERE MaPhong = NEW.MaPhong;

END$$
DELIMITER ;
