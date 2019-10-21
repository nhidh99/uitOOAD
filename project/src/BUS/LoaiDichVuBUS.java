package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.LoaiDichVuDAO;
import DTO.LoaiDichVuDTO;

public class LoaiDichVuBUS {
	public static List<LoaiDichVuDTO> getDSLoaiDichVu() throws SQLException {
		return LoaiDichVuDAO.getDSLoaiDichVu();
	}

	public static boolean deleteLoaiDichVu(Integer maLoaiDichVu) throws SQLException {
		if (LoaiDichVuDAO.checkLoaiDichVu(maLoaiDichVu)) {
			return false;
		}
		return LoaiDichVuDAO.deleteLoaiDichVu(maLoaiDichVu);
	}
}
