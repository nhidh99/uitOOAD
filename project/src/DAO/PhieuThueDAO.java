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

import DTO.PhieuThueDTO;
import helper.DBHelper;

public class PhieuThueDAO {

	public static boolean insertPhieuThue(PhieuThueDTO phieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO PhieuThue (MaNhanVien, NgayLapPhieu, TenKhachThue, "
				+ "CMND, SoDienThoai, Email, GhiChu) VALUES  (?,?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, phieuThue.getMaNhanVien());
		statement.setDate(2, new Date(phieuThue.getNgayLapValue().getTime()));
		statement.setString(3, phieuThue.getTenKhachThue());
		statement.setString(4, phieuThue.getCMND());
		statement.setString(5, phieuThue.getSoDienThoai());
		statement.setString(6, phieuThue.getEmail());

		if (phieuThue.getGhiChu().isEmpty()) {
			statement.setNull(7, Types.VARCHAR);
		} else
			statement.setString(7, phieuThue.getGhiChu());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static List<PhieuThueDTO> getDSPhieuThue() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM view_DSPhieuThue ORDER BY MaPhieuThue DESC";
		ResultSet rs = statement.executeQuery(query);

		List<PhieuThueDTO> output = new ArrayList<PhieuThueDTO>();
		while (rs.next()) {
			PhieuThueDTO pt = new PhieuThueDTO(rs.getInt("MaPhieuThue"), rs.getInt("MaNhanVien"),
					rs.getDate("NgayLapPhieu"), rs.getString("TenKhachThue"), rs.getString("CMND"),
					rs.getString("SoDienThoai"), rs.getString("Email"), rs.getInt("TongTienCoc"),
					rs.getBoolean("ThanhToanCoc"), rs.getString("GhiChu"));
			output.add(pt);
		}
		conn.close();
		return output;
	}

	public static boolean checkPhieuThue(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL check_PhieuThue(?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPhieuThue);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean output = rs.getBoolean(1);
		conn.close();
		return output;
	}

	public static boolean deletePhieuThue(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM PhieuThue WHERE MaPhieuThue = " + maPhieuThue;
		Statement statement = conn.createStatement();
		int records = statement.executeUpdate(query);
		conn.close();
		return records > 0;
	}

	public static PhieuThueDTO getPhieuThueById(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM view_DSPhieuThue WHERE MaPhieuThue = " + maPhieuThue;
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		PhieuThueDTO output = new PhieuThueDTO(rs.getInt("MaPhieuThue"), rs.getInt("MaNhanVien"),
				rs.getDate("NgayLapPhieu"), rs.getString("TenKhachThue"), rs.getString("CMND"),
				rs.getString("SoDienThoai"), rs.getString("Email"), rs.getInt("TongTienCoc"),
				rs.getBoolean("ThanhToanCoc"), rs.getString("GhiChu"));
		conn.close();
		return output;
	}

	public static boolean updatePhieuThue(PhieuThueDTO newPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE PhieuThue "
				+ "SET TenKhachThue = ?, CMND = ?, Email = ?, SoDienThoai = ?, GhiChu = ? "
				+ "WHERE MaPhieuThue = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, newPhieuThue.getTenKhachThue());
		statement.setString(2, newPhieuThue.getCMND());
		statement.setString(3, newPhieuThue.getEmail());
		statement.setString(4, newPhieuThue.getSoDienThoai());
		statement.setString(5, newPhieuThue.getGhiChu());
		statement.setInt(6, newPhieuThue.getMaPhieuThue());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean updateThanhToanCoc(Integer maPhieuThue, Boolean thanhToanCoc) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE PhieuThue SET ThanhToanCoc = ? WHERE MaPhieuThue = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setBoolean(1, thanhToanCoc);
		statement.setInt(2, maPhieuThue);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}