package BUS;

import DAO.ListDAO;
import java.sql.*;
import java.util.*;

public class ListBUS {
	public static List<String> getListName() throws SQLException {
		return ListDAO.getListName();
	}

	public static boolean deleteListByName(String name) throws SQLException {
		return ListDAO.deleteListByName(name);
	}
	
	public static String getIdByName(String name) throws SQLException {
		return ListDAO.getIdByName(name);
	}
}
