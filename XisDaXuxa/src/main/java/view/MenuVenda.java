package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import controler.ControladoraVenda;
import model.vo.ItemBebidaVO;
import model.vo.ItemPratoVO;
import model.vo.VendaVO;

public class MenuVenda {
	
	Scanner teclado = new Scanner(System.in);
	private static int senha = 0;

	public void apresentarMenuVenda() {

		System.out.println("\n\nFood Truck - Xis da Xuxa");
		System.out.println("\nOpções:");
		System.out.println("\n1 - Cadastrar Venda");
		System.out.println("2 - Cancelar Venda");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());
		
		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				this.cadastrarVenda();
				break;
			}
			case 2: {
				this.cancelarVenda();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\n\nFood Truck - Xis da Xuxa");
			System.out.println("\nOpções:");
			System.out.println("\n1 - Cadastrar Venda");
			System.out.println("2 - Cancelar Venda");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}
	
	private void cancelarVenda() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nInforme o código da Venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
		
		ControladoraVenda controladoraVenda = new ControladoraVenda();
		controladoraVenda.cancelarVendaController(vendaVO);
	}
	
	private void cadastrarVenda() {
		VendaVO vendaVO = new VendaVO();
		vendaVO.setItensPratos(this.pedidoPratos());
		vendaVO.setItensBebidas(this.pedidoBebidas());
		vendaVO.setDataVenda(new Date());
		vendaVO.setSenhaPedido(this.getSenha());
		vendaVO.setFlagVendaCancelada(false);
		
		ControladoraVenda controladoraVenda = new ControladoraVenda();
		controladoraVenda.cadastrarVendaController(vendaVO);
	}

	private int getSenha() {
		if(senha  == 99) {
			senha = 0;
			return senha;
		} else {
			return senha++;
		}
	}

	private ArrayList<ItemBebidaVO> pedidoBebidas() {
		ArrayList<ItemBebidaVO> bebidas = new ArrayList<ItemBebidaVO>();
		System.out.println("\n\nFood Truck - Xis da Xuxa \n ----- Pedido de Bebida -----");
		System.out.println("\nOpções:");
		System.out.println("1 - Incluir Bebida");
		System.out.println("2 - Finalizar Pedido Bebida");
		System.out.print("\nDigite a Opção:");
		int opcao = Integer.parseInt(teclado.nextLine());
		while(opcao != 2) {
			switch(opcao) {
			case 1: {
				bebidas = this.incluirBebidas();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\n\nFood Truck - Xis da Xuxa \n ----- Pedido de Bebida -----");
			System.out.println("\nOpções:");
			System.out.println("1 - Incluir Bebida");
			System.out.println("2 - Finalizar Pedido Bebida");
			System.out.print("\nDigite a Opção:");
			opcao = Integer.parseInt(teclado.nextLine());
		}
		return bebidas;
	}

	private ArrayList<ItemBebidaVO> incluirBebidas() {
		ArrayList<ItemBebidaVO> itemBebidasVO = new ArrayList<ItemBebidaVO>();
		String continuar = "S";
		while(continuar.equalsIgnoreCase("S")) {
			ItemBebidaVO itemBebidaVO = new ItemBebidaVO();
			System.out.print("\nDigite o Código da Bebida: ");
			itemBebidaVO.setIdBebida(Integer.parseInt(teclado.nextLine()));
			System.out.print("\nInforme a Quantidade: ");
			itemBebidaVO.setQuantidade(Integer.parseInt(teclado.nextLine()));
			itemBebidasVO.add(itemBebidaVO);
			System.out.print("\nDeseja Incluir mais alguma Bebida [S ou N]? ");
			continuar = teclado.nextLine();
		}
		return itemBebidasVO;
	}

	private ArrayList<ItemPratoVO> pedidoPratos() {
		ArrayList<ItemPratoVO> pratos = new ArrayList<ItemPratoVO>();
		System.out.println("\n\nFood Truck - Xis da Xuxa \n ----- Pedido de Prato -----");
		System.out.println("\nOpções:");
		System.out.println("1 - Incluir Prato");
		System.out.println("2 - Finalizar Pedido Prato");
		System.out.print("\nDigite a Opção:");
		int opcao = Integer.parseInt(teclado.nextLine());
		while(opcao != 2) {
			switch(opcao) {
			case 1: {
				pratos = this.incluirPratos();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\n\nFood Truck - Xis da Xuxa \n ----- Pedido de Prato -----");
			System.out.println("\nOpções:");
			System.out.println("1 - Incluir Prato");
			System.out.println("2 - Finalizar Pedido Prato");
			System.out.print("\nDigite a Opção:");
			opcao = Integer.parseInt(teclado.nextLine());
		}
		return pratos;
	}

	private ArrayList<ItemPratoVO> incluirPratos() {
		ArrayList<ItemPratoVO> itemPratosVO = new ArrayList<ItemPratoVO>();
		String continuar = "S";
		while(continuar.equalsIgnoreCase("S")) {
			ItemPratoVO itemPratoVO = new ItemPratoVO();
			System.out.print("\nDigite o Código do Prato: ");
			itemPratoVO.setIdPrato(Integer.parseInt(teclado.nextLine()));
			System.out.print("\nInforma a Quantidade: ");
			itemPratoVO.setQuantidade(Integer.parseInt(teclado.nextLine()));
			itemPratosVO.add(itemPratoVO);
			System.out.print("\nDeseja Incluir mais algum Prato [S ou N]? ");
			continuar = teclado.nextLine();
		}
		return itemPratosVO;
	}

}
