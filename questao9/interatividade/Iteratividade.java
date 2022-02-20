package atividade.questao9.interatividade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import atividade.questao9.controller.ProdutoController;
import atividade.questao9.model.Produto;

public class Iteratividade {
	ProdutoController control = new ProdutoController();
	Scanner leEntrada = new Scanner(System.in);
	Integer id = 0, alteration = 0;
	String nome, descricao;
	Double desconto = 0.0, valor = 0.0;

	public void show_Menu() {
		System.out.println("<--- Bem Vindo ao Menu --->\n");
		System.out.println("[1] CADASTRAR\tProduto!");
		System.out.println("[2] ATUALIZAR\tProduto!");
		System.out.println("[3] EXCLUIR\tProduto!");
		System.out.println("[4] BUSCAR\tProduto!");
		System.out.println("[0] SAIR!");
		System.out.println("\nDigite sua opcao: ");
	}

	public void insercao(Integer i) {
		leEntrada.useLocale(Locale.ENGLISH);
		boolean help = true;
		while (help) {
			try {
				if (i == 0) {
					while (id <= 0) {
						System.out.println("Insira o ID do Produto: ");
						id = leEntrada.nextInt();
						leEntrada.nextLine();
					}
				} else
					id = i;
				System.out.println("Insira o nome do Produto: ");
				nome = leEntrada.nextLine();
				System.out.println("Insira a descricao do Produto: ");
				descricao = leEntrada.nextLine();
				while (valor <= 0.0 || desconto <= 0.0) {
					System.out.println("Insira o valor do Produto: ");
					valor = leEntrada.nextDouble();
					leEntrada.nextLine();
					System.out.println("Insira o desconto do Produto: ");
					desconto = leEntrada.nextDouble();
					leEntrada.nextLine();
					if (desconto >= valor)
						System.err.println("Valor e Desconto devem ser valores positivos e o valor tem que ser maior que o desconto!\n");
				}
				help = false;
			} catch (Exception e) {
				System.out.println("Entrada Invalida!\n");
				continue;
			} finally {
				if (help == false)
					System.out.println("Aperte *Enter*!");
				leEntrada.nextLine();
			}
		}
		Produto product = new Produto(id, nome, descricao, valor, desconto, data());
		control.inserir(product);
		insercaoExtra();
		System.out.println("Produto adicionado!");
	}

	public void exclusao() {
		boolean help = true;
		while (help) {
			try {
				System.out.println("Insira o ID do Produto: ");
				id = leEntrada.nextInt();
				leEntrada.nextLine();
				control.deletar(id);
				System.out.println("Produto excluido!");
				help = false;
			} catch (Exception e) {
				System.out.println("Entrada Invalida!\n");
				continue;
			} finally {
				leEntrada.nextLine();
			}
		}
	}

	public List<Produto> procura() {
		boolean help = true;
		while (help) {
			try {
				while(id <= 0) {
					System.out.println("Insira o ID do Produto a ser buscado: ");
					id = leEntrada.nextInt();	
				}
				help = false;
			} catch (Exception e) {
				System.out.println("Entrada Invalida!\n");
				continue;
			} finally {
				if (help == false)
					System.out.println("Aperte *Enter*!");
				leEntrada.nextLine();
			}
		}
		alteration = id;
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = control.buscar(id);
		return produtos;
	}

	public void alteracao() {
		leEntrada.useLocale(Locale.ENGLISH);
		Double desconto = 0.0, valor = 0.0;
		boolean help = true;
		while (help) {
			try {
				if (!procura().isEmpty()) {
					leEntrada.nextLine();
					System.out.println("Insira o novo nome do Produto: ");
					nome = leEntrada.nextLine();
					System.out.println("Insira a nova descricao do Produto: ");
					descricao = leEntrada.nextLine();
					while(valor <= 0 || desconto <= 0) {
						System.out.println("Insira o novo valor do Produto: ");
						valor = leEntrada.nextDouble();
						leEntrada.nextLine();
						System.out.println("Insira o novo desconto do Produto: ");
						desconto = leEntrada.nextDouble();
						leEntrada.nextLine();
						if (desconto >= valor)
							System.err.println("Valor e Desconto devem ser valores positivos e o valor tem que ser maior que o desconto!\n");
					}
				} else
					insercao(alteration);
				help = false;
			} catch (Exception e) {
				System.out.println("Entrada Invalida!\n");
				continue;
			} finally {
				if (help == false)
					System.out.println("Aperte *Enter*!");
				leEntrada.nextLine();
			}
		}
		control.alterar(nome, descricao, valor, desconto, id, data());
		System.out.println("Produto atualizado!");
	}

	public void buscaPalavra() {
		List<Produto> buscas = new ArrayList<Produto>();
		System.out.println("Insira o que quer buscar: ");
		String busca = leEntrada.nextLine();
		String[] result = busca.split(" ");
		for (Integer i = 0; i < result.length; i++) {
			String palavra = result[i];
			buscas = control.buscarPalavra(palavra);
			if (!buscas.isEmpty())
				System.out.println(buscas);
			else
				System.out.println("A palavra " + palavra + " nao esta no banco de dados!");
		}
	}

	public String data() {
		Calendar c = Calendar.getInstance();
		Integer ano = c.get(Calendar.YEAR);
		Integer month = c.get(Calendar.MONTH) + 1;
		Integer day = c.get(Calendar.DAY_OF_MONTH);
		return ano + "-" + month + "-" + day;
	}

	public void insercaoExtra() {
		Integer i = 0;
		String[] nomes = { "Leite", "Desodorante", "Amaciante", "Biscoito", "Shampoo" };
		String[] descricoes = { "Longa Vida", "Boa Saude", "Produz Felicidade", "Produz Alegria", "O queridinho" };
		Double[] valores = { 9.99, 19.99, 29.99, 15.99, 12.99 };
		Double[] descontos = { 5.99, 6.99, 7.99, 4.99, 2.99 };
		Random r = new Random();
		while (i < 3) {
			id = r.nextInt(1000);
			Integer randomNumber = r.nextInt(nomes.length);
			nome = nomes[randomNumber];
			randomNumber = r.nextInt(nomes.length);
			descricao = descricoes[randomNumber];
			randomNumber = r.nextInt(nomes.length);
			valor = valores[randomNumber];
			randomNumber = r.nextInt(nomes.length);
			desconto = descontos[randomNumber];
			Produto product = new Produto(id, nome, descricao, valor, desconto, data());
			control.inserir(product);
			i++;
		}
	}
}