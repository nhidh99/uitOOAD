package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BUS.HoaDonBUS;
import DTO.HDPtckDTO;
import DTO.HoaDonDTO;
import helper.DBHelper;

public class HDPtckDAO {

	public static boolean insertHDPtck(HDPtckDTO hdPtck) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO PTCK_HoaDon (MaHoaDon, NoiDung, TriGia) VALUES (?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, hdPtck.getHoaDon().getMaHoaDon());
		statement.setString(2, hdPtck.getNoiDung());
		statement.setInt(3, hdPtck.getTriGiaValue());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static List<HDPtckDTO> getDSHDPtckByMaHD(Integer maHD) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM PTCK_HoaDon WHERE MaHoaDon = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maHD);
		ResultSet rs = statement.executeQuery();
		List<HDPtckDTO> output = new ArrayList<HDPtckDTO>();
		while (rs.next()) {
			HoaDonDTO hoaDon = HoaDonBUS.getHoaDonById(maHD);
			HDPtckDTO ptckHD = new HDPtckDTO(rs.getInt("MaPTCKHD"), hoaDon, rs.getString("NoiDung"),
					rs.getInt("TriGia"));
			output.add(ptckHD);
		}
		conn.close();
		return output;
	}
}
