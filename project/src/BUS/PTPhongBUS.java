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

	public static boolean deleteAllPTPhong() throws SQLException {
		return PTPhongDAO.deleteAllPTPhong();
	}
	
	public static boolean deletePTPhong(PTPhongDTO ptPhong) throws SQLException {
		if (PTPhongDAO.checkPTPhong(ptPhong)) {
			return PTPhongDAO.deletePTPhong(ptPhong.getMaPTPhong());
		}
		return false;
	}

	public static PTPhongDTO getPTPhongById(Integer maPTP) throws SQLException {
		return PTPhongDAO.getPTPhongById(maPTP);
	}
	
	public static List<PTPhongDTO> getDSPTPhongByMaPhong(String maPhong) throws SQLException {
		return PTPhongDAO.getDSPTPhongByMaPhong(maPhong);
	}

	public static boolean updateMaHoaDon(Integer maPTP, Integer maHoaDon) throws SQLException {
		return PTPhongDAO.updateMaHoaDon(maPTP, maHoaDon);
	}

	public static List<PTPhongDTO> getDSPTPhongByMaHD(Integer maHD) throws SQLException {
		return PTPhongDAO.getDSPTPhongByMaHD(maHD);
	}
}
