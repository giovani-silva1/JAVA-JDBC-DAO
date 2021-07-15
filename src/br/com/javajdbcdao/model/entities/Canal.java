package br.com.javajdbcdao.model.entities;

import java.io.Serializable;

public class Canal implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String regiao;
	private String horarioInicial;
	private String horarioFinal;

	public Canal() {

	}

	public Canal(Integer id, String nome, String regiao, String horarioInicial, String horarioFinal) {
		this.id = id;
		this.nome = nome;
		this.regiao = regiao;
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
	}
	
	

	public Canal(String nome, String regiao, String horarioInicial) {
		super();
		this.nome = nome;
		this.regiao = regiao;
		this.horarioInicial = horarioInicial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public String getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	@Override
	public String toString() {
		return "Canal [id=" + id + ", nome=" + nome + ", regiao=" + regiao + ", horarioInicial=" + horarioInicial
				+ ", horarioFinal=" + horarioFinal + "]";
	}

}
