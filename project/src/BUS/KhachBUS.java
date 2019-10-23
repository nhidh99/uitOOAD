package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.KhachDAO;
import DTO.KhachDTO;

public class KhachBUS {
	public static List<KhachDTO> getDSKhachByMaPTP(Integer maPTP) throws SQLException {
		return KhachDAO.getDSKhachByMaPTP(maPTP);
	}
}
