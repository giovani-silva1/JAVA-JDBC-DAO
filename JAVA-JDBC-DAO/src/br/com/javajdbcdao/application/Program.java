package br.com.javajdbcdao.application;

import br.com.javajdbcdao.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		Department department = new Department(1,"books");
		System.out.println(department);
	}
}
