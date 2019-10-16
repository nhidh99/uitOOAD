package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import DTO.PhieuThueDTO;
import helper.DBHelper;

public class PhieuThueDAO {

	public static Integer getNextMaPhieuThue() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "CALL get_NextID('PhieuThue')";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		Integer output = rs.getInt(1);
		conn.close();
		return output;
	}
	
	public static boolean insertPhieuThue(PhieuThueDTO phieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO PhieuThue (MaNhanVien, NgayLapPhieu, TenKhachThue, "
				+ "CMND, SoDienThoai, Email, GhiChu) VALUES  (?,?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, phieuThue.getMaNhanVien());
		statement.setDate(2, new Date(phieuThue.getNgayLapPhieu().getTime()));
		statement.setString(3, phieuThue.getTenKhachThue());
		statement.setString(4, phieuThue.getCMND());
		statement.setString(5, phieuThue.getSoDienThoai());
		statement.setString(6, phieuThue.getEmail());
		
		if (phieuThue.getGhiChu().isEmpty()) {
			statement.setNull(7, Types.VARCHAR);
		}
		else statement.setString(7, phieuThue.getGhiChu());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}