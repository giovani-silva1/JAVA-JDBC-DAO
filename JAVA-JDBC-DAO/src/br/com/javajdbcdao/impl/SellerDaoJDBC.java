package br.com.javajdbcdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartmentId(Integer id) {
		List<Seller> sellers = new ArrayList<Seller>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("select seller.*,department.Name as 'DepName' from seller inner join department on seller.DepartmentId = department.Id where department.Id = ? order by name");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Department department = instanciateDepartment(resultSet);
				Seller seller = instanciateSeller(resultSet, department);
				sellers.add(seller);
			}
			return sellers;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
			preparedStatement = conn.prepareStatement("select seller.*,department.Name as 'DepName' from seller inner join department on seller.DepartmentId = department.Id where department.Id = ? order by name");
			preparedStatement.setInt(1, department.getId());
			resultSet = preparedStatement.executeQuery();
			
			List<Seller>sellers = new ArrayList<Seller>();
			Map<Integer,Department> departments = new HashMap<Integer, Department>();
			
			while(resultSet.next()) {
				Department dep = departments.get(resultSet.getInt("DepartmentId"));
				if(dep == null) {
					dep = instanciateDepartment(resultSet);
					departments.put(resultSet.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instanciateSeller(resultSet, dep);
				sellers.add(seller);
				
			}
		
			return sellers;
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		
	
	}

}
