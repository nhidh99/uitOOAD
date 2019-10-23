package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.PTPhongDTO;
import DTO.KhachDTO;
import helper.DBHelper;

public class KhachDAO {
	public static List<KhachDTO> getDSKhachByMaPTP(Integer maPTP) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM KhachHang WHERE MaPTPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maPTP);
		ResultSet rs = statement.executeQuery();

		List<KhachDTO> output = new ArrayList<KhachDTO>();
		while (rs.next()) {
			PTPhongDTO ptPhong = PTPhongDAO.getPTPhongById(rs.getInt("MaPTPhong"));
			KhachDTO khach = new KhachDTO(rs.getInt("MaKhachHang"), ptPhong, rs.getString("HoTen"),
					rs.getString("CMND"), rs.getString("SoDienThoai"), rs.getString("GioiTinh"),
					rs.getString("QuocTich"));
			output.add(khach);
		}
		conn.close();
		return output;
	}
}
