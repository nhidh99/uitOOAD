package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.DichVuDTO;
import DTO.PTPhongDTO;
import DTO.PtpDichVuDTO;
import helper.DBHelper;

public class PtpDichVuDAO {

	public static List<PtpDichVuDTO> getDSDichVuByMaPTP(Integer maPTP) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM PTP_DV WHERE MaPTPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPTP);
		ResultSet rs = statement.executeQuery();

		List<PtpDichVuDTO> output = new ArrayList<PtpDichVuDTO>();
		while (rs.next()) {
			DichVuDTO dichVu = DichVuDAO.getDichVuById(rs.getInt("MaDichVu"));
			PTPhongDTO ptPhong = PTPhongDAO.getPTPhongById(rs.getInt("MaPTPhong"));
			PtpDichVuDTO ptp_dv = new PtpDichVuDTO(rs.getInt("MaPTPDV"), ptPhong, dichVu, rs.getInt("SoLuong"),
					rs.getInt("GiaDichVu"), rs.getInt("ThanhTien"));
			output.add(ptp_dv);
		}
		conn.close();
		return output;
	}

	public static boolean insertPtpDichVu(PtpDichVuDTO ptp_dv) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO PTP_DV (MaPTPhong, MaDichVu, SoLuong, GiaDichVu, ThanhTien) "
				+ "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ptp_dv.getPTPhong().getMaPTPhong());
		statement.setInt(2, ptp_dv.getMaDichVu());
		statement.setInt(3, ptp_dv.getSoLuong());
		statement.setInt(4, ptp_dv.getDonGiaValue());
		statement.setInt(5, ptp_dv.getThanhTienValue());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean deletePtpDichVu(Integer maPTPDichVu) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM PTP_DV WHERE MaPTPDV = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPTPDichVu);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static boolean updatePtpDichVu(PtpDichVuDTO ptp_dv) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE PTP_DV SET GiaDichVu = ?, ThanhTien = ? WHERE MaPTPDV = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ptp_dv.getDonGiaValue());
		statement.setInt(2, ptp_dv.getThanhTienValue());
		statement.setInt(3, ptp_dv.getMaPTPDichVu());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
}
