package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

public class NhaCungCapBUS {
	public static List<NhaCungCapDTO> getDSNhaCungCap() throws SQLException {
		return NhaCungCapDAO.getDSNhaCungCap();
	}
}
