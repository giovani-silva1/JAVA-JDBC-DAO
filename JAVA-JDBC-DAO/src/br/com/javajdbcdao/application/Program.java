package br.com.javajdbcdao.application;

import java.util.Date;

import br.com.javajdbcdao.entities.Department;
import br.com.javajdbcdao.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department department = new Department(1, "books");

		Seller seller = new Seller(1, "Carla", "carla@gmail.com", new Date(), 3000.00, department);

		System.out.println(department);
		System.out.println(seller);
	}
}
