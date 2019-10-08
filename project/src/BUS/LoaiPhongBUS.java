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
	
	public static boolean themLoaiPhong(String tenLoaiPhong, Integer soKhachToiDa, Integer donGia) throws SQLException {
		if(LoaiPhongDAO.checkLoaiPhongTonTai(tenLoaiPhong)) {
			return false;
		}
		return LoaiPhongDAO.addLoaiPhong(tenLoaiPhong, soKhachToiDa, donGia);
	}
	
	public static boolean suaLoaiPhong(String tenLoaiPhongCu, String tenLoaiPhongMoi, Integer soKhachToiDa, Integer donGia) throws SQLException {
		return LoaiPhongDAO.updateLoaiPhong(tenLoaiPhongCu, tenLoaiPhongMoi, soKhachToiDa, donGia);
	}
}