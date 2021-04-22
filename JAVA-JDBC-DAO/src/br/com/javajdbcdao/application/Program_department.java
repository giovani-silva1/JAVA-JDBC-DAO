package br.com.javajdbcdao.application;

import java.util.List;

import br.com.javajdbcdao.model.dao.DaoFactory;
import br.com.javajdbcdao.model.dao.DepartmentDao;
import br.com.javajdbcdao.model.entities.Department;

public class Program_department {

	public static void main(String[] args) {

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("--------- CRIANDO UM NOVO DEPARTAMENTO --------------- ");

		Department department = new Department(null, "NOVO DEPARTAMENTO");

		System.out.println("salvando departamento");
		departmentDao.insert(department);

		System.out.println("--------- DELETANDO UM NOVO DEPARTAMENTO ---------------");
		departmentDao.deletar(8);
		System.out.println("Deletado o departamento");

		System.out.println("---------  ENCONTRANDO UM DEPARTAMENTO ------------- ");

		Department depEncontrado = departmentDao.findById(9);
		System.out.println(depEncontrado);

		System.out.println("-------- MODIFICAR UM DEPARTAMENTO -------------");
		Department depEncontrado2 = departmentDao.findById(9);
		depEncontrado2.setName("Mudei agora para outro nome");
		departmentDao.update(depEncontrado2);
		
		System.out.println("-------- ENCONTRANDO A LISTA DE DEPARTAMENTOS --------");
		
		List<Department> departamentosEncontrados = departmentDao.findAll();
		for (Department department2 : departamentosEncontrados) {
			System.out.println(department2);
		}
	}
}
