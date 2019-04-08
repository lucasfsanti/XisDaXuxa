package view;

import java.util.ArrayList;
import java.util.Scanner;

import controler.ControladoraBebida;
import model.vo.BebidaVO;

public class MenuBebidas {
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuBebidas() {
		System.out.println("\n\nFood Truck - Xis da Xuxa \n----- Menu Cadastro de Bebidas -----");
		System.out.println("\nOpções:");
		System.out.println("\n1 - Cadastrar Bebida");
		System.out.println("2 - Consultar Bebida");
		System.out.println("3 - Atualizar Bebida");
		System.out.println("4 - Excluir Bebida");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());

		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				this.cadastrarBebida();
				break;
			}
			case 2: {
				this.consultarBebida();
				break;
			}
			case 3: {
				this.atualizarBebida();
				break;
			}
			case 4: {
				this.excluirBebida();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\n\nFood Truck - Xis da Xuxa \n----- Menu Cadastro de Bebidas -----");
			System.out.println("\nOpções:");
			System.out.println("\n1 - Cadastrar Bebida");
			System.out.println("2 - Consultar Bebida");
			System.out.println("3 - Atualizar Bebida");
			System.out.println("4 - Excluir Bebida");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}
	
	private void excluirBebida() {
		BebidaVO bebidaVO = new BebidaVO();
		System.out.print("\nInforme o Código da Bebida: ");
		bebidaVO.setIdBebida(Integer.parseInt(teclado.nextLine()));
		
		ControladoraBebida controladoraBebida = new ControladoraBebida();
		controladoraBebida.excluirBebidaController(bebidaVO);
	}

	private void atualizarBebida() {
		BebidaVO bebidaVO = new BebidaVO();
		System.out.print("\nDigite o código da bebida: ");
		bebidaVO.setIdBebida(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nDigite o nome da bebida: ");
		bebidaVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o preço da bebida: ");
		bebidaVO.setPreco(Double.parseDouble(teclado.nextLine()));
		
		ControladoraBebida controladoraBebida = new ControladoraBebida();
		controladoraBebida.atualizarBebidaController(bebidaVO);
	}

	private void consultarBebida() {
		System.out.println("\nInforme o tipo de consulta a ser realizada");
		System.out.println("\n1 - Consultar todas as Bebidas");
		System.out.println("2 - Consultar uma Bebida específica");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());
		
		ControladoraBebida controladoraBebida = new ControladoraBebida();
		
		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				ArrayList<BebidaVO> listaBebidasVO = controladoraBebida.consultarTodasBebidasController();
				System.out.println("\n-------- RESULTADO DA CONSULTA --------");
				System.out.printf("\n%3s   %-20s   %-10s   \n", "ID", "NOME", "PREÇO");
				for(int i = 0; i < listaBebidasVO.size(); i++) {
					listaBebidasVO.get(i).imprimir();
				}
				System.out.println();
				break;
			}
			case 2:{ 
				BebidaVO bebidaVO = new BebidaVO();
				System.out.print("\nInforme o código da Bebida: ");
				bebidaVO.setIdBebida(Integer.parseInt(teclado.nextLine()));
				
				BebidaVO bebida = controladoraBebida.consultarBebidaController(bebidaVO);
				System.out.println("\n-------- RESULTADO DA CONSULTA --------");
				System.out.printf("\n%3s   %-20s   %-10s   \n", "ID", "NOME", "PREÇO");
				bebida.imprimir();
				break;
			}
			default: 
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			System.out.println("\nInforme o tipo de consulta a ser realizada");
			System.out.println("\n1 - Consultar todas as Bebidas");
			System.out.println("2 - Consultar uma Bebida específica");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}

	private void cadastrarBebida() {
		BebidaVO bebidaVO = new BebidaVO();
		System.out.print("\nDigite o nome da bebida: ");
		bebidaVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o preço da bebida: ");
		bebidaVO.setPreco(Double.parseDouble(teclado.nextLine()));
		
		ControladoraBebida controladoraBebida = new ControladoraBebida();
		controladoraBebida.cadastrarBebidaController(bebidaVO);
	}

}

