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
