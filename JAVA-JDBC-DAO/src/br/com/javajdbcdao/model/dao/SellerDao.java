package br.com.javajdbcdao.model.dao;

import java.util.List;

import br.com.javajdbcdao.model.entities.Seller;

public interface SellerDao {

	void inserir(Seller seller);

	void update(Seller seller);

	void deletar(Integer id);

	Seller findById(Integer id);

	List<Seller> listAll();
}
