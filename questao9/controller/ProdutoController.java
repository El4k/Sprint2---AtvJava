package atividade.questao9.controller;

import java.sql.Connection;
import java.util.List;

import atividade.questao9.dao.ProdutoDAO;
import atividade.questao9.factory.ConnectionFactory;
import atividade.questao9.model.Produto;

public class ProdutoController {
	private ProdutoDAO produtoDAO;
	
	public ProdutoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.produtoDAO = new ProdutoDAO(connection);
	}
	
	public void deletar(Integer id) {
		this.produtoDAO.deletar(id);
	}

	public void inserir(Produto produto) {
		this.produtoDAO.inserir(produto);
	}

	public List<Produto> listar() {
		return this.produtoDAO.listar();
	}
	
	public List<Produto> buscar(Integer id) {
		return this.produtoDAO.buscar(id);
	}
	
	public List<Produto> buscarPalavra(String nome) {
		return this.produtoDAO.buscarPalavra(nome);
	}

	public void alterar(String nome, String descricao, Double desconto, Double valor, Integer id, String data) {
		this.produtoDAO.alterar(nome, descricao, desconto, valor, id, data);
	}
}
