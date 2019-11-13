package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import BUS.NhanVienBUS;
import DTO.HoaDonDTO;
import DTO.NhanVienDTO;
import helper.DBHelper;

public class HoaDonDAO {

	public static boolean insertHoaDon(HoaDonDTO hoaDon) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO HoaDon (MaNhanVien, NgayHoaDon, TenKhachTra, CMND, SoDienThoai, Email,"
					+ "TongTienPhong, TongTienPTCK, TongTienCoc, GiaTri, TienNhan, TienThua, GhiChu) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, hoaDon.getNhanVien().getMaNhanVien());
			statement.setDate(2, new Date(hoaDon.getNgayHoaDonValue().getTime()));
			statement.setString(3, hoaDon.getTenKhach());
			statement.setString(4, hoaDon.getCMND());
			statement.setString(5, hoaDon.getDienThoai());
			statement.setString(6, hoaDon.getEmail());
			statement.setInt(7, hoaDon.getTongTienPhongValue());
			statement.setInt(8, hoaDon.getTongTienPTCKValue());
			statement.setInt(9, hoaDon.getTongTienCocValue());
			statement.setInt(10, hoaDon.getGiaTriValue());
			statement.setInt(11, hoaDon.getTienNhanValue());
			statement.setInt(12, hoaDon.getTienThuaValue());

			if (hoaDon.getGhiChu().trim().isEmpty()) {
				statement.setNull(13, Types.VARCHAR);
			} else
				statement.setString(13, hoaDon.getGhiChu());
			int output = statement.executeUpdate();
			return output > 0;
		} finally {
			conn.close();
		}
	}

	public static Integer getMaxMaHoaDon() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT MAX(MaHoaDon) FROM HoaDon";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			Integer output = rs.getInt(1);
			return output;
		} finally {
			conn.close();
		}
	}

	public static HoaDonDTO getHoaDonById(Integer maHoaDon) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM HoaDon WHERE MaHoaDon = " + maHoaDon;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			NhanVienDTO nhanVien = NhanVienBUS.getNhanVienById(rs.getInt("MaNhanVien"));
			HoaDonDTO output = new HoaDonDTO(maHoaDon, nhanVien, rs.getDate("NgayHoaDon"), rs.getString("TenKhachTra"),
					rs.getString("CMND"), rs.getString("SoDienThoai"), rs.getString("Email"),
					rs.getInt("TongTienPhong"), rs.getInt("TongTienPTCK"), rs.getInt("TongTienCoc"),
					rs.getInt("GiaTri"), rs.getInt("TienNhan"), rs.getInt("TienThua"), rs.getString("GhiChu"));
			return output;
		} finally {
			conn.close();
		}
	}

	public static List<HoaDonDTO> getDSHoaDon() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM HoaDon ORDER BY MaHoaDon DESC";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			List<HoaDonDTO> output = new ArrayList<HoaDonDTO>();
			while (rs.next()) {
				NhanVienDTO nhanVien = NhanVienDAO.getNhanVienById(rs.getInt("MaNhanVien"));
				HoaDonDTO hoaDon = new HoaDonDTO(rs.getInt("MaHoaDon"), nhanVien, rs.getDate("NgayHoaDon"),
						rs.getString("TenKhachTra"), rs.getString("CMND"), rs.getString("SoDienThoai"),
						rs.getString("Email"), rs.getInt("TongTienPhong"), rs.getInt("TongTienPTCK"),
						rs.getInt("TongTienCoc"), rs.getInt("GiaTri"), rs.getInt("TienNhan"), rs.getInt("TienThua"),
						rs.getString("GhiChu"));
				output.add(hoaDon);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteHoaDon(Integer maHoaDon) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "DELETE FROM HoaDon WHERE MaHoaDon = " + maHoaDon;
			Statement statement = conn.createStatement();
			int records = statement.executeUpdate(query);
			return records > 0;
		} finally {
			conn.close();
		}
	}
}
