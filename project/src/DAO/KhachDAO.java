package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.PTPhongDTO;
import DTO.KhachDTO;
import helper.DBHelper;

public class KhachDAO {
	public static List<KhachDTO> getDSKhachByMaPTP(Integer maPTP) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM KhachHang WHERE MaPTPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPTP);
		ResultSet rs = statement.executeQuery();

		List<KhachDTO> output = new ArrayList<KhachDTO>();
		while (rs.next()) {
			PTPhongDTO ptPhong = PTPhongDAO.getPTPhongById(rs.getInt("MaPTPhong"));
			KhachDTO khach = new KhachDTO(rs.getInt("MaKhachHang"), ptPhong, rs.getString("HoTen"),
					rs.getString("CMND"), rs.getString("SoDienThoai"), rs.getString("GioiTinh"),
					rs.getString("QuocTich"));
			output.add(khach);
		}
		conn.close();
		return output;
	}

	public static boolean insertKhach(KhachDTO khach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO KhachHang (HoTen, SoDienThoai, GioiTinh, QuocTich, MaPTPhong, CMND)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, khach.getHoTen());
		statement.setString(2, khach.getSoDienThoai());
		statement.setString(3, khach.getGioiTinh());
		statement.setString(4, khach.getQuocTich());
		statement.setInt(5, khach.getPTPhong().getMaPTPhong());
		statement.setString(6, khach.getCMND());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean deleteKhach(Integer maKhachHang) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM KhachHang WHERE MaKhachHang = " + maKhachHang;
		Statement statement = conn.createStatement();
		int records = statement.executeUpdate(query);
		conn.close();
		return records > 0;
	}

	public static boolean updateKhach(KhachDTO khach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE KhachHang "
				+ "SET HoTen = ?, SoDienThoai = ?, GioiTinh = ?, QuocTich = ?, CMND = ? "
				+ "WHERE MaKhachHang = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, khach.getHoTen());
		statement.setString(2, khach.getSoDienThoai());
		statement.setString(3, khach.getGioiTinh());
		statement.setString(4, khach.getQuocTich());
		statement.setString(5, khach.getCMND());
		statement.setInt(6, khach.getMaKhachHang());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}
