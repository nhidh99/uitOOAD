package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PhongDAO;
import DTO.PhongDTO;

public class PhongBUS {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		return PhongDAO.getDSPhong();
	}
	
	public static boolean insertPhong(PhongDTO phong) throws SQLException {
		if(PhongDAO.checkPhong(phong)) {
			return false;
		}
		else
			return PhongDAO.insertPhong(phong);
	}
	
	public static boolean deletePhong(PhongDTO phong) throws SQLException {
		return PhongDAO.deletePhong(phong);
	}
	
	public static boolean updatePhong(PhongDTO phongCu, PhongDTO phongMoi) throws SQLException {
		if(phongCu.getMaPhong().equals(phongMoi.getMaPhong())) {
			return PhongDAO.updatePhong(phongCu, phongMoi);
		}
		else
		{
			if(PhongDAO.checkPhong(phongMoi)) {
				return false;
			}
			else
				return PhongDAO.updatePhong(phongCu, phongMoi);
		}
	}
}