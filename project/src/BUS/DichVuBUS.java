package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.DichVuDAO;
import DTO.DichVuDTO;

public class DichVuBUS {
	public static List<DichVuDTO> getDSDichVu() throws SQLException {
		return DichVuDAO.getDSDichVu();
	}
	public static boolean insertDSDichVu(DichVuDTO dichVu) throws SQLException {
		return DichVuDAO.insertDSDichVu(dichVu);
	}
}
