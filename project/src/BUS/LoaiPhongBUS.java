package BUS;

import java.sql.SQLException;
import java.util.*;
import DAO.LoaiPhongDAO;
import DTO.LoaiPhongDTO;

public class LoaiPhongBUS {
	public static List<LoaiPhongDTO> getDSLoaiPhong() throws SQLException {
		return LoaiPhongDAO.getDSLoaiPhong();
	}

	public static boolean deleteLoaiPhong(Integer maLoaiPhong) throws SQLException {
		if (LoaiPhongDAO.checkLoaiPhong(maLoaiPhong)) {
			return false;
		}
		return LoaiPhongDAO.deleteLoaiPhong(maLoaiPhong);
	}
}