package atividade.questao9.model;

import atividade.questao9.interatividade.Iteratividade;

public class Produto {
	
	Iteratividade interacao = new Iteratividade();
	
	private Integer id;
	private String nome;
	private String descricao;
	private Double desconto;
	private Double valor;
	private String data;
	
	public Produto(Integer id, String nome, String descricao, Double desconto, Double valor, String data) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.valor = valor;
		this.data = interacao.data();
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

	public String getDescricao() {
		return descricao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Double getValor() {
		return valor;
	}

	
	@Override
	public String toString() {
		return "\tid: " + id + " - " + nome +"\n";
	}

	public String getData() {
		return data;
	}
}