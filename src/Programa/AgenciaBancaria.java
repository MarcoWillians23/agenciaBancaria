package Programa;

import java.util.ArrayList;
import java.util.Scanner;

import entities.ContaBancaria;
import entities.Pessoa;

public class AgenciaBancaria {

	static Scanner input = new Scanner(System.in);
	static ArrayList<ContaBancaria> contasBancarias;

	public static void main(String[] args) {
		contasBancarias = new ArrayList<ContaBancaria>();
		operacoes();
	}

	public static void operacoes() {

		System.out.println("------------------------------------------------------");
		System.out.println("-------------Bem vindos a nossa Ag�ncia---------------");
		System.out.println("------------------------------------------------------");
		System.out.println("***** Selecione uma opera��o que deseja realizar *****");
		System.out.println("------------------------------------------------------");
		System.out.println("|   Op��o 1 - Criar conta   |");
		System.out.println("|   Op��o 2 - Depositar     |");
		System.out.println("|   Op��o 3 - Sacar         |");
		System.out.println("|   Op��o 4 - Transferir    |");
		System.out.println("|   Op��o 5 - Listar        |");
		System.out.println("|   Op��o 6 - Sair          |");

		int operacao = input.nextInt();

		switch (operacao) {
		case 1:
			criarConta();
			break;
		case 2:
			depositar();
			break;
		case 3:
			sacar();
			break;
		case 4:
			transferir();
			break;
		case 5:
			listarContas();
			break;
		case 6:
			System.out.println("flw � nois");
			System.exit(0);
			break;

		default:
			System.out.println("Opc�o inv�lida! ");
			operacoes();
			break;
		}
	}

	public static void criarConta() {
		input.nextLine();
		System.out.print("Nome: ");
		String nome = input.nextLine();
		System.out.print("CPF: ");
		String cpf = input.nextLine();
		System.out.print("Email: ");
		String email = input.next();

		Pessoa pessoa = new Pessoa(nome, cpf, email);

		ContaBancaria conta = new ContaBancaria(pessoa);

		contasBancarias.add(conta);
		System.out.println("Sua conta foi criada com sucesso! ");

		operacoes();
	}

	private static ContaBancaria encontrarConta(int numeroConta) {
		ContaBancaria conta = null;
		if (contasBancarias.size() > 0) {
			for (ContaBancaria c : contasBancarias) {
				if (c.getNumeroConta() == numeroConta) {
					conta = c;
				}
			}
		}
		return conta;
	}

	public static void depositar() {
		System.out.println("N�mero da conta: ");
		int numeroConta = input.nextInt();

		ContaBancaria conta = encontrarConta(numeroConta);

		if (conta != null) {
			System.out.println("Qual o valor deseja depositar? ");
			Double valorDeposito = input.nextDouble();
			conta.depositar(valorDeposito);
			System.out.println("Valor depositado com sucesso! ");
		} else {
			System.out.println("Conta n�o encontrada! ");
		}
		operacoes();
	}

	public static void sacar() {
		System.out.println("N�mero da conta: ");
		int numeroConta = input.nextInt();

		ContaBancaria conta = encontrarConta(numeroConta);

		if (conta != null) {
			System.out.println("Qual valor deseja sacar? ");
			Double valorSaque = input.nextDouble();
			conta.sacar(valorSaque);
		} else {
			System.out.println("Conta n�o encontrada! ");
		}
		operacoes();
	}

	public static void transferir() {
		System.out.println("N�mero da conta do remetente: ");
		int remetente = input.nextInt();

		ContaBancaria contaRemetente = encontrarConta(remetente);

		if (contaRemetente != null) {
			System.out.println("N�mero da conta do destinat�rio: ");
			int destinatario = input.nextInt();

			ContaBancaria contaDestinatario = encontrarConta(destinatario);

			if (contaDestinatario != null) {
				System.out.println("Valor da tranfer�ncia: ");
				Double valor = input.nextDouble();

				contaRemetente.transferir(contaDestinatario, valor);
			}
		}
		operacoes();
	}

	public static void listarContas() {
		if (contasBancarias.size() > 0) {
			for (ContaBancaria conta : contasBancarias) {
				System.out.println(conta);
			}
		} else {
			System.out.println("N�o h� contas cadastradas! ");
		}
		operacoes();
	}

}
