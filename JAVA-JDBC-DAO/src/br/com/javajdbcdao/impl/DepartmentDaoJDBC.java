package br.com.javajdbcdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javajdbcdao.model.dao.DepartmentDao;
import br.com.javajdbcdao.model.entities.Department;
import db.DB;
import db.DbException;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void update(Department department) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("UPDATE DEPARTMENT SET NAME = ? WHERE ID = ?");
			preparedStatement.setString(1, department.getName());
			preparedStatement.setInt(2, department.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void insert(Department department) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO DEPARTMENT (Name) VALUES (?)");
			preparedStatement.setString(1, department.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}

	}

	@Override
	public void deletar(Integer id) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE ID = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = conn.prepareStatement("SELECT * from department where id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			Department department = null;
			while (resultSet.next()) {
				int idEncontrado = resultSet.getInt("id");
				String nameDepartment = resultSet.getString("Name");
				department = new Department(idEncontrado, nameDepartment);

			}
			return department;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Department> departments = new ArrayList<Department>();

		try {
			preparedStatement = conn.prepareStatement("select * from department");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Department department = new Department(resultSet.getInt("id"), resultSet.getString("name"));
				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
