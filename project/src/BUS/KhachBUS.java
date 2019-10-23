package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.KhachDAO;
import DTO.KhachDTO;

public class KhachBUS {
	public static List<KhachDTO> getDSKhachByMaPTP(Integer maPTP) throws SQLException {
		return KhachDAO.getDSKhachByMaPTP(maPTP);
	}

	public static boolean insertKhach(KhachDTO khach) throws SQLException {
		return KhachDAO.insertKhach(khach);
	}

	public static boolean deleteKhach(Integer maKhachHang) throws SQLException {
		return KhachDAO.deleteKhach(maKhachHang);
	}

	public static boolean updateKhach(KhachDTO khach) throws SQLException {
		return KhachDAO.updateKhach(khach);
	}
}
