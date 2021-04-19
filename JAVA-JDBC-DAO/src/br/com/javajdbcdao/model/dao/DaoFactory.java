package br.com.javajdbcdao.model.dao;

import br.com.javajdbcdao.impl.SellerDaoJDBC;
import db.DB;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
