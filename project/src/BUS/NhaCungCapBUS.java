package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

public class NhaCungCapBUS {
	public static List<NhaCungCapDTO> getDSNhaCungCap() throws SQLException {
		return NhaCungCapDAO.getDSNhaCungCap();
	}
	
	public static boolean deleteNhaCungCap(Integer maNhaCungCap) throws SQLException {
//		if(NhaCungCapDAO.checkNhaCungCap(maNhaCungCap)) {
//			return false;
//		}
		return NhaCungCapDAO.deleteNhaCungCap(maNhaCungCap);
	}
}
