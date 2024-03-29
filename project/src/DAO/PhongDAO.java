package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import DTO.*;
import helper.DBHelper;

public class PhongDAO {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
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
			return output;
		} finally {
			conn.close();
		}
	}

	public static List<PhongDTO> getDSPhongCoTheThue(Timestamp ngayNhan, Timestamp ngayTra, Integer maLoaiPhong)
			throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
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
			return output;
		} finally {
			conn.close();
		}
	}

	public static PhongDTO getPhongById(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM view_DSPhong WHERE MaPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, maPhong);
			ResultSet rs = statement.executeQuery();
			rs.next();

			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));
			PhongDTO output = new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu"));
			return output;
		} finally {
			conn.close();
		}
	}

	public static Integer getMaPTP(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT MaPTPHienTai FROM Phong WHERE MaPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, maPhong);
			ResultSet rs = statement.executeQuery();
			rs.next();
			Integer output = rs.getInt(1);
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean checkPhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT EXISTS (SELECT 1 FROM Phong WHERE MaPhong = ?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, maPhong);
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean isExist = rs.getBoolean(1);
			return isExist;
		} finally {
			conn.close();
		}
	}

	public static boolean insertPhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO Phong(MaPhong, MaLoaiPhong, MaTinhTrang, GhiChu) VALUES (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, phong.getMaPhong());
			statement.setInt(2, phong.getLoaiPhong().getMaLoaiPhong());
			statement.setInt(3, phong.getTinhTrang().getMaTinhTrang());
			if (phong.getGhiChu().trim().isEmpty()) {
				statement.setNull(4, Types.VARCHAR);
			} else {
				statement.setString(4, phong.getGhiChu());
			}
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean deletePhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "DELETE FROM Phong WHERE MaPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, maPhong);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updatePhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE Phong SET MaLoaiPhong = ?, MaTinhTrang = ?, GhiChu = ? WHERE MaPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, phong.getLoaiPhong().getMaLoaiPhong());
			statement.setInt(2, phong.getTinhTrang().getMaTinhTrang());
			if (!phong.getGhiChu().trim().isEmpty()) {
				statement.setString(3, phong.getGhiChu());
			} else
				statement.setNull(3, Types.VARCHAR);
			statement.setString(4, phong.getMaPhong());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static List<String> findPhongByTenKhach(String ten) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT DISTINCT MaPhong " + "FROM PT_Phong PTP JOIN KhachHang KH "
					+ "ON PTP.MaPTPhong = KH.MaPTPhong " + "WHERE HoTen = ? ORDER BY MaPhong";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			List<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add(rs.getString("MaPhong"));
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static List<String> findPhongByCMNDKhach(String CMND) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT DISTINCT MaPhong " + "FROM PT_Phong PTP JOIN KhachHang KH "
					+ "ON PTP.MaPTPhong = KH.MaPTPhong " + "WHERE CMND = ? ORDER BY MaPhong";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, CMND);
			ResultSet rs = statement.executeQuery();
			List<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add(rs.getString("MaPhong"));
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean updateNhanPhong(String maPhong, Integer maPTPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE Phong SET MaPTPHienTai = ?, MaTinhTrang = 11001 WHERE MaPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maPTPhong);
			statement.setString(2, maPhong);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}
}