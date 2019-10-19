package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	public static List<NhanVienDTO> getDSNhanVien() throws SQLException {
		return NhanVienDAO.getDSNhanVien();
	}

	public static boolean insertNhanVien(NhanVienDTO nhanVien) throws SQLException {
		if (NhanVienDAO.checkNhanVien(nhanVien)) {
			return false;
		}
		return NhanVienDAO.insertNhanVien(nhanVien);
	}

	public static Integer getMaxMaNhanVien() throws SQLException {
		return NhanVienDAO.getMaxMaNhanVien();
	}
	
	public static NhanVienDTO getNhanVienById(Integer id) throws SQLException {
		return NhanVienDAO.getNhanVienById(id);
	}

	public static NhanVienDTO getNhanVienByUsername(String username) throws SQLException {
		return NhanVienDAO.getNhanVienByUsername(username);
	}

	public static boolean updateNhanVien(NhanVienDTO nhanVien) throws SQLException {
		if (NhanVienDAO.checkNhanVien(nhanVien)) {
			return false;
		}
		return NhanVienDAO.updateNhanVien(nhanVien);
	}

	public static boolean deleteNhanVien(Integer id) throws SQLException {
		return NhanVienDAO.deleteNhanVien(id);
	}
	
	public static boolean checkLoginNhanVien(String username, String password) throws SQLException {
		return NhanVienDAO.checkLoginNhanVien(username, password);
	}
}
