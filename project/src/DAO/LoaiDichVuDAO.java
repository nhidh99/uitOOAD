package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.LoaiDichVuDTO;
import helper.DBHelper;

public class LoaiDichVuDAO {
	public static List<LoaiDichVuDTO> getDSLoaiDichVu() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from LoaiDichVu";
		ResultSet rs = statement.executeQuery(query);

		List<LoaiDichVuDTO> output = new ArrayList<LoaiDichVuDTO>();
		while (rs.next()) {
			output.add(new LoaiDichVuDTO(
					rs.getInt("MaLoaiDichVu"),
					rs.getString("TenLoaiDichVu")));
		}
		conn.close();
		return output;
	}
	
	public static boolean checkLoaiDichVuTonTai(String tenLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM loaidichvu WHERE TenLoaiDichVu = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenLoaiDichVu);
		ResultSet rs = statement.executeQuery();
		boolean isExist = false;
		
		if(rs.next()) {
			isExist = true;
		}
		
		conn.close();
		return isExist;
	}
	
	public static boolean addLoaiDichVu(String tenLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO loaidichvu(TenLoaiDichVu) VALUES(?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenLoaiDichVu);
		statement.execute();
		conn.close();
		return true;
	}
	
	public static boolean updateLoaiDichVu(String tenLoaiDichVuCu, String tenLoaiDichVuMoi) throws SQLException {

		Connection conn = DBHelper.getConnection();
		String query = "UPDATE loaidichvu SET TenLoaiDichVu = ? WHERE TenLoaiDichVu = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenLoaiDichVuMoi);
		statement.setString(2, tenLoaiDichVuCu);
		statement.execute();
		conn.close();
		
		return true;
	}
}