package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.LoaiDichVuDAO;
import DTO.LoaiDichVuDTO;

public class LoaiDichVuBUS {
	public static List<LoaiDichVuDTO> getDSLoaiDichVu() throws SQLException {
		return LoaiDichVuDAO.getDSLoaiDichVu();
	}
	public static Integer getMaLoaiDichVu(String tenLoaiDichVu) throws SQLException {
		return LoaiDichVuDAO.getMaLoaiDichVu(tenLoaiDichVu);
	}
}
