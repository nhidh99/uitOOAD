package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PTPhongDAO;
import DTO.PTPhongDTO;

public class PTPhongBUS {

	public static boolean insertPTPhong(PTPhongDTO ptp) throws SQLException {
		return PTPhongDAO.insertPTPhong(ptp);
	}
	
	public static List<PTPhongDTO> getDSPhongDangKy() throws SQLException {
		return PTPhongDAO.getDSPhongDangKy();
	}
}
