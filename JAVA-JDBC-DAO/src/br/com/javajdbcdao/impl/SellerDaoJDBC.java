package br.com.javajdbcdao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.Result;

import br.com.javajdbcdao.model.dao.SellerDao;
import br.com.javajdbcdao.model.entities.Department;
import br.com.javajdbcdao.model.entities.Seller;
import db.DB;
import db.DbException;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void inserir(Seller seller) {

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conn.prepareStatement("INSERT INTO SELLER (name,email,birthdate,basesalary,departmentid) values (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getName());
			preparedStatement.setString(2, seller.getEmail());
			preparedStatement.setDate(3, new Date(seller.getBirthDate().getTime()));
			preparedStatement.setDouble(4, seller.getBaseSalary());
			preparedStatement.setInt(5, seller.getDepartment().getId());

			int rows = preparedStatement.executeUpdate();

			if (rows > 0) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
			} else {
				throw new DbException("Erro inesperado nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);

		}

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = conn.prepareStatement(
					"select seller.*,department.Name as DepName from seller inner join department on seller.DepartmentId = department.Id where seller.Id = ?;");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Department department = instanciateDepartment(resultSet);
				Seller seller = instanciateSeller(resultSet, department);
				return seller;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

	private Seller instanciateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(resultSet.getInt("Id"));
		seller.setName(resultSet.getString("Name"));
		seller.setEmail(resultSet.getString("Email"));
		seller.setBirthDate(resultSet.getDate("BirthDate"));
		seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
		seller.setDepartment(department);
		return seller;
	}

	private Department instanciateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> listAll() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = conn.prepareStatement(
					"select seller.*,department.Name as 'DepName' from seller inner join department department on department.Id = seller.DepartmentId order by name");
			resultSet = preparedStatement.executeQuery();
			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> departaments = new HashMap<Integer, Department>();
			while (resultSet.next()) {
				Department dep = departaments.get(resultSet.getInt("DepartmentId"));
				if (dep == null) {
					dep = instanciateDepartment(resultSet);
					departaments.put(resultSet.getInt("DepartmentId"), dep);
				}
				Seller seller = instanciateSeller(resultSet, dep);
				sellers.add(seller);

			}
			return sellers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findByDepartmentId(Integer id) {
		List<Seller> sellers = new ArrayList<Seller>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select seller.*,department.Name as 'DepName' from seller inner join department on seller.DepartmentId = department.Id where department.Id = ? order by name");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Department department = instanciateDepartment(resultSet);
				Seller seller = instanciateSeller(resultSet, department);
				sellers.add(seller);
			}
			return sellers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select seller.*,department.Name as 'DepName' from seller inner join department on seller.DepartmentId = department.Id where department.Id = ? order by name");
			preparedStatement.setInt(1, department.getId());
			resultSet = preparedStatement.executeQuery();

			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> departments = new HashMap<Integer, Department>();

			while (resultSet.next()) {
				Department dep = departments.get(resultSet.getInt("DepartmentId"));
				if (dep == null) {
					dep = instanciateDepartment(resultSet);
					departments.put(resultSet.getInt("DepartmentId"), dep);
				}

				Seller seller = instanciateSeller(resultSet, dep);
				sellers.add(seller);

			}

			return sellers;
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

}
