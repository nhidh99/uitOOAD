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