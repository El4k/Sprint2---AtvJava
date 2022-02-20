package atividade.questao9.model;

import java.util.Scanner;

import atividade.questao9.interatividade.Iteratividade;

public class Menu {
	Iteratividade interacao = new Iteratividade();
	Scanner leEntrada = new Scanner(System.in);
	Integer opcao;

	public void funcionalidades_Menu() {
		do {
			boolean help = true;
			while (help) {
				try {
					interacao.show_Menu();
					opcao = leEntrada.nextInt();
					help = false;
				} catch (Exception e) {
					System.err.println("Entrada Invalida!\n");
					continue;
				} finally {
					leEntrada.nextLine();
				}
			}
			switch (opcao) {
			case 1:
				interacao.insercao(0);
				break;

			case 2:
				interacao.alteracao();
				break;

			case 3:
				interacao.exclusao();
				break;

			case 4:
				interacao.buscaPalavra();
				break;

			case 0:
				System.out.println("Obrigado pela sua visita!");
				leEntrada.close();
				break;

			default:
				System.out.println("Digite uma opcao valida!");
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		} while (opcao != 0);
	}
}