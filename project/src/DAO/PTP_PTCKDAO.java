package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.PTP_PTCKDTO;
import helper.DBHelper;

public class PTP_PTCKDAO {
	public static List<PTP_PTCKDTO> getDSPTCKByMaPT(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "select * from ptck_phong where MaPTPhong = '" + maPhieuThue + "'";
		ResultSet rs = statement.executeQuery(query);

		List<PTP_PTCKDTO> output = new ArrayList<PTP_PTCKDTO>();
		while (rs.next()) {
			PTP_PTCKDTO ptck = new PTP_PTCKDTO(
					rs.getInt("MaPTCKPhong"),
					rs.getInt("MaPTPhong"),
					rs.getString("NoiDung"),
					rs.getInt("SoLuong"),
					rs.getInt("DonGia"),
					rs.getInt("TriGia"));
			output.add(ptck);
		}
		conn.close();
		return output;
	}
	
	public static boolean insertPTP_PTCK(PTP_PTCKDTO ptp_ptck) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO ptck_phong (MaPTPhong, NoiDung, SoLuong, DonGia, TriGia) VALUES (?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ptp_ptck.getMaPTPhong());
		statement.setString(2, ptp_ptck.getNoiDung());
		statement.setInt(3, ptp_ptck.getSoLuong());
		statement.setInt(4, ptp_ptck.getDonGia());
		statement.setInt(5, ptp_ptck.getTriGia());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
	public static boolean deletePTP_PTCK(Integer maPTP_PTCK) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = String.format("DELETE FROM ptck_phong WHERE MaPTCKPhong = '%d'", maPTP_PTCK);
		int output = statement.executeUpdate(query);
		conn.close();
		return output > 0 ;
	}
	
	public static Integer getMaxMaPTP_PTCK() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT MAX(MaPTCKPhong) FROM ptck_phong";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		Integer output = rs.getInt(1);
		conn.close();
		return output;
	}

	public static boolean updatePTP_PTCK(PTP_PTCKDTO ptp_ptck) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE ptck_phong SET NoiDung = ?, SoLuong = ?, DonGia = ?, TriGia = ? WHERE MaPTCKPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, ptp_ptck.getNoiDung());
		statement.setInt(2, ptp_ptck.getSoLuong());
		statement.setInt(3, ptp_ptck.getDonGia());
		statement.setInt(4, ptp_ptck.getTriGia());
		statement.setInt(5, ptp_ptck.getMaPTCKPhong());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
}
