package org.oa.ajax_rest_demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.oa.ajax_rest_demo.model.Pet;

public class PetDao implements BaseDao<Pet> {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jeepetshop";
	private static final String USER = "root";
	private static final String PASSWORD = "0okm9ijn8uhb";
	private static final String SQL_INSERT = "INSERT INTO pets (name, age) VALUES (?, ?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM  pets";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM  pets WHERE idpets = ?";
	private static final String SQL_UPDATE_BY_ID = "UPDATE pets SET name = ?, age = ? WHERE idpets = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM pets WHERE idpets = ?";

	@Override
	public List<Pet> loadAll() {
		List<Pet> petsList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
			Pet std = null;
			while (rs.next()) {
				std = new Pet();
				std.setIdPets(rs.getInt("idpets"));
				std.setName(rs.getString("name"));
				std.setAge(rs.getInt("age"));
				petsList.add(std);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petsList;
	}

	@Override
	public Pet findById(long id) {
		Pet pet = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_SELECT_BY_ID);
			pt.setLong(1, id);
			ResultSet rs = pt.executeQuery();
			
			if (rs.next()) {
				pet = new Pet();
				pet.setIdPets(rs.getInt("idpets"));
				pet.setName(rs.getString("name"));
				pet.setAge(rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pet;
	}

	@Override
	public Pet create(Pet item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getAge());
			int n = ps.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("Number of the inserted rows: " + n);
            }
			ResultSet key = ps.getGeneratedKeys();
			if (key.next())
				item.setIdPets(new Integer(key.getInt(1)));
			else
				throw new DatabaseException("Something wrond with getting pet ID");
        } catch (DatabaseException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return item;
	}

	@Override
	public Pet update(Pet item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_UPDATE_BY_ID);
			pt.setString(1, item.getName());
			pt.setInt(2, item.getAge());
			pt.setInt(3, item.getIdPets());
			if (pt.executeUpdate() != 1)
				throw new DatabaseException("Can't update pet");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean delete(Pet item) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pt = conn.prepareStatement(SQL_DELETE_BY_ID);
			pt.setInt(1, item.getIdPets());
			return pt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
