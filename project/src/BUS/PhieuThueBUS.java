package BUS;

import DAO.*;
import DTO.PhieuThueDTO;

import java.sql.SQLException;

public class PhieuThueBUS {
	public static Integer getNextMaPhieuThue() throws SQLException {
		return PhieuThueDAO.getNextMaPhieuThue();
	}
	
	public static boolean insertPhieuThue(PhieuThueDTO phieuThue) throws SQLException {
		return PhieuThueDAO.insertPhieuThue(phieuThue);
	}
}
