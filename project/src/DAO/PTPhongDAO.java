package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.PTPhongDTO;
import DTO.PhongDTO;
import helper.DBHelper;
import helper.DateFormatHelper;

public class PTPhongDAO {

	public static boolean insertPTPhong(PTPhongDTO ptp) throws SQLException {
		long soDem = DateFormatHelper.diffDays(ptp.getNgayTraValue(), ptp.getNgayNhanValue());
		long thanhTien = soDem * ptp.getDonGiaThueValue();

		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO PT_Phong (MaPhong, LoaiPhongThue, SoKhachToiDa,"
				+ "NgayNhan, NgayTra, DonGiaThue, TienCoc, ThanhTien) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, ptp.getMaPhong());
		statement.setString(2, ptp.getLoaiPhongThue());
		statement.setInt(3, ptp.getSoKhachToiDa());
		statement.setTimestamp(4, ptp.getNgayNhanValue());
		statement.setTimestamp(5, ptp.getNgayTraValue());
		statement.setInt(6, ptp.getDonGiaThueValue());
		statement.setInt(7, ptp.getTienCocValue());
		statement.setLong(8, thanhTien);

		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static List<PTPhongDTO> getDSPhongDangKy() throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM PT_Phong WHERE MaPhieuThue IS NULL";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
		while (rs.next()) {
			PhongDTO phong = PhongDAO.getPhongById(rs.getString("MaPhong"));
			PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getInt("DonGiaThue"),
					rs.getTimestamp("NgayNhan"), rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"),
					rs.getInt("ThanhTien"));
			output.add(ptp);
		}
		conn.close();
		return output;
	}

	public static List<PTPhongDTO> getDSPTPhongByMaPhieu(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM PT_Phong WHERE MaPhieuThue = " + maPhieuThue;
		ResultSet rs = statement.executeQuery(query);

		List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
		while (rs.next()) {
			PhongDTO phong = PhongDAO.getPhongById(rs.getString("MaPhong"));
			PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getInt("DonGiaThue"),
					rs.getTimestamp("NgayNhan"), rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"),
					rs.getInt("ThanhTien"));
			output.add(ptp);
		}
		conn.close();
		return output;
	}

	public static boolean deleteAllPTPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "DELETE FROM PT_Phong WHERE MaPhieuThue IS NULL";
		statement.execute(query);
		conn.close();
		return true;
	}

	public static boolean deletePTPhong(Integer maPTPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "DELETE FROM PT_Phong WHERE MaPTPhong = " + maPTPhong;
		int records = statement.executeUpdate(query);
		conn.close();
		return records > 0;
	}

	public static PTPhongDTO getPTPhongById(Integer maPTP) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM PT_Phong WHERE MaPTPhong = " + maPTP;
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		PhongDTO phong = PhongDAO.getPhongById(rs.getString("MaPhong"));
		PTPhongDTO output = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getInt("DonGiaThue"),
				rs.getTimestamp("NgayNhan"), rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"), rs.getInt("ThanhTien"));
		conn.close();
		return output;
	}

	public static List<PTPhongDTO> getDSPTPhongByMaPhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_dsptphong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		ResultSet rs = statement.executeQuery();
		List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
		while (rs.next()) {
			PhongDTO phong = PhongDAO.getPhongById(maPhong);
			PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getInt("DonGiaThue"),
					rs.getTimestamp("NgayNhan"), rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"),
					rs.getInt("ThanhTien"));
			output.add(ptp);
		}
		conn.close();
		return output;
	}
	
	public static List<PTPhongDTO> getDSPTPhongByMaHD(Integer maHD) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM PT_Phong WHERE MaHoaDon = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maHD);
		ResultSet rs = statement.executeQuery();
		List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
		while (rs.next()) {
			PhongDTO phong = PhongDAO.getPhongById(rs.getString("MaPhong"));
			PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getInt("DonGiaThue"),
					rs.getTimestamp("NgayNhan"), rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"),
					rs.getInt("ThanhTien"));
			output.add(ptp);
		}
		conn.close();
		return output;
	}

	public static boolean checkPTPhong(PTPhongDTO ptPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL check_PTPhong(?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ptPhong.getMaPTPhong());
		statement.setString(2, ptPhong.getMaPhong());
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean output = rs.getBoolean(1);
		conn.close();
		return output;
	}

	public static boolean updateMaHoaDon(Integer maPTP, Integer maHoaDon) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE PT_Phong SET MaHoaDon = ? WHERE MaPTPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maHoaDon);
		statement.setInt(2, maPTP);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}