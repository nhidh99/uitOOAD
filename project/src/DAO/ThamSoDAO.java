package DAO;

import java.sql.Connection;
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
}