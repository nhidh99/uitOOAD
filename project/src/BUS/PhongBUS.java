package BUS;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import DAO.PhongDAO;
import DTO.PhongDTO;

public class PhongBUS {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		return PhongDAO.getDSPhong();
	}
	
	public static List<PhongDTO> getDSPhongCoTheThue(Timestamp ngayNhan, Timestamp ngayTra, Integer maLoaiPhong) throws SQLException {
		return PhongDAO.getDSPhongCoTheThue(ngayNhan, ngayTra, maLoaiPhong);
	}

	public static PhongDTO getPhongById(String maPhong) throws SQLException {
		return PhongDAO.getPhongById(maPhong);
	}

	public static Integer getMaPTP(String maPhong) throws SQLException {
		return PhongDAO.getMaPTP(maPhong);
	}

	public static boolean insertPhong(PhongDTO phong) throws SQLException {
		if (PhongDAO.checkPhong(phong.getMaPhong())) {
			return false;			
		}
		return PhongDAO.insertPhong(phong);
	}

	public static boolean deletePhong(String maPhong) throws SQLException {
		return PhongDAO.deletePhong(maPhong);
	}

	public static boolean updatePhong(PhongDTO phong) throws SQLException {
		return PhongDAO.updatePhong(phong);
	}
}