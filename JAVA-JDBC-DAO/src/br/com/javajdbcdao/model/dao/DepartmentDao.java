package br.com.javajdbcdao.model.dao;

import java.util.List;

import br.com.javajdbcdao.model.entities.Department;

public interface DepartmentDao {

	void update(Department department);

	void insert(Department department);

	void deletar(Integer id);

	Department findById(Integer id);

	List<Department> findAll();
}
