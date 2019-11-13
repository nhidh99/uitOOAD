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
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * from LoaiDichVu WHERE KhaDung = true";
			ResultSet rs = statement.executeQuery(query);

			List<LoaiDichVuDTO> output = new ArrayList<LoaiDichVuDTO>();
			while (rs.next()) {
				output.add(new LoaiDichVuDTO(rs.getInt("MaLoaiDichVu"), rs.getString("TenLoaiDichVu")));
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteLoaiDichVu(Integer maLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "CALL del_LoaiDichVu(?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maLoaiDichVu);
			statement.execute();
			return true;
		} finally {
			conn.close();
		}
	}

	public static LoaiDichVuDTO getLoaiDichVuById(Integer maLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM LoaiDichVu WHERE MaLoaiDichVu = " + maLoaiDichVu;
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			LoaiDichVuDTO output = new LoaiDichVuDTO(maLoaiDichVu, rs.getString("TenLoaiDichVu"));
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean checkLoaiDichVu(String tenLoaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT EXISTS (SELECT 1 FROM LoaiDichVu WHERE TenLoaiDichVu = ? LIMIT 1)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tenLoaiDichVu);
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean isExist = rs.getBoolean(1);
			return isExist;
		} finally {
			conn.close();
		}
	}

	public static boolean insertLoaiDichVu(LoaiDichVuDTO loaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO LoaiDichVu (TenLoaiDichVu) VALUES (?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, loaiDichVu.getTenLoaiDichVu());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updateLoaiDichVu(LoaiDichVuDTO loaiDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE LoaiDichVu SET TenLoaiDichVu = ? WHERE MaLoaiDichVu = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, loaiDichVu.getTenLoaiDichVu());
			statement.setInt(2, loaiDichVu.getMaLoaiDichVu());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}
}