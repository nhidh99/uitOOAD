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
			output.add(new NhaCungCapDTO(rs.getInt("MaNhaCungCap"), rs.getString("TenNhaCungCap"),
					rs.getString("SoDienThoai")));
		}
		conn.close();
		return output;
	}

	public static boolean checkNhaCungCapTonTai(String tenNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM nhacungcap WHERE TenNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenNhaCungCap);
		ResultSet rs = statement.executeQuery();
		boolean isExist = false;
		if(rs.next()) {
			isExist = true;
		}
		conn.close();
		return isExist;
	}

	public static boolean deleteNhaCungCap(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM NhaCungCap WHERE MaNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNhaCungCap);
		statement.execute();
		conn.close();
		return true;
	}

	public static Integer getMaFromTen(String tenNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT MaNhaCungCap FROM nhacungcap WHERE TenNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenNhaCungCap);
		ResultSet rs = statement.executeQuery();
		Integer result = null;
		if (rs.next()) {
			result = rs.getInt(1);
		}
		return result;
	}
	
	public static String getTenFromMa(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT TenNhaCungCap FROM nhacungcap WHERE MaNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNhaCungCap);
		ResultSet rs = statement.executeQuery();
		String result = null;
		if (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}
	
	public static boolean addNhaCungCap(String tenNhaCungCap, Integer soDienThoai) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO nhacungcap(TenNhaCungCap,SoDienThoai) VALUES (?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenNhaCungCap);
		statement.setInt(2, soDienThoai);
		statement.execute();
		
		conn.close();
		return true;
	}
	public static boolean updateNhaCungCap(String tenNhaCungCapCu, String tenNhaCungCapMoi, Integer soDienThoai) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE nhacungcap SET TenNhaCungCap = ? , SoDienThoai = ? WHERE TenNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenNhaCungCapMoi);
		statement.setInt(2, soDienThoai);
		statement.setString(3, tenNhaCungCapCu);
		statement.execute();
		conn.close();
		return true;
	}
}