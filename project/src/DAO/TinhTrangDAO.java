package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.TinhTrangDTO;
import helper.DBHelper;


public class TinhTrangDAO {
	public static List<TinhTrangDTO> getDSTinhTrang() throws SQLException{
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM tinhtrang";
		Statement statement = conn.createStatement();
		
		ResultSet rs = statement.executeQuery(query);
		List<TinhTrangDTO> dsTinhTrang = new ArrayList<TinhTrangDTO>();
		
		while(rs.next()) {
			dsTinhTrang.add(new TinhTrangDTO(rs.getInt(1),rs.getString(2)));
		}
		
		conn.close();
		return dsTinhTrang;
	}
	
	public static TinhTrangDTO getTinhTrang(Integer maTinhTrang) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM tinhtrang WHERE MaTinhTrang  = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1,maTinhTrang);
		ResultSet rs = statement.executeQuery();
		List<TinhTrangDTO> dsTinhTrang = new ArrayList<TinhTrangDTO>();
		
		while(rs.next()) {
			dsTinhTrang.add(new TinhTrangDTO(rs.getInt(1),rs.getString(2)));
		}
		return dsTinhTrang.get(0);
	}	
}
