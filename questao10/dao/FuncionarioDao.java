package atividade.questao10.dao;

import java.util.List;

import javax.persistence.EntityManager;

import atividade.questao10.model.Funcionario;

public class FuncionarioDao {
	private EntityManager em;
	
	public FuncionarioDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Funcionario funcionario) {
		this.em.persist(funcionario);
	}
	
	public void atualizar(Funcionario funcionario) {
		this.em.merge(funcionario);
	}
	
	public List<Funcionario> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Funcionario p WHERE p.nome = :nome";
		return em.createQuery(jpql, Funcionario.class).
				setParameter("nome", nome).
				getResultList();
	}
	
	public List<Funcionario> buscarTodos() {
		String jpql = "SELECT p FROM Funcionario p";
		return em.createQuery(jpql, Funcionario.class).getResultList();
	}
	
}