package DAO;

import java.sql.*;
import java.util.*;
import DTO.NhanVienDTO;
import helper.DBHelper;

public class NhanVienDAO {
	public static List<NhanVienDTO> getDSNhanVien() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhanVien";
		ResultSet rs = statement.executeQuery(query);

		List<NhanVienDTO> output = new ArrayList<NhanVienDTO>();
		while (rs.next()) {
			output.add(new NhanVienDTO(
					rs.getInt("MaNhanVien"),
					rs.getString("TenNhanVien"),
					rs.getString("CMND"),
					rs.getString("DiaChi"),
					rs.getString("Email"),
					rs.getString("SoDienThoai"),
					rs.getString("QuyenHan")));
		}
		conn.close();
		return output;
	}
	
	public static NhanVienDTO DangNhap(String taikhoan, String matkhau) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhanVien where TenTaiKhoan= '" + taikhoan + "' and MatKhau = '" + matkhau + "'";
		ResultSet rs = statement.executeQuery(query);
		NhanVienDTO result;
		if(rs.next()) {
			result = new NhanVienDTO(
					rs.getInt("MaNhanVien"),
					rs.getString("TenNhanVien"),
					rs.getString("CMND"),
					rs.getString("DiaChi"),
					rs.getString("Email"),
					rs.getString("SoDienThoai"),
					rs.getString("QuyenHan"));
		}
		else {
			result = null;
		}
		conn.close();
		
		return result;
	}
}