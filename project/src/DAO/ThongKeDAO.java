package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.ThongKeDoanhThuDTO;
import DTO.ThongKeLoaiDichVuDTO;
import DTO.ThongKeLoaiPhongDTO;
import DTO.ThongKeSoKhachDTO;
import helper.DBHelper;

public class ThongKeDAO {

	public static List<ThongKeDoanhThuDTO> getDoanhThuTheoNam(Integer nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_tkDoanhThuNam WHERE Nam = " + nam;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		long tongDoanhThu = 0;
		List<ThongKeDoanhThuDTO> output = new ArrayList<ThongKeDoanhThuDTO>();
		while (rs.next()) {
			output.add(new ThongKeDoanhThuDTO(rs.getInt("Thang"), rs.getInt("DoanhThu")));
			tongDoanhThu += rs.getInt("DoanhThu");
		}
		
		for (ThongKeDoanhThuDTO tk : output) {
			tk.setTile((float) (tk.getDoanhThu()) / tongDoanhThu * 100);
		}
		conn.close();
		return output;
	}

	public static List<ThongKeSoKhachDTO> getSoKhachTheoNam(Integer nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT Thang, LuongKhach FROM view_tkLuongKhachNam WHERE Nam = " + nam;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		List<ThongKeSoKhachDTO> output = new ArrayList<ThongKeSoKhachDTO>();
		while (rs.next()) {
			output.add(new ThongKeSoKhachDTO(rs.getInt("LuongKhach"), rs.getInt("Thang")));
		}
		Integer tongSoKhach = output.stream().mapToInt(ThongKeSoKhachDTO::getSoKhach).sum();
		output.stream().forEach(tk -> tk.setTiLe((float) tk.getSoKhach() / tongSoKhach * 100));
		conn.close();
		return output;
	}

	public static List<ThongKeLoaiPhongDTO> getLoaiPhongTheoThang(Integer thang, Integer nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT LoaiPhongThue, TienPhong FROM view_tkLoaiPhongThang WHERE Thang = ? AND Nam = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, thang);
		statement.setInt(2, nam);
		ResultSet rs = statement.executeQuery();

		List<ThongKeLoaiPhongDTO> output = new ArrayList<ThongKeLoaiPhongDTO>();
		while (rs.next()) {
			output.add(new ThongKeLoaiPhongDTO(rs.getString("LoaiPhongThue"), rs.getInt("TienPhong")));
		}
		Integer tongDoanhThu = output.stream().mapToInt(ThongKeLoaiPhongDTO::getDoanhThu).sum();
		output.stream().forEach(tk -> tk.setTiLe((float) tk.getDoanhThu() / tongDoanhThu * 100));
		conn.close();
		return output;
	}
	
	public static List<ThongKeLoaiDichVuDTO> getLoaiDichVuTheoThang(Integer thang, Integer nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT DoanhThu, TenLoaiDichVu FROM view_tkLoaiDichVuThang WHERE Thang = ? AND Nam = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, thang);
		statement.setInt(2, nam);
		ResultSet rs = statement.executeQuery();
		
		List<ThongKeLoaiDichVuDTO> output = new ArrayList<ThongKeLoaiDichVuDTO>();
		while (rs.next()) {
			output.add(new ThongKeLoaiDichVuDTO(rs.getInt("DoanhThu"), rs.getString("TenLoaiDichVu")));
		}
		
		Integer tongDoanhThu = output.stream().mapToInt(ThongKeLoaiDichVuDTO::getDoanhThu).sum();
		output.stream().forEach(tk -> tk.setTiLe((float) tk.getDoanhThu() / tongDoanhThu * 100));
		return output;
	}
}
