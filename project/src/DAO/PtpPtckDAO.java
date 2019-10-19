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
		String query = "SELECT * FROM PTCK_Phong WHERE MaPTPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPTP);
		ResultSet rs = statement.executeQuery();

		List<PtpPtckDTO> output = new ArrayList<PtpPtckDTO>();
		while (rs.next()) {
			PTPhongDTO ptPhong = PTPhongDAO.getPTPhongById(rs.getInt("MaPTPhong"));
			PtpPtckDTO ptp_ptck = new PtpPtckDTO(rs.getInt("MaPTCKPhong"), ptPhong, 
					rs.getString("NoiDung"),
					rs.getInt("SoLuong"),
					rs.getInt("DonGia"),
					rs.getInt("TriGia"));
			output.add(ptp_ptck);
		}
		conn.close();
		return output;
	}

}
