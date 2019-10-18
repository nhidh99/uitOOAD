package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

public class KhachHangBUS {
	public static List<KhachHangDTO> getDSKhachHang() throws SQLException {
		return KhachHangDAO.getDSKhachHang();
	}
	
	public static boolean insertKhachHang(KhachHangDTO dichVu) throws SQLException {
		return KhachHangDAO.insertKhachHang(dichVu);
	}

	public static boolean updateKhachHang(KhachHangDTO dichVu) throws SQLException {
		return KhachHangDAO.updateKhachHang(dichVu);
	}

	public static boolean deleteKhachHang(Integer maKhachHang) throws SQLException {
		return KhachHangDAO.deleteKhachHang(maKhachHang);
	}
}