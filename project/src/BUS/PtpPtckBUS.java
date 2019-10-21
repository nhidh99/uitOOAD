package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PtpPtckDAO;
import DTO.PtpPtckDTO;

public class PtpPtckBUS {

	public static List<PtpPtckDTO> getDSPtckByMaPTP(Integer maPTP) throws SQLException {
		return PtpPtckDAO.getDSPtckByMaPTP(maPTP);
	}

	public static boolean insertPtpPtck(PtpPtckDTO ptck) throws SQLException {
		return PtpPtckDAO.insertPtpPtcK(ptck);
	}

	public static boolean deletePtpPtck(Integer maPTCKPhong) throws SQLException {
		return PtpPtckDAO.deletePtpPtck(maPTCKPhong);
	}

	public static boolean updatePtpPtck(PtpPtckDTO ptck) throws SQLException {
		return PtpPtckDAO.updatePtpPtck(ptck);
	}
}
