package br.com.javajdbcdao.application;

import java.util.List;

import br.com.javajdbcdao.model.dao.DaoFactory;
import br.com.javajdbcdao.model.dao.SellerDao;
import br.com.javajdbcdao.model.entities.Department;
import br.com.javajdbcdao.model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		
		List<Seller> sellers = sellerDao.findByDepartmentId(2);
		for (Seller seller2 : sellers) {
			System.out.println(seller2);
		}
		
		
		Department department = new Department(2, null);
		
		List<Seller>sellersTest = sellerDao.findByDepartment(department);
		
		for (Seller seller2 : sellersTest) {
			System.out.println(seller2);
		}
		System.out.println(seller);
	}
}
