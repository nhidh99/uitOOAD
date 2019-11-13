package BUS;

import java.sql.SQLException;
import java.util.*;
import DAO.LoaiPhongDAO;
import DTO.LoaiPhongDTO;

public class LoaiPhongBUS {
	public static List<LoaiPhongDTO> getDSLoaiPhong() throws SQLException {
		return LoaiPhongDAO.getDSLoaiPhong();
	}

	public static boolean deleteLoaiPhong(LoaiPhongDTO loaiPhong) throws SQLException {
		return LoaiPhongDAO.deleteLoaiPhong(loaiPhong.getMaLoaiPhong());
	}
		

	public static boolean insertLoaiPhong(LoaiPhongDTO loaiPhong) throws SQLException {
		if (LoaiPhongDAO.checkLoaiPhong(loaiPhong.getTenLoaiPhong())) {
			return false;
		}
		return LoaiPhongDAO.insertLoaiPhong(loaiPhong);
	}

	public static boolean updateLoaiPhong(LoaiPhongDTO loaiPhong) throws SQLException {
		return LoaiPhongDAO.updateLoaiPhong(loaiPhong);
	}
}