CREATE DEFINER=`root`@`localhost` TRIGGER `phieuthue_BEFORE_INSERT` BEFORE INSERT ON `phieuthue` FOR EACH ROW 
BEGIN
	SET NEW.TongTienCoc = (
		SELECT SUM(TienCoc) 
		FROM PT_Phong 
        WHERE MaPhieuThue IS NULL);
END