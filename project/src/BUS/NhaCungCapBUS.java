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
		return NhaCungCapDAO.deleteNhaCungCap(maNhaCungCap);
	}
	
	public static boolean addNhaCungCap(String tenNhaCungCap, Integer soDienThoai) throws SQLException {
		if(NhaCungCapDAO.checkNhaCungCapTonTai(tenNhaCungCap))
			return false;
		else
			return NhaCungCapDAO.addNhaCungCap(tenNhaCungCap, soDienThoai);
	}
	
	public static boolean updateNhaCungCap(String tenNhaCungCapCu,String tenNhaCungCapMoi, Integer soDienThoai ) throws SQLException {
		if(NhaCungCapDAO.updateNhaCungCap(tenNhaCungCapCu, tenNhaCungCapMoi, soDienThoai)) {
			return true;
		}
		else
			return false;
	}
}
