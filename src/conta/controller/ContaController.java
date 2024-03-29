package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	/**
	 * Collection listaContas contendo Objetos do tipo Conta
	 */

// Criamos uma Collection ArrayList, do tipo Conta (Classe Abstrata), chamada listaContas.
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();// Collection que irá armazenar os dados de todas as
																	// Contas, funcionando como um Banco de dados em
																	// Memória da nossa aplicação.
	int numero = 0;// Criamos uma variável do tipo int chamada numero, armazenará o numero da
					// última conta que foi criada.

	/**
	 * Procurar Conta por numero
	 */
	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\n A conta número: " + numero + " não foi encontrada!");
	}

	/**
	 * Método Listar todas as Contas
	 */
	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA Conta número: " + conta.getNumero() + " foi criada com sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("A Conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");
		} else
			System.out.println("A Conta número: " + conta.getNumero() + " não foi encontrada!");
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("A Conta numero: " + numero + " foi deletada com sucesso!");
		} else
			System.out.println("A Conta número: " + numero + " não foi encontrada!");

	}

//Antes de Efetuar o saque na conta, precisamos verificar se a conta existe. Observe que foi criada a variável local buscaConta, para receber o Objeto da Classe Conta, que foi encontrado na Collection listaContas.
//Objeto conta foi criado através da palavra reservada var. Por inferência, a palavra reservada var entende que a variável conta, deve ser um Objeto da Classe Conta, porque receberá o retorno do Método buscarNaCollection.
	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {

			if (conta.sacar(valor) == true)
				System.out.println("\nO Saque na Conta numero: " + numero + " foi efetuado com sucesso!");

		} else
			System.out.println("\nA Conta numero: " + numero + " não foi encontrada!");

	}

// Antes de Efetuar o depósito na conta, precisamos verificar se a conta existe. Observe que foi criada a variável local conta, para receber o Objeto da Classe Conta, que foi encontrado na Collection listaContas. Esse Objeto foi criado através da palavra reservada var. Por inferência, a palavra reservada var entende que a variável conta, deve ser um Objeto da Classe Conta, porque receberá o retorno do Método buscarNaCollection.
	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO Deposito na Conta numero: " + numero + " foi efetuado com sucesso!");
		} else
			System.out.println(
					"\nA Conta número " + numero + " não foi encontrada ou a Conta destino não é uma Conta Corrente!");
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
/*
 * numeroOrigem: Número da conta que será efetuada a operação de Saque.
numeroDestino: Número da conta que será efetuada a operação de Depósito, ou seja, receberá a Transferência.
valor: O valor que será debitado da conta de origem e será creditado na conta de destino.
 */
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);

		if (contaOrigem != null && contaDestino != null) {

			if (contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA Transferência foi efetuado com sucesso!");
			}
		} else
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
	}

	/**
	 * Métodos Auxiliares
	 **/

	/**
	 * Método para gerar automaticamente o Número da Conta
	 */
	public int gerarNumero() {

		return ++numero;
	}

	/**
	 * Método para buscar a Conta na Collection
	 */

	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}

	/**
	 * Método para retornar o Tipo da Conta
	 */
	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}

		return 0;
	}
}
