package org.oa.ajax_rest_demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.oa.ajax_rest_demo.model.Tools;

public class ToolsDao implements BaseDao<Tools> {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jeepetshop";
	private static final String USER = "root";
	private static final String PASSWORD = "0okm9ijn8uhb";
	private static final String SQL_INSERT = "INSERT INTO tools (name) VALUES (?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM tools";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM  tools WHERE idtools = ?";
	private static final String SQL_UPDATE_BY_ID = "UPDATE tools SET name = ? WHERE idtools = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM tools WHERE idtools = ?";

	@Override
	public List<Tools> loadAll() {
		List<Tools> foodsList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
			Tools std = null;
			while (rs.next()) {
				std = new Tools();
				std.setIdTools(rs.getInt("idtools"));
				std.setName(rs.getString("name"));
				foodsList.add(std);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodsList;
	}

	@Override
	public Tools findById(long id) {
		Tools tool = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_SELECT_BY_ID);
			pt.setLong(1, id);
			ResultSet rs = pt.executeQuery();
			
			if (rs.next()) {
				tool = new Tools();
				tool.setIdTools(rs.getInt("idtools"));
				tool.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tool;
	}

	@Override
	public Tools create(Tools item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			int n = ps.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("Number of the inserted rows: " + n);
            }
			ResultSet key = ps.getGeneratedKeys();
			if (key.next())
				item.setIdTools(new Integer(key.getInt(1)));
			else
				throw new DatabaseException("Something wrond with getting tools ID");
        } catch (DatabaseException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return item;
	}

	@Override
	public Tools update(Tools item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_UPDATE_BY_ID);
			pt.setString(1, item.getName());
			pt.setInt(2, item.getIdTools());
			if (pt.executeUpdate() != 1)
				throw new DatabaseException("Can't update tool");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean delete(Tools item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_DELETE_BY_ID);
			pt.setInt(1, item.getIdTools());
			return pt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
