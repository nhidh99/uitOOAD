package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	public static List<NhanVienDTO> getDSNhanVien() throws SQLException {
		return NhanVienDAO.getDSNhanVien();
	}
}
