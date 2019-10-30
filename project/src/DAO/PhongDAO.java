package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import DTO.*;
import helper.DBHelper;

public class PhongDAO {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM view_DSPhong ORDER BY MaPhong";
		ResultSet rs = statement.executeQuery(query);

		List<PhongDTO> output = new ArrayList<PhongDTO>();
		while (rs.next()) {
			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));
			output.add(new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu")));
		}
		conn.close();
		return output;
	}

	public static List<PhongDTO> getDSPhongCoTheThue(Timestamp ngayNhan, Timestamp ngayTra, Integer maLoaiPhong)
			throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL search_DSPhong(?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setTimestamp(1, ngayNhan);
		statement.setTimestamp(2, ngayTra);
		statement.setInt(3, maLoaiPhong);
		ResultSet rs = statement.executeQuery();

		List<PhongDTO> output = new ArrayList<PhongDTO>();
		while (rs.next()) {
			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));
			output.add(new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu")));
		}
		conn.close();
		return output;

	}

	public static PhongDTO getPhongById(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_DSPhong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();
		
		TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
		LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"), 
				rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));		
		PhongDTO output = new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu"));
		conn.close();
		return output;
	}

	public static Integer getMaPTP(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT MaPTPHienTai FROM Phong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();		
		Integer output = rs.getInt(1);
		conn.close();
		return output;
	}

	public static boolean checkPhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT EXISTS (SELECT 1 FROM Phong WHERE MaPhong = ? AND KhaDung = true)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}

	public static boolean insertPhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO Phong (MaPhong, MaLoaiPhong, MaTinhTrang, GhiChu) VALUES(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, phong.getMaPhong());
		statement.setInt(2, phong.getLoaiPhong().getMaLoaiPhong());
		statement.setInt(3, phong.getTinhTrang().getMaTinhTrang());
		statement.setString(4, phong.getGhiChu());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean deletePhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL del_Phong(?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean updatePhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE Phong SET MaLoaiPhong = ?, MaTinhTrang = ?, GhiChu = ? WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, phong.getLoaiPhong().getMaLoaiPhong());
		statement.setInt(2, phong.getTinhTrang().getMaTinhTrang());
		statement.setString(3, phong.getGhiChu());
		statement.setString(4, phong.getMaPhong());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static List<String> findPhongByTenKhach(String ten) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT DISTINCT MaPhong "
				+ "FROM PT_Phong PTP JOIN KhachHang KH "
				+ "ON PTP.MaPTPhong = KH.MaPTPhong "
				+ "WHERE HoTen = ? ORDER BY MaPhong";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, ten);
		ResultSet rs = statement.executeQuery();		
		List<String> output = new ArrayList<String>();
		while (rs.next()) {
			output.add(rs.getString("MaPhong"));
		}
		conn.close();
		return output;		
	}
	
	public static List<String> findPhongByCMNDKhach(String CMND) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT DISTINCT MaPhong "
				+ "FROM PT_Phong PTP JOIN KhachHang KH "
				+ "ON PTP.MaPTPhong = KH.MaPTPhong "
				+ "WHERE CMND = ? ORDER BY MaPhong";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, CMND);
		ResultSet rs = statement.executeQuery();		
		List<String> output = new ArrayList<String>();
		while (rs.next()) {
			output.add(rs.getString("MaPhong"));
		}
		conn.close();
		return output;		
	}

	public static boolean updateNhanPhong(String maPhong, Integer maPTPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE Phong SET MaPTPHienTai = ?, MaTinhTrang = 11001 WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPTPhong);
		statement.setString(2, maPhong);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}