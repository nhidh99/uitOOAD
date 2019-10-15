package BUS;

import java.sql.SQLException;
import java.util.List;
import DAO.PTP_DichVuDAO;
import DTO.PTP_DichVuDTO;

public class PTP_DichVuBUS {
	public static List<PTP_DichVuDTO> getDSDichVuByMaPT(Integer maPhieuThue) throws SQLException {
		return PTP_DichVuDAO.getDSDichVuByMaPT(maPhieuThue);
	}
	
	public static boolean insertPTP_DichVu(PTP_DichVuDTO ptp_dichVu) throws SQLException {
		return PTP_DichVuDAO.insertPTP_DichVu(ptp_dichVu);
	}
	
	public static boolean deletePTP_DichVu(Integer maPTP_DichVu) throws SQLException {
		return PTP_DichVuDAO.deletePTP_DichVu(maPTP_DichVu);
	}
	
	public static Integer getMaxMaPTP_DichVu() throws SQLException {
		return PTP_DichVuDAO.getMaxMaPTP_DichVu();
	}
}