package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

public class NhaCungCapBUS {
	public static List<NhaCungCapDTO> getDSNhaCungCap() throws SQLException {
		return NhaCungCapDAO.getDSNhaCungCap();
	}

	public static NhaCungCapDTO getNhaCungCapById(Integer maNhaCungCap) throws SQLException {
		return NhaCungCapDAO.getNhaCungCapById(maNhaCungCap);
	}

	public static boolean insertNhaCungCap(NhaCungCapDTO nhaCungCap) throws SQLException {
		if (NhaCungCapDAO.checkNhaCungCap(nhaCungCap.getTenNhaCungCap())) {
			return false;
		}
		return NhaCungCapDAO.insertNhaCungCap(nhaCungCap);
	}

	public static boolean updateNhaCungCap(NhaCungCapDTO nhaCungCap) throws SQLException {
		return NhaCungCapDAO.updateNhaCungCap(nhaCungCap);
	}

	public static boolean deleteNhaCungCap(Integer maNhaCungCap) throws SQLException {
		if (NhaCungCapDAO.checkNhaCungCap(maNhaCungCap)) {
			return false;
		}
		return NhaCungCapDAO.deleteNhaCungCap(maNhaCungCap);
	}
}
