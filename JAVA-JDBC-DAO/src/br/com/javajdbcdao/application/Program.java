package br.com.javajdbcdao.application;

import java.util.Date;

import br.com.javajdbcdao.model.dao.DaoFactory;
import br.com.javajdbcdao.model.dao.SellerDao;
import br.com.javajdbcdao.model.entities.Department;
import br.com.javajdbcdao.model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department department = new Department(1, "books");

		Seller seller = new Seller(1, "Carla", "carla@gmail.com", new Date(), 3000.00, department);

		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println(department);
		System.out.println(seller);
	}
}
