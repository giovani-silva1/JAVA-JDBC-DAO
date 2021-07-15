package br.com.javajdbcdao.application;

import java.util.Scanner;

import br.com.javajdbcdao.model.dao.CanalDao;
import br.com.javajdbcdao.model.dao.DaoFactory;
import br.com.javajdbcdao.model.entities.Canal;

public class Program_cadastrarCanal {

	public static void main(String[] args) {

		CanalDao canalDao = DaoFactory.createCanalDao();
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		String novoCanalRegistrado = sc.nextLine();
		String regiao = novoCanalRegistrado.substring(0, 2);
		String nomeCanal = novoCanalRegistrado.substring(3, 13);
		String horarioInicial = novoCanalRegistrado.substring(13, 19);
		String horarioFinal = novoCanalRegistrado.substring(19, 25);
		
		canalDao.inserir(new Canal(null, nomeCanal, regiao, horarioInicial, horarioFinal));
		
		sc.close();
		
		
	}
}
