package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.PTPhongDTO;
import DTO.PtpPtckDTO;
import helper.DBHelper;

public class PtpPtckDAO {

	public static List<PtpPtckDTO> getDSPtckByMaPTP(Integer maPTP) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM PTCK_Phong WHERE MaPTPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maPTP);
			ResultSet rs = statement.executeQuery();

			List<PtpPtckDTO> output = new ArrayList<PtpPtckDTO>();
			while (rs.next()) {
				PTPhongDTO ptPhong = PTPhongDAO.getPTPhongById(rs.getInt("MaPTPhong"));
				PtpPtckDTO ptp_ptck = new PtpPtckDTO(rs.getInt("MaPTCKPhong"), ptPhong, rs.getString("NoiDung"),
						rs.getInt("SoLuong"), rs.getInt("DonGia"), rs.getInt("TriGia"));
				output.add(ptp_ptck);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean insertPtpPtcK(PtpPtckDTO ptck) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO PTCK_Phong (MaPTPhong, NoiDung, SoLuong, DonGia, TriGia) VALUES (?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, ptck.getMaPTPhong());
			statement.setString(2, ptck.getNoiDung());
			statement.setInt(3, ptck.getSoLuong());
			statement.setInt(4, ptck.getDonGiaValue());
			statement.setInt(5, ptck.getTriGiaValue());
			int output = statement.executeUpdate();
			return output > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean deletePtpPtck(Integer maPTCKPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "DELETE FROM PTCK_Phong WHERE MaPTCKPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maPTCKPhong);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updatePtpPtck(PtpPtckDTO ptck) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE PTCK_Phong SET NoiDung = ?, SoLuong = ?, DonGia = ?, TriGia = ? WHERE MaPTCKPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, ptck.getNoiDung());
			statement.setInt(2, ptck.getSoLuong());
			statement.setInt(3, ptck.getDonGiaValue());
			statement.setInt(4, ptck.getTriGiaValue());
			statement.setInt(5, ptck.getMaPTCKPhong());
			int output = statement.executeUpdate();
			return output > 0;
		} finally {
			conn.close();
		}
	}

}
