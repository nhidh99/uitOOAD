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
				rs.getInt("SoNgayTraCoc"),
				rs.getFloat("TiLeThueVAT"),
				rs.getFloat("TiLeTienCoc"),
				rs.getFloat("PhuThuQuaKhach"),
				rs.getFloat("PhuThuTraPhongTre"));
		
		conn.close();
		return output;
	}
	
	public static boolean updateThamSo(Integer soNgayTraCoc, float tiLeThueVAT, float tiLeTienCoc, float phuThuQuaKhach, float phuThuTraPhongTre) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE thamso SET SoNgayTraCoc = ?, TiLeThueVAT = ?, TiLeTienCoc = ?, PhuThuQuaKhach = ?, PhuThuTraPhongTre = ?";
		PreparedStatement statement = conn.prepareCall(query);
		statement.setInt(1, soNgayTraCoc);
		statement.setFloat(2, tiLeThueVAT);
		statement.setFloat(3, tiLeTienCoc);
		statement.setFloat(4, phuThuQuaKhach);
		statement.setFloat(5, phuThuTraPhongTre);
		statement.execute();
		conn.close();
		
		return true;
	}
}