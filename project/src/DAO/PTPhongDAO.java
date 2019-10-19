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

public class PTPhongDAO {

	public static boolean insertPTPhong(PTPhongDTO ptp) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO PT_Phong (MaPhong, LoaiPhongThue, SoKhachToiDa,"
				+ "NgayNhan, NgayTra, DonGiaThue, TienCoc) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, ptp.getMaPhong());
		statement.setString(2, ptp.getLoaiPhongThue());
		statement.setInt(3, ptp.getSoKhachToiDa());
		statement.setTimestamp(4, ptp.getNgayNhanValue());
		statement.setTimestamp(5, ptp.getNgayTraValue());
		statement.setInt(6, ptp.getDonGiaThueValue());
		statement.setInt(7, ptp.getTienCocValue());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static List<PTPhongDTO> getDSPhongDangKy() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM PT_Phong WHERE MaPhieuThue IS NULL";
		ResultSet rs = statement.executeQuery(query);

		List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
		while (rs.next()) {
			PhongDTO phong = PhongDAO.getPhongById(rs.getString("MaPhong"));
			PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getTimestamp("NgayNhan"),
					rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"));
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
			PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), phong, rs.getTimestamp("NgayNhan"),
					rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"));
			output.add(ptp);
		}
		conn.close();
		return output;
	}

	public static boolean deleteAllPhieuDangKy() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "DELETE FROM PT_Phong WHERE MaPhieuThue IS NULL";
		statement.execute(query);
		conn.close();
		return true;
	}
	
	public static boolean deletePhieuDangKy(Integer maPTPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "DELETE FROM PT_Phong WHERE MaPTPhong = " + maPTPhong;
		int records = statement.executeUpdate(query);
		conn.close();
		return records > 0;
	}
}
