package DAO;

import java.sql.*;
import java.util.*;

import DTO.LoaiPhongDTO;
import helper.DBHelper;

public class LoaiPhongDAO {

	public static List<LoaiPhongDTO> getDSLoaiPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from LoaiPhong";
		ResultSet rs = statement.executeQuery(query);

		List<LoaiPhongDTO> output = new ArrayList<LoaiPhongDTO>();
		while (rs.next()) {
			output.add(new LoaiPhongDTO(
					rs.getInt("MaLoaiPhong"),
					rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"),
					rs.getInt("DonGia")));
		}
		conn.close();
		return output;
	}
}