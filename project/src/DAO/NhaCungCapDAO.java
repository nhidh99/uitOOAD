package DAO;

import java.sql.*;
import java.util.*;

import DTO.NhaCungCapDTO;
import helper.DBHelper;

public class NhaCungCapDAO {

	public static List<NhaCungCapDTO> getDSNhaCungCap() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhaCungCap";
		ResultSet rs = statement.executeQuery(query);

		List<NhaCungCapDTO> output = new ArrayList<NhaCungCapDTO>();
		while (rs.next()) {
			output.add(new NhaCungCapDTO(
					rs.getInt("MaNhaCungCap"),
					rs.getString("TenNhaCungCap"),
					rs.getString("SoDienThoai")));
		}
		conn.close();
		return output;
	}

	public static NhaCungCapDTO getNhaCungCapById(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM NhaCungCap WHERE MaNhaCungCap = " + maNhaCungCap;
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		NhaCungCapDTO output = new NhaCungCapDTO(maNhaCungCap, rs.getString("TenNhaCungCap"), rs.getString("SoDienThoai"));
		conn.close();
		return output;
	}

	public static boolean checkNhaCungCap(String tenNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT EXISTS (SELECT 1 FROM NhaCungCap WHERE TenNhaCungCap = ? LIMIT 1)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenNhaCungCap);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}

	public static boolean insertNhaCungCap(NhaCungCapDTO nhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO NhaCungCap (TenNhaCungCap, SoDienThoai) VALUES (?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nhaCungCap.getTenNhaCungCap());
		statement.setString(2, nhaCungCap.getSoDienThoai());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean updateNhaCungCap(NhaCungCapDTO nhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE NhaCungCap SET TenNhaCungCap = ? , SoDienThoai = ? WHERE MaNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nhaCungCap.getTenNhaCungCap());
		statement.setString(2, nhaCungCap.getSoDienThoai());
		statement.setInt(3, nhaCungCap.getMaNhaCungCap());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean deleteNhaCungCap(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM NhaCungCap WHERE MaNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNhaCungCap);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean checkNhaCungCap(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT EXISTS (SELECT 1 FROM DichVu JOIN NhaCungCap ON "
				+ "DichVu.MaNhaCungCap = NhaCungCap.MaNhaCungCap "
				+ "WHERE NhaCungCap.MaNhaCungCap = ? LIMIT 1)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNhaCungCap);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}
}