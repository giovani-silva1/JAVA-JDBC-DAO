package br.com.javajdbcdao.application;

import java.util.Date;
import java.util.List;

import br.com.javajdbcdao.model.dao.DaoFactory;
import br.com.javajdbcdao.model.dao.SellerDao;
import br.com.javajdbcdao.model.entities.Department;
import br.com.javajdbcdao.model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		Seller seller = sellerDao.findById(3);

		System.out.println(" ---------- LISTANDO POR DEPARTMENTID -----------------------");
		List<Seller> sellers = sellerDao.findByDepartmentId(2);
		for (Seller seller2 : sellers) {
			System.out.println(seller2);
		}

		Department department = new Department(2, null);

		System.out.println(" ---------- LISTANDO POR DEPARTMENT -----------------------");
		List<Seller> sellersTest = sellerDao.findByDepartment(department);

		for (Seller seller2 : sellersTest) {
			System.out.println(seller2);
		}

		System.out.println(" ---------- LISTANDO TODOS-----------------------");

		List<Seller> sellersListAll = sellerDao.listAll();
		for (Seller seller2 : sellersListAll) {
			System.out.println(seller2);
		}

		System.out.println("------------------- NOVO SELLER --------------------");

		Seller sellerNew = new Seller(null, "Paulo", "Paulo@novoSeller.com", new Date(), 90.000, department);
		sellerDao.inserir(sellerNew);
		System.out.println("Novo Vendedor com o novo id é : " + sellerNew.getId());

		System.out.println("------------------ FAZENDO UPDATE ------------------");
		Seller sellerUpdate = sellerDao.findById(1);
		sellerUpdate.setName("Nome updated");
		System.out.println("UPDATE REALIZADO COM SUCESSO");
		sellerDao.update(sellerUpdate);

	}
}
