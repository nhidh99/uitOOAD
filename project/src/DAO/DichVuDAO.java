package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.DichVuDTO;
import helper.DBHelper;

public class DichVuDAO {
	public static List<DichVuDTO> getDSDichVu() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from DichVu INNER JOIN LoaiDichVu ON DichVu.MaLoaiDichVu = LoaiDichVu.MaLoaiDichVu	";
		ResultSet rs = statement.executeQuery(query);
		
		List<DichVuDTO> output = new ArrayList<DichVuDTO>();
		while (rs.next()) {
			output.add(new DichVuDTO(
					rs.getInt("MaDichVu"),
					rs.getString("TenDichVu"),
					rs.getString("DonViTinh"),
					rs.getInt("SoLuongTon"),
					rs.getInt("DonGia"),
					rs.getString("TenLoaiDichVu"),
					rs.getInt("MaLoaiDichVu"),
					rs.getInt("MaNhaCungCap"),
					rs.getInt("KhaDung")));
		}
		conn.close();
		return output;
	}
	public static boolean insertDSDichVu(DichVuDTO dichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO DichVu (TenDichVu, DonViTinh, SoLuongTon, DonGia, MaLoaiDichVu, MaNhaCungCap) VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, dichVu.getTenDichVu());
		statement.setString(2, dichVu.getDonViTinh());
		statement.setInt(3, dichVu.getSoLuongTon());
		statement.setInt(4, dichVu.getDonGia());
		statement.setInt(5, dichVu.getMaLoaiDichVu());
		statement.setInt(6, dichVu.getMaNhaCungCap());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}

}