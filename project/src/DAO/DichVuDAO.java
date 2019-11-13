package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BUS.LoaiDichVuBUS;
import BUS.NhaCungCapBUS;
import DTO.*;
import helper.DBHelper;

public class DichVuDAO {
	public static List<DichVuDTO> getDSDichVu() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * from DichVu WHERE KhaDung = true";
			ResultSet rs = statement.executeQuery(query);

			List<DichVuDTO> output = new ArrayList<DichVuDTO>();
			while (rs.next()) {
				LoaiDichVuDTO loaiDichVu = LoaiDichVuBUS.getLoaiDichVuById(rs.getInt("MaLoaiDichVu"));
				NhaCungCapDTO nhaCungCap = NhaCungCapBUS.getNhaCungCapById(rs.getInt("MaNhaCungCap"));
				output.add(new DichVuDTO(rs.getInt("MaDichVu"), rs.getString("TenDichVu"), rs.getString("DonViTinh"),
						rs.getInt("SoLuongTon"), rs.getInt("DonGia"), loaiDichVu, nhaCungCap));
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean insertDichVu(DichVuDTO dichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO DichVu (TenDichVu, DonViTinh, SoLuongTon, DonGia, MaLoaiDichVu, MaNhaCungCap) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, dichVu.getTenDichVu());
			statement.setString(2, dichVu.getDonViTinh());
			statement.setInt(3, dichVu.getSoLuongTon() == null ? -1 : dichVu.getSoLuongTon());
			statement.setInt(4, dichVu.getDonGiaValue());
			statement.setInt(5, dichVu.getMaLoaiDichVu());
			statement.setInt(6, dichVu.getMaNhaCungCap());
			int output = statement.executeUpdate();
			return output > 0;
		} finally {
			conn.close();
		}

	}

	public static boolean updateDichVu(DichVuDTO dichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE DichVu " + "SET TenDichVu = ?," + "DonViTinh = ?," + "DonGia = ?,"
					+ "SoLuongTon = ?," + "MaLoaiDichVu = ?," + "MaNhaCungCap = ? " + "WHERE MaDichVu = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, dichVu.getTenDichVu());
			statement.setString(2, dichVu.getDonViTinh());
			statement.setInt(3, dichVu.getDonGiaValue());
			statement.setInt(4, dichVu.getSoLuongTon() == null ? -1 : dichVu.getSoLuongTon());
			statement.setInt(5, dichVu.getMaLoaiDichVu());
			statement.setInt(6, dichVu.getMaNhaCungCap());
			statement.setInt(7, dichVu.getMaDichVu());
			int output = statement.executeUpdate();
			return output > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteDichVu(Integer maDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "CALL del_DichVu(?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maDichVu);
			int output = statement.executeUpdate();
			return output > 0;
		} finally {
			conn.close();
		}
	}

	public static DichVuDTO getDichVuById(int maDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM DichVu WHERE MaDichVu = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maDichVu);
			ResultSet rs = statement.executeQuery();
			rs.next();

			LoaiDichVuDTO loaiDichVu = LoaiDichVuBUS.getLoaiDichVuById(rs.getInt("MaLoaiDichVu"));
			NhaCungCapDTO nhaCungCap = NhaCungCapBUS.getNhaCungCapById(rs.getInt("MaNhaCungCap"));
			DichVuDTO output = new DichVuDTO(rs.getInt("MaDichVu"), rs.getString("TenDichVu"),
					rs.getString("DonViTinh"), rs.getInt("SoLuongTon"), rs.getInt("DonGia"), loaiDichVu, nhaCungCap);
			return output;
		} finally {
			conn.close();
		}
	}
}