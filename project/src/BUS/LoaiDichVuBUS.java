package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.LoaiDichVuDAO;
import DTO.LoaiDichVuDTO;

public class LoaiDichVuBUS {
	public static List<LoaiDichVuDTO> getDSLoaiDichVu() throws SQLException {
		return LoaiDichVuDAO.getDSLoaiDichVu();
	}
	
	public static boolean updateLoaiDichVu(String tenLoaiDichVuCu, String tenLoaiDichVuMoi) throws SQLException {
		if(LoaiDichVuDAO.checkLoaiDichVuTonTai(tenLoaiDichVuMoi))
			return false;
		else
			return LoaiDichVuDAO.updateLoaiDichVu(tenLoaiDichVuCu, tenLoaiDichVuMoi);
	}
}
