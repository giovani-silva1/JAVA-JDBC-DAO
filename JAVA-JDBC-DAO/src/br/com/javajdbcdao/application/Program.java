package br.com.javajdbcdao.application;

import br.com.javajdbcdao.model.dao.DaoFactory;
import br.com.javajdbcdao.model.dao.SellerDao;
import br.com.javajdbcdao.model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		Seller seller = sellerDao.findById(3);

		System.out.println(seller);
	}
}
