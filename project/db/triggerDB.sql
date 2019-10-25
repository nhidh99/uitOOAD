CREATE DEFINER=`root`@`localhost` TRIGGER `phieuthue_BEFORE_INSERT` BEFORE INSERT ON `phieuthue` FOR EACH ROW BEGIN
	SET NEW.TongTienCoc = (SELECT SUM(TienCoc) FROM PT_Phong WHERE MaPhieuThue IS NULL);
END

CREATE DEFINER=`root`@`localhost` TRIGGER `phieuthue_AFTER_INSERT` AFTER INSERT ON `phieuthue` FOR EACH ROW BEGIN
	UPDATE PT_Phong
    SET MaPhieuThue = NEW.MaPhieuThue
    WHERE MaPhieuThue IS NULL;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `pt_phong_AFTER_DELETE` AFTER DELETE ON `pt_phong` FOR EACH ROW BEGIN
	IF NOT EXISTS (
		SELECT *
		FROM PT_Phong
        WHERE MaPhieuThue = OLD.MaPhieuThue
        LIMIT 1)
	THEN
		DELETE FROM PhieuThue
        WHERE MaPhieuThue = OLD.MaPhieuThue;
	END IF;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `ptp_dv_AFTER_INSERT` AFTER INSERT ON `ptp_dv` FOR EACH ROW BEGIN
	UPDATE dichvu 
	SET SoLuongTon = IF (SoLuongTon = -1, -1, IF(SoLuongTon - NEW.SoLuong >= 0, SoLuongTon - NEW.SoLuong, SoLuongTon))
	WHERE MaDichVu = NEW.MaDichVu;
    
	UPDATE pt_phong
    SET ThanhTien = ThanhTien + NEW.SoLuong * NEW.GiaDichVu
    WHERE pt_phong.MaPTPhong = NEW.MaPTPhong;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `ptp_dv_AFTER_UPDATE` AFTER UPDATE ON `ptp_dv` FOR EACH ROW BEGIN
    UPDATE pt_phong
    SET ThanhTien = ThanhTien - (OLD.SoLuong * OLD.GiaDichVu) + (NEW.SoLuong * NEW.GiaDichVu)
    WHERE pt_phong.MaPTPhong = NEW.MaPTPhong;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `ptp_dv_AFTER_DELETE` AFTER DELETE ON `ptp_dv` FOR EACH ROW BEGIN
	UPDATE dichvu 
	SET SoLuongTon = IF (SoLuongTon = -1, -1, SoLuongTon + OLD.SoLuong)
	WHERE MaDichVu = OLD.MaDichVu;
    
	UPDATE pt_phong
    SET ThanhTien = ThanhTien - OLD.SoLuong * OLD.GiaDichVu
    WHERE pt_phong.MaPTPhong = OLD.MaPTPhong;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `ptck_phong_AFTER_INSERT` AFTER INSERT ON `ptck_phong` FOR EACH ROW BEGIN
	UPDATE pt_phong
    SET ThanhTien = ThanhTien + NEW.SoLuong * NEW.DonGia
    WHERE pt_phong.MaPTPhong = NEW.MaPTPhong;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `ptck_phong_AFTER_UPDATE` AFTER UPDATE ON `ptck_phong` FOR EACH ROW BEGIN
	UPDATE pt_phong
    SET ThanhTien = ThanhTien - (OLD.SoLuong * OLD.DonGia) + (NEW.SoLuong * NEW.DonGia)
    WHERE pt_phong.MaPTPhong = NEW.MaPTPhong;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `ptck_phong_AFTER_DELETE` AFTER DELETE ON `ptck_phong` FOR EACH ROW BEGIN
	UPDATE pt_phong
    SET ThanhTien = ThanhTien - OLD.SoLuong * OLD.DonGia
    WHERE pt_phong.MaPTPhong = OLD.MaPTPhong;
END