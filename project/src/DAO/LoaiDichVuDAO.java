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
		String query = "SELECT * from LoaiDichVu WHERE KhaDung = true";
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

	public static boolean checkLoaiDichVu(Integer maLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT EXISTS (SELECT 1 FROM DichVu WHERE MaLoaiDichVu = ? LIMIT 1)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maLoaiDichVu);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}

	public static boolean deleteLoaiDichVu(Integer maLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL del_LoaiDichVu(?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maLoaiDichVu);
		statement.execute();
		conn.close();
		return true;
	}
}