package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DTO.HDPtckDTO;
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
	
}
