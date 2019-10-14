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
	
	public static boolean checkPTP_DichVu(PTP_DichVuDTO ptp_dichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT (EXISTS (SELECT 1 FROM ptp_dv WHERE MaPTPhong = ? AND MaDichVu = ?)"
				+ "AND (EXISTS (SELECT 1 FROM DichVu WHERE MaDichVu = ? AND KhaDung = 1 AND SoLuongTon != 0)))";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ptp_dichVu.getMaPTPhong());
		statement.setInt(2, ptp_dichVu.getMaDichVu());
		statement.setInt(3, ptp_dichVu.getMaDichVu());
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}

	
}