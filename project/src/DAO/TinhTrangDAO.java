package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.TinhTrangDTO;
import helper.DBHelper;

public class TinhTrangDAO {

	public static List<TinhTrangDTO> getDSTinhTrang() throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM TinhTrang WHERE TenTinhTrang != ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, "Thuê");		
		ResultSet rs = statement.executeQuery();
		
		List<TinhTrangDTO> output = new ArrayList<TinhTrangDTO>();
		while (rs.next()) {
			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			output.add(tinhTrang);
		}
		conn.close();
		return output;
	}
}
