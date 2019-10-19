package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.PTPhongDAO;
import DTO.PTPhongDTO;

public class PTPhongBUS {

	public static boolean insertPTPhong(PTPhongDTO ptp) throws SQLException {
		return PTPhongDAO.insertPTPhong(ptp);
	}
	
	public static List<PTPhongDTO> getDSPhongDangKy() throws SQLException {
		return PTPhongDAO.getDSPhongDangKy();
	}

	public static List<PTPhongDTO> getDSPTPhongByMaPhieu(Integer maPhieuThue) throws SQLException {
		return PTPhongDAO.getDSPTPhongByMaPhieu(maPhieuThue);
	}

	public static boolean deleteAllPhieuDangKy() throws SQLException {
		return PTPhongDAO.deleteAllPhieuDangKy();
	}
	
	public static boolean deletePhieuDangKy(Integer maPTPhong) throws SQLException {
		return PTPhongDAO.deletePhieuDangKy(maPTPhong);
	}

	public static PTPhongDTO getPTPhongById(Integer maPTP) throws SQLException {
		return PTPhongDAO.getPTPhongById(maPTP);
	}
}
