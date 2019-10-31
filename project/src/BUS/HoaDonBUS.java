package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

public class HoaDonBUS {

	public static List<HoaDonDTO> getDSHoaDon() throws SQLException {
		return HoaDonDAO.getDSHoaDon();
	}

	public static boolean insertHoaDon(HoaDonDTO hoaDon) throws SQLException {
		return HoaDonDAO.insertHoaDon(hoaDon);
	}

	public static Integer getMaxMaHoaDon() throws SQLException {
		return HoaDonDAO.getMaxMaHoaDon();
	}

	public static HoaDonDTO getHoaDonById(Integer maHoaDon) throws SQLException {
		return HoaDonDAO.getHoaDonById(maHoaDon);
	}

	public static boolean deleteHoaDon(Integer maHoaDon) throws SQLException {
		return HoaDonDAO.deleteHoaDon(maHoaDon);
	}
}