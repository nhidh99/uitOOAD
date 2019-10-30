package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PtpDichVuDAO;
import DTO.PtpDichVuDTO;

public class PtpDichVuBUS {

	public static List<PtpDichVuDTO> getDSDichVuByMaPTP(Integer maPTP) throws SQLException {
		return PtpDichVuDAO.getDSDichVuByMaPTP(maPTP);
	}

	public static boolean insertPtpDichVu(PtpDichVuDTO ptp_dv) throws SQLException {
		return PtpDichVuDAO.insertPtpDichVu(ptp_dv);
	}

	public static boolean deletePtpDichVu(Integer maPTPDichVu) throws SQLException {
		return PtpDichVuDAO.deletePtpDichVu(maPTPDichVu);
	}

	public static boolean updatePtpDichVu(PtpDichVuDTO ptp_dv) throws SQLException {
		return PtpDichVuDAO.updatePtpDichVu(ptp_dv);
	}
}
