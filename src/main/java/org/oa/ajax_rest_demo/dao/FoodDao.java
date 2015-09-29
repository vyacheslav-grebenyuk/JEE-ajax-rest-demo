package org.oa.ajax_rest_demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.oa.ajax_rest_demo.model.Food;

public class FoodDao implements BaseDao<Food> {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jeepetshop";
	private static final String USER = "root";
	private static final String PASSWORD = "0okm9ijn8uhb";
	private static final String SQL_INSERT = "INSERT INTO food (name, price) VALUES (?, ?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM food";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM  food WHERE idfood = ?";
	private static final String SQL_UPDATE_BY_ID = "UPDATE food SET name = ?, price = ? WHERE idfood = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM food WHERE idfood = ?";

	@Override
	public List<Food> loadAll() {
		List<Food> foodsList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
			Food std = null;
			while (rs.next()) {
				std = new Food();
				std.setIdFood(rs.getInt("idfood"));
				std.setName(rs.getString("name"));
				std.setPrice(rs.getFloat("price"));
				foodsList.add(std);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodsList;
	}

	@Override
	public Food findById(long id) {
		Food food = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_SELECT_BY_ID);
			pt.setLong(1, id);
			ResultSet rs = pt.executeQuery();
			
			if (rs.next()) {
				food = new Food();
				food.setIdFood(rs.getInt("idfood"));
				food.setName(rs.getString("name"));
				food.setPrice(rs.getFloat("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return food;
	}

	@Override
	public Food create(Food item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setFloat(2, item.getPrice());
			int n = ps.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("Number of the inserted rows: " + n);
            }
			ResultSet key = ps.getGeneratedKeys();
			if (key.next())
				item.setIdFood(new Integer(key.getInt(1)));
			else
				throw new DatabaseException("Something wrond with getting food ID");
        } catch (DatabaseException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return item;
	}

	@Override
	public Food update(Food item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_UPDATE_BY_ID);
			pt.setString(1, item.getName());
			pt.setFloat(2, item.getPrice());
			pt.setInt(3, item.getIdFood());
			if (pt.executeUpdate() != 1)
				throw new DatabaseException("Can't update food");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean delete(Food item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_DELETE_BY_ID);
			pt.setInt(1, item.getIdFood());
			return pt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
