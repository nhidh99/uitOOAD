package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.DichVuDAO;
import DTO.DichVuDTO;

public class DichVuBUS {
	public static List<DichVuDTO> getDSDichVu() throws SQLException {
		return DichVuDAO.getDSDichVu();
	}
	
	public static boolean insertDichVu(DichVuDTO dichVu) throws SQLException {
		return DichVuDAO.insertDichVu(dichVu);
	}

	public static boolean updateDichVu(DichVuDTO dichVu) throws SQLException {
		return DichVuDAO.updateDichVu(dichVu);
	}
}