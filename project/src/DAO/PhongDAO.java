package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.*;
import helper.DBHelper;

public class PhongDAO {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "CALL get_DSPhong";
		ResultSet rs = statement.executeQuery(query);

		List<PhongDTO> output = new ArrayList<PhongDTO>();
		while (rs.next()) {
			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			LoaiPhongDTO loaiPhong = new LoaiPhongDTO(
					rs.getInt("MaLoaiPhong"), 
					rs.getString("TenLoaiPhong"), 
					rs.getInt("SoKhachToiDa"), 
					rs.getInt("DonGia"));
			output.add(new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu")));
		}
		conn.close();
		return output;
	}
	
}