package atividade.questao10.util;

import java.util.Scanner;

import atividade.questao10.interatividade.Iteratividade;

public class MenuUtil {
	
	public void showMenu() {
		Scanner leEntrada = new Scanner(System.in);
		Iteratividade interacao = new Iteratividade();
		
		System.out.println("Digite a quantidade de Funcionarios de sua empresa: ");
		Integer qntdFuncionario = leEntrada.nextInt();
		leEntrada.nextLine();
		System.out.println();
		
		interacao.pegaResposta(qntdFuncionario);
		
		
		interacao.mostraResposta();
		
		leEntrada.close();
	}
}
