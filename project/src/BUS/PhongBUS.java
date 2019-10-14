package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PhongDAO;
import DTO.PhongDTO;

public class PhongBUS {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		return PhongDAO.getDSPhong();
	}
}