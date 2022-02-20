package atividade.questao10.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String resposta;
	
	public Funcionario(String nome, String resposta) {
		this.nome = nome;
		this.resposta = resposta;
	}
	
	Funcionario() {
	}

	public String getResposta() {
		return resposta;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "\tFuncionario: " + nome + ", Humor: " + resposta + "\n";
	}
}