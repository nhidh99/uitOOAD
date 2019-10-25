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