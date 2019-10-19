package BUS;

import DAO.*;
import DTO.PhieuThueDTO;

import java.sql.SQLException;
import java.util.List;

public class PhieuThueBUS {
	public static boolean insertPhieuThue(PhieuThueDTO phieuThue) throws SQLException {
		return PhieuThueDAO.insertPhieuThue(phieuThue);
	}
	
	public static boolean deletePhieuThue(Integer maPhieuThue) throws SQLException {
		if (PhieuThueDAO.checkPhieuThue(maPhieuThue)) {
			return false;
		}
		return PhieuThueDAO.deletePhieuThue(maPhieuThue);
	}

	public static List<PhieuThueDTO> getDSPhieuThue() throws SQLException {
		return PhieuThueDAO.getDSPhieuThue();
	}

	public static PhieuThueDTO getPhieuThueById(Integer maPhieuThue) throws SQLException {
		return PhieuThueDAO.getPhieuThueById(maPhieuThue);
	}
	
	public static boolean updatePhieuThue(PhieuThueDTO newPhieuThue) throws SQLException {
		return PhieuThueDAO.updatePhieuThue(newPhieuThue);
	}

	public static boolean updateThanhToanCoc(Integer maPhieuThue, Boolean thanhToanCoc) throws SQLException {
		return PhieuThueDAO.updateThanhToanCoc(maPhieuThue, thanhToanCoc);
	}
}
