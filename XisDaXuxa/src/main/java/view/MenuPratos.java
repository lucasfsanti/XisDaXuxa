package view;

import java.util.ArrayList;
import java.util.Scanner;

import controler.ControladoraPrato;
import model.vo.PratoVO;

public class MenuPratos {

	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuPratos() {
		System.out.println("\n\nFood Truck - Xis da Xuxa \n----- Menu Cadastro de Pratos -----");
		System.out.println("\nOpções:");
		System.out.println("\n1 - Cadastrar Prato");
		System.out.println("2 - Consultar Prato");
		System.out.println("3 - Atualizar Prato");
		System.out.println("4 - Excluir Prato");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());

		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				this.cadastrarPrato();
				break;
			}
			case 2: {
				this.consultarPrato();
				break;
			}
			case 3: {
				this.atualizarPrato();
				break;
			}
			case 4: {
				this.excluirPrato();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\n\nFood Truck - Xis da Xuxa \n----- Menu Cadastro de Pratos -----");
			System.out.println("\nOpções:");
			System.out.println("\n1 - Cadastrar Prato");
			System.out.println("2 - Consultar Prato");
			System.out.println("3 - Atualizar Prato");
			System.out.println("4 - Excluir Prato");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}
	
	private void excluirPrato() {
		PratoVO pratoVO = new PratoVO();
		System.out.print("\nInforme o Código do Prato: ");
		pratoVO.setIdPrato(Integer.parseInt(teclado.nextLine()));
		
		ControladoraPrato controladoraPrato = new ControladoraPrato();
		controladoraPrato.excluirPratoController(pratoVO);
	}

	private void atualizarPrato() {
		PratoVO pratoVO = new PratoVO();
		System.out.print("\nDigite o código do prato: ");
		pratoVO.setIdPrato(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nDigite o nome do prato: ");
		pratoVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o preço do prato: ");
		pratoVO.setPreco(Double.parseDouble(teclado.nextLine()));
		
		ControladoraPrato controladoraPrato = new ControladoraPrato();
		controladoraPrato.atualizarPratoController(pratoVO);
	}

	private void consultarPrato() {
		System.out.println("\nInforme o tipo de consulta a ser realizada");
		System.out.println("\n1 - Consultar todos os Pratos");
		System.out.println("2 - Consultar um Prato Específico");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());
		
		ControladoraPrato controladoraPrato = new ControladoraPrato();
		
		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				ArrayList<PratoVO> listaPratosVO = controladoraPrato.consultarTodosPratosController();
				System.out.println("\n-------- RESULTADO DA CONSULTA --------");
				System.out.printf("\n%3s   %-20s   %-10s   \n", "ID", "NOME", "PREÇO");
				for(int i = 0; i < listaPratosVO.size(); i++) {
					listaPratosVO.get(i).imprimir();
				}
				System.out.println();
				break;
			}
			case 2:{ 
				PratoVO pratoVO = new PratoVO();
				System.out.print("\nInforme o código do Prato: ");
				pratoVO.setIdPrato(Integer.parseInt(teclado.nextLine()));
				
				PratoVO prato = controladoraPrato.consultarPratoController(pratoVO);
				System.out.println("\n-------- RESULTADO DA CONSULTA --------");
				System.out.printf("\n%3s   %-20s   %-10s   \n", "ID", "NOME", "PREÇO");
				prato.imprimir();
				break;
			}
			default: 
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			System.out.println("\nInforme o tipo de consulta a ser realizada");
			System.out.println("\n1 - Consultar todos os Pratos");
			System.out.println("2 - Consultar um Prato Específico");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}

	private void cadastrarPrato() {
		PratoVO pratoVO = new PratoVO();
		System.out.print("\nDigite o nome do prato: ");
		pratoVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o preço do prato: ");
		pratoVO.setPreco(Double.parseDouble(teclado.nextLine()));
		
		ControladoraPrato controladoraPrato = new ControladoraPrato();
		controladoraPrato.cadastrarPratoController(pratoVO);
	}

}
