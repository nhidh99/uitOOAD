package DAO;

import java.sql.Connection;
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
	public static List<ThongKeDoanhThuDTO> getDoanhThuTheoThang() throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "Call ThongKeDoanhThu";
		Statement statement = conn.createStatement();
		ResultSet rs =  statement.executeQuery(query);
		
		List<ThongKeDoanhThuDTO> listBaoCao = new ArrayList<ThongKeDoanhThuDTO>();
		while(rs.next()) {
			listBaoCao.add(new ThongKeDoanhThuDTO(rs.getString("Thang"), rs.getInt("TienPhong"), rs.getInt("TienDichVu"), rs.getInt("TienPTCK")));
		}
		return listBaoCao;
	}
	
	public static List<ThongKeLoaiPhongDTO> getThongKeTheoLoaiPhong() throws SQLException{
		Connection conn = DBHelper.getConnection();
		String query = "Call ThongKeTheoLoaiPhong";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		List<ThongKeLoaiPhongDTO> listThongKe = new ArrayList<ThongKeLoaiPhongDTO>();
		while(rs.next()) {
			listThongKe.add(new ThongKeLoaiPhongDTO(rs.getString("LoaiPhongThue"), rs.getInt("TienPhong"), rs.getString("Thang")));
		}
		return listThongKe;
	}
	
	public static List<ThongKeSoKhachDTO> getThongKeSoKhach() throws SQLException{
		Connection conn = DBHelper.getConnection();
		String query = "Call ThongKeSoKhach";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		List<ThongKeSoKhachDTO> listThongKe = new ArrayList<ThongKeSoKhachDTO>();
		while(rs.next()) {
			listThongKe.add(new ThongKeSoKhachDTO(rs.getInt("SoKhach"),rs.getString("Thang")));
		}
		return listThongKe;
	}
	
	public static List<ThongKeLoaiDichVuDTO> getThongKeTheoLoaiDichVu() throws SQLException{
		Connection conn = DBHelper.getConnection();
		String query = "Call ThongKeTheoLoaiDichVu";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		List<ThongKeLoaiDichVuDTO> listThongKe = new ArrayList<ThongKeLoaiDichVuDTO>();
		while(rs.next()) {
			listThongKe.add(new ThongKeLoaiDichVuDTO(rs.getInt("DoanhThu"),rs.getString("Thang"),rs.getString("TenLoaiDichVu")));
		}
		return listThongKe;
	}
}
