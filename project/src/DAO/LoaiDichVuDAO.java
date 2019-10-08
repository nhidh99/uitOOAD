package DAO;

import java.sql.Connection;
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
	public static Integer getMaLoaiDichVu(String tenLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from LoaiDichVu WHERE TenLoaiDichVu = '" + tenLoaiDichVu +"'";
		ResultSet rs = statement.executeQuery(query);
		Integer result = -1;
		if(rs.next()) {
			result = rs.getInt("MaLoaiDichVu");
		}
		conn.close();
		return result;
	}
}