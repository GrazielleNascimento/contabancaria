package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
// Criamos uma Collection ArrayList, do tipo Conta (Classe Abstrata), chamada listaContas.
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();//Collection que irá armazenar os dados de todas as Contas, funcionando como um Banco de dados em Memória da nossa aplicação.
	int numero = 0;//Criamos uma variável do tipo int chamada numero, armazenará o numero da última conta que foi criada.
	@Override
	public void procurarPorNumero(int numero) {
		
	}
	@Override
	public void listarTodas() {
		for(var conta : listaContas) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(int numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sacar(int numero, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub
		
	}
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas ) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
}
