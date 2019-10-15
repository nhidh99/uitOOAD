package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.DichVuDTO;
import DTO.PTP_DichVuDTO;
import helper.DBHelper;

public class PTP_DichVuDAO {
	public static List<PTP_DichVuDTO> getDSDichVuByMaPT(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "select * from ptp_dv join DichVu ON ptp_dv.MaDichVu = DichVu.MaDichVu where MaPTPhong = '" + maPhieuThue + "'";
		ResultSet rs = statement.executeQuery(query);

		List<PTP_DichVuDTO> output = new ArrayList<PTP_DichVuDTO>();
		while (rs.next()) {
			DichVuDTO dichVu = new DichVuDTO(
					rs.getString("TenDichVu"),
					rs.getString("DonViTinh"), 
					rs.getInt("SoLuongTon"),
					rs.getInt("DonGia"));
			PTP_DichVuDTO ptp_dichVu = new PTP_DichVuDTO(
					rs.getInt("MaPTPDV"),
					rs.getInt("MaPTPhong"),
					dichVu,
					rs.getInt("SoLuong"),
					rs.getInt("GiaDichVu"),
					rs.getInt("ThanhTien")
					);
			output.add(ptp_dichVu);
		}
		conn.close();
		return output;
	}
	
	public static boolean insertPTP_DichVu(PTP_DichVuDTO ptp_dichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO ptp_dv (MaPTPhong, MaDichVu, SoLuong, GiaDichVu, ThanhTien) VALUES (?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ptp_dichVu.getMaPTPhong());
		statement.setInt(2, ptp_dichVu.getMaDichVu());
		statement.setInt(3, ptp_dichVu.getSoLuong());
		statement.setInt(4, ptp_dichVu.getDonGiaValue());
		statement.setInt(5, ptp_dichVu.getThanhTienValue());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
	public static boolean deletePTP_DichVu(Integer maPTP_DichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = String.format("DELETE FROM ptp_dv WHERE MaPTPDV = '%d'", maPTP_DichVu);
		int output = statement.executeUpdate(query);
		conn.close();
		return output > 0 ;
	}
	
	public static Integer getMaxMaPTP_DichVu() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT MAX(MaPTPDV) FROM ptp_dv";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		Integer output = rs.getInt(1);
		conn.close();
		return output;
	}

	public static boolean updatePTP_DichVu(PTP_DichVuDTO ptp_dichVu) {
		/*
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE ptp_dv "
				+ "SET TenNhanVien = ?, CMND = ?, DiaChi = ?, Email = ?, SoDienThoai = ?, ChucVu = ? "
				+ "WHERE MaPTPDV = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nhanVien.getTenNhanVien());
		statement.setString(2, nhanVien.getCMND());
		statement.setString(3, nhanVien.getDiaChi());
		statement.setString(4, nhanVien.getEmail());
		statement.setString(5, nhanVien.getSoDienThoai());
		statement.setString(6, nhanVien.getChucVu());
		statement.setInt(7, ptp_dichVu.getMaPTPDichVu());
		statement.execute();
		conn.close();*/
		return true;
	}
}