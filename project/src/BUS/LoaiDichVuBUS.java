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
		return LoaiDichVuDAO.deleteLoaiDichVu(maLoaiDichVu);
	}

	public static LoaiDichVuDTO getLoaiDichVuById(Integer maLoaiDichVu) throws SQLException {
		return LoaiDichVuDAO.getLoaiDichVuById(maLoaiDichVu);
	}

	public static boolean insertLoaiDichVu(LoaiDichVuDTO loaiDichVu) throws SQLException {
		if (LoaiDichVuDAO.checkLoaiDichVu(loaiDichVu.getTenLoaiDichVu())) {
			return false;
		}
		return LoaiDichVuDAO.insertLoaiDichVu(loaiDichVu);
	}

	public static boolean updateLoaiDichVu(LoaiDichVuDTO loaiDichVu) throws SQLException {
		if (LoaiDichVuDAO.checkLoaiDichVu(loaiDichVu.getTenLoaiDichVu())) {
			return false;
		}
		return LoaiDichVuDAO.updateLoaiDichVu(loaiDichVu);
	}
}
