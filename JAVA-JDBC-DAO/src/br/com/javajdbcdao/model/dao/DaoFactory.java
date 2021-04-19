package br.com.javajdbcdao.model.dao;

import br.com.javajdbcdao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
