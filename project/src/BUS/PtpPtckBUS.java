package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PtpPtckDAO;
import DTO.PtpPtckDTO;

public class PtpPtckBUS {

	public static List<PtpPtckDTO> getDSPtckByMaPTP(Integer maPTP) throws SQLException {
		return PtpPtckDAO.getDSPtckByMaPTP(maPTP);
	}

}
