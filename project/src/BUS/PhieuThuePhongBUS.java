package BUS;

import java.sql.SQLException;

import DAO.PhieuThuePhongDAO;
import DTO.PhieuThuePhongDTO;

public class PhieuThuePhongBUS {
	public static PhieuThuePhongDTO getPTPhongByMaPhong(Integer maPhong) throws SQLException {
		return PhieuThuePhongDAO.getPTPhongByMaPhong(maPhong);
	}

}