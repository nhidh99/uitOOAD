package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.ProductDTO;
import helper.DBHelper;

public class ProductDAO {
	public static List<ProductDTO> getProducts() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from SanPham";
		ResultSet rs = statement.executeQuery(query);

		List<ProductDTO> output = new ArrayList<ProductDTO>();
		while (rs.next()) {
			ProductDTO product = new ProductDTO(
					rs.getString("MaSP"),
					rs.getString("TenSP"),
					rs.getInt("SoLuong"),
					rs.getInt("DonGia"),
					rs.getString("XuatXu"),
					rs.getString("MaDM"));
			output.add(product);
		}
		conn.close();
		return output;
	}

	public static String getListById(String id) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT TenDM FROM DanhMuc WHERE MaDM = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);

		ResultSet rs = statement.executeQuery();
		rs.next();
		String output = rs.getString("TenDM");
		conn.close();
		return output;
	}

	public static boolean checkProductById(String id) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT COUNT(*) AS 'output' FROM SanPham WHERE MaSP = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);

		ResultSet rs = statement.executeQuery();
		rs.next();
		int output = rs.getInt("output");
		conn.close();
		return output > 0;
	}

	public static boolean insertProduct(ProductDTO product) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO SanPham (MaSP, TenSP, SoLuong, DonGia, XuatXu, MaDM) VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, product.getId());
		statement.setString(2, product.getName());
		statement.setInt(3, product.getNumber());
		statement.setInt(4, product.getPrice());
		statement.setString(5, product.getOrigin());
		statement.setString(6, product.getListId());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}

	public static boolean updateProduct(ProductDTO product) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE SanPham\n"
				+ "SET TenSP = ?,"
				+ "SoLuong = ?,"
				+ "DonGia = ?,"
				+ "XuatXu = ?,"
				+ "MaDM = ?\n"
				+ "WHERE MaSP = ?";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, product.getName());
		statement.setInt(2, product.getNumber());
		statement.setInt(3, product.getPrice());
		statement.setString(4, product.getOrigin());
		statement.setString(5, product.getListId());
		statement.setString(6, product.getId());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
}
