package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	public static List<NhanVienDTO> getDSNhanVien() throws SQLException {
		return NhanVienDAO.getDSNhanVien();
	}
	public static NhanVienDTO DangNhap(String taikhoan, String matkhau ) throws SQLException {
		return NhanVienDAO.DangNhap(taikhoan, matkhau);
	}
	public static boolean deleteNhanVien(NhanVienDTO nhanVien) throws SQLException {
		return NhanVienDAO.deleteNhanVien(nhanVien);
	}
}
