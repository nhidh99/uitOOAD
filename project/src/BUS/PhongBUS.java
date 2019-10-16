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
}