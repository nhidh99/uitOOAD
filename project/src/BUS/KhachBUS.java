package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.KhachDAO;
import DTO.KhachDTO;

public class KhachBUS {
	public static List<KhachDTO> getDSKhach(Integer maPhieuThue) throws SQLException {
		return KhachDAO.getDSKhach(maPhieuThue);
	}
	
	public static boolean insertKhach(KhachDTO khach) throws SQLException {
		return KhachDAO.insertKhach(khach);
	}

	public static boolean updateKhach(KhachDTO khach) throws SQLException {
		return KhachDAO.updateKhach(khach);
	}

	public static boolean deleteKhach(Integer maKhach) throws SQLException {
		return KhachDAO.deleteKhach(maKhach);
	}
	
	public static List<KhachDTO> searchKhach(String hoten, String cmnd) throws SQLException {
		return KhachDAO.searchKhach(hoten, cmnd);
	}
}