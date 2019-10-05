package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.ProductDAO;
import DTO.ProductDTO;

public class ProductBUS {
	public static List<ProductDTO> getProducts() throws SQLException {
		return ProductDAO.getProducts();
	}
	
	public static String getListById(String id) throws SQLException {
		return ProductDAO.getListById(id);
	}

	public static boolean insertProduct(ProductDTO product) throws SQLException {
		if (ProductDAO.checkProductById(product.getId())) {
			return false;
		}
		return ProductDAO.insertProduct(product);
	}

	public static boolean updateProduct(ProductDTO product) throws SQLException {
		if (ProductDAO.checkProductById(product.getId())) {
			return ProductDAO.updateProduct(product);
		}
		return false;
	}
}
