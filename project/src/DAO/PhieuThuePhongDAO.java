package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.PhieuThuePhongDTO;
import helper.DBHelper;

public class PhieuThuePhongDAO {
	public static PhieuThuePhongDTO getPTPhongByMaPhong(Integer maPhong) throws SQLException {
		//To do: handle trÆ°á»�ng há»£p 1 phÃ²ng Ä‘Ã£ Ä‘Æ°á»£c thuÃª nhiá»�u láº§n => láº¥y phiáº¿u thuÃª phÃ²ng má»›i nháº¥t.
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from PT_Phong where MaPhong = '" + maPhong + "'";
		ResultSet rs = statement.executeQuery(query);
		PhieuThuePhongDTO pt_Phong = null;
		if(rs.next()) {
			pt_Phong= new PhieuThuePhongDTO(
				rs.getInt("MaPTPhong"),
				rs.getInt("MaPhong"),
				rs.getString("LoaiPhongThue"),
				rs.getInt("SoKhachToiDa"),
				rs.getDate("NgayNhan"),
				rs.getDate("NgayTra"),
				rs.getInt("DonGiaThue")
				);
		}
		conn.close();
		return pt_Phong;
	}
}