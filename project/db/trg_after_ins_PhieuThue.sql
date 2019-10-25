CREATE DEFINER=`root`@`localhost` TRIGGER `phieuthue_AFTER_INSERT` AFTER INSERT ON `phieuthue` FOR EACH ROW BEGIN
	UPDATE PT_Phong
    SET MaPhieuThue = NEW.MaPhieuThue
    WHERE MaPhieuThue IS NULL;
END