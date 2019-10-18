package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PTP_PTCKDAO;
import DTO.PTP_PTCKDTO;

public class PTP_PTCKBUS {

	public static List<PTP_PTCKDTO> getDSPTCKByMaPT(Integer maPhieuThue) throws SQLException {
		return PTP_PTCKDAO.getDSPTCKByMaPT(maPhieuThue);
	}
	
	public static boolean insertPTP_PTCK(PTP_PTCKDTO ptp_ptck) throws SQLException {
		return PTP_PTCKDAO.insertPTP_PTCK(ptp_ptck);
	}
	
	public static boolean deletePTP_PTCK(Integer maPTP_PTCK) throws SQLException {
		return PTP_PTCKDAO.deletePTP_PTCK(maPTP_PTCK);
	}
	
	public static Integer getMaxMaPTP_PTCK() throws SQLException {
		return PTP_PTCKDAO.getMaxMaPTP_PTCK();
	}
	
	public static boolean updatePTP_PTCK(PTP_PTCKDTO ptp_ptck) throws SQLException {
		return PTP_PTCKDAO.updatePTP_PTCK(ptp_ptck);
	}
}
