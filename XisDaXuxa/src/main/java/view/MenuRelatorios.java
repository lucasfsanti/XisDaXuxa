package view;

import java.util.ArrayList;
import java.util.Scanner;

import controler.ControladoraBebida;
import controler.ControladoraPrato;
import controler.ControladoraRelatorio;
import model.dto.ProdutoDTO;
import model.vo.BebidaVO;
import model.vo.PratoVO;
import model.vo.VendaVO;

public class MenuRelatorios {
	
	Scanner teclado = new Scanner(System.in);
	
	public void apresentarMenuRelatorios() {
		System.out.println("\n\nFood Truck - Xis da Xuxa \n----- Menu Cadastro de Relatórios -----");
		System.out.println("\nOpções");
		System.out.println("\n1 - Relatório de Produtos");
		System.out.println("2 - Relatório Contábil de Todas as Vendas Canceladas");
		System.out.println("3 - Relatório Contábil de Todas as Vendas");
		System.out.println("4 - Relatório de uma Venda por Código");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());
		
		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				this.relatorioProdutos();
				break;
			}
			case 2: {
				this.relatorioTodasVendasCanceladas();
				break;
			}
			case 3: {
				this.relatorioTodasVendas();
				break;
			}
			case 4: {
				this.relatorioVendaPorCodigo();
				break;
			}
			default : {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\n\nFood Truck - Xis da Xuxa \n----- Menu Cadastro de Relatórios -----");
			System.out.println("\nOpções");
			System.out.println("\n1 - Relatório de Produtos");
			System.out.println("2 - Relatório Contábil de Todas as Vendas Canceladas");
			System.out.println("3 - Relatório Contábil de Todas as Vendas");
			System.out.println("4 - Relatório de uma Venda por Código");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}
	
	private void relatorioVendaPorCodigo() {
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nInforme o Código da Venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
		VendaVO venda = controladoraRelatorio.consultarVendaPorIdVendaController(vendaVO);
		System.out.println("\n-------- RESULTADO DA CONSULTA --------");
		venda.imprimir();
		System.out.println("\n---------------------------------------------------------------------------------");
	}

	private void relatorioTodasVendas() {
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<VendaVO> listaVendasVO = controladoraRelatorio.consultarTodasVendasController();
		System.out.println("\n-------- RESULTADO DA CONSULTA --------");
		for(int i = 0; i < listaVendasVO.size(); i++) {
			listaVendasVO.get(i).imprimir();
			System.out.println("\n---------------------------------------------------------------------------------");
		}
	}

	private void relatorioTodasVendasCanceladas() {
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<VendaVO> listaVendasVO = controladoraRelatorio.consultarTodasVendasCanceladasController();
		System.out.println("\n-------- RESULTADO DA CONSULTA --------");
		for(int i = 0; i < listaVendasVO.size(); i++) {
			listaVendasVO.get(i).imprimir();
			System.out.println("\n---------------------------------------------------------------------------------");
		}
	}

	private void relatorioProdutos() {
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<ProdutoDTO> listaProdutosDTO = controladoraRelatorio.consultarTodosProdutosController();
		System.out.println("\n-------- RESULTADO DA CONSULTA --------");
		System.out.printf("\n%3s   %-20s   %-10s   \n", "ID", "NOME", "PREÇO");
		for(int i = 0; i < listaProdutosDTO.size(); i++) {
			listaProdutosDTO.get(i).imprimir();
		}
		System.out.println("\n---------------------------------------");
		System.out.println("Total de Produtos     " + listaProdutosDTO.size());
		System.out.println();
	}

}
