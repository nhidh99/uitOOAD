package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.ThamSoDTO;
import helper.DBHelper;

public class ThamSoDAO {
	public static ThamSoDTO getThamSo() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from ThamSo";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		
		ThamSoDTO output = new ThamSoDTO(
				rs.getFloat("TiLeThueVAT"),
				rs.getFloat("TiLeTienCoc"));
		
		conn.close();
		return output;
	}

	public static boolean updateThamSo(ThamSoDTO thamSo) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE ThamSo SET TiLeThueVAT = ?, TiLeTienCoc = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setFloat(1, thamSo.getTiLeThueVAT());
		statement.setFloat(2, thamSo.getTiLeTienCoc());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}