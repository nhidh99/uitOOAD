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
		if(PTP_DichVuDAO.checkPTP_DichVu(ptp_dichVu)) {
			return false;
		}
		return PTP_DichVuDAO.insertPTP_DichVu(ptp_dichVu);
	}

}