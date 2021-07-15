package br.com.javajdbcdao.model.dao;

import br.com.javajdbcdao.model.entities.Canal;

public interface CanalDao {

	void inserir(Canal canal);

	Canal encontrarPorRegiaoHorario(String regiao, String horarioInicial);

}
