package atividade.questao10.interatividade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import atividade.questao10.dao.FuncionarioDao;
import atividade.questao10.model.Funcionario;
import atividade.questao10.util.JPAUtil;

public class Iteratividade {
	EntityManager em = JPAUtil.getEntityManager();
	Scanner leEntrada = new Scanner(System.in);
	String resposta, nome;
	Integer qntdFuncionario = 0;

	public void pegaResposta(Integer qntd) {
		for (Integer i = 0; i < qntd; i++) {
			em.getTransaction().begin();
			System.out.println("Escreva seu nome: ");
			nome = leEntrada.nextLine();

			System.out.println();
			
			System.out.println("Expresse o seu sentimento: ");
			resposta = leEntrada.nextLine();
			
			System.out.println();
			
			Funcionario funcionario = new Funcionario(nome, analisaResposta(resposta));
			funcionarioEntity().cadastrar(funcionario);
			
			em.getTransaction().commit();
		}
	}

	public FuncionarioDao funcionarioEntity() {
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		return funcionarioDao;
	}

	public String analisaResposta(String palavra) {
		char temp;
		Integer iF = 0, iC = 0;
		for (int i = 0; i < palavra.length(); i++) {
			temp = palavra.charAt(i);
			if (temp == ':') {
				temp = palavra.charAt(i + 1);
				if (temp == '-') {
					temp = palavra.charAt(i + 2);
					if (temp == ')')
						iF++;
					else if (temp == '(')
						iC++;
				}
			}
		}
		if (iF > iC)
			return "divertido";
		else if (iF < iC)
			return "chateado";
		else
			return "neutro";
	}
	
	public void mostraResposta() {
		List<Funcionario> busca = new ArrayList<Funcionario>();
		busca = funcionarioEntity().buscarTodos();
		System.out.println(busca);
		
		System.out.println("Digite o nome do funcionario para visualizar seu sentimento: ");
		nome = leEntrada.nextLine();
		busca = funcionarioEntity().buscarPorNome(nome);
		
		System.out.println("Funcionario: " + busca);
		em.close();
	}
}