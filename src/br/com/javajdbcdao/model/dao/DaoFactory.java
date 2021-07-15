package br.com.javajdbcdao.model.dao;

import br.com.javajdbcdao.impl.CanalDaoJDBC;
import db.DB;

public class DaoFactory {

	public static CanalDao createCanalDao() {
		return new CanalDaoJDBC(DB.getConnection());
	}

}
