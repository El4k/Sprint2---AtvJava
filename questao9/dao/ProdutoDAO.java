package atividade.questao9.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import atividade.questao9.model.Produto;

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void inserir(Produto produto) {
		try {
			String sql = "INSERT INTO PRODUTO (ID, NOME, DESCRICAO, VALOR, DESCONTO, DATA) VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, produto.getId());
				pstm.setString(2, produto.getNome());
				pstm.setString(3, produto.getDescricao());
				pstm.setDouble(4, produto.getDesconto());
				pstm.setDouble(5, produto.getValor());
				pstm.setString(6, produto.getData());
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Produto> listar() {
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			String sql = "SELECT ID, NOME, DESCRICAO, VALOR, DESCONTO, DATA FROM PRODUTO";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmProduto(produtos, pstm);
			}
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Produto> buscar(Integer id) {
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			String sql = "SELECT ID, NOME, DESCRICAO, VALOR, DESCONTO, DATA FROM PRODUTO WHERE ID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();

				trasformarResultSetEmProduto(produtos, pstm);
			}
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> buscarPalavra(String nome) {
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			String sql = "SELECT ID, NOME, DESCRICAO, VALOR, DESCONTO, DATA FROM PRODUTO WHERE NOME LIKE ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, "%" + nome + "%");
//				pstm.setString(2, "%" + palavra + "%");
				pstm.execute();

				trasformarResultSetEmProduto(produtos, pstm);
			}
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(String nome, String descricao, Double desconto, Double valor, Integer id, String data) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ?, P.VALOR = ?, P.DESCONTO = ?, P.DATA = ? WHERE ID = ?")) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setDouble(3, valor);
			stm.setDouble(4, desconto);
			stm.setString(5, data);
			stm.setInt(6, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
		try(ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3), 
						rst.getDouble(4), rst.getDouble(5), rst.getString(6));
				produtos.add(produto);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
