package view;

import java.util.Scanner;

public class Menu {

	Scanner teclado = new Scanner(System.in);
	
	public void apresentarMenu() {

		System.out.println("Food Truck - Xis da Xuxa");
		System.out.println("\nOpções:");
		System.out.println("\n1 - Cadastro");
		System.out.println("2 - Venda");
		System.out.println("3 - Relatórios");
		System.out.println("0 - Sair");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());
		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				MenuCadastro menuCadastro = new MenuCadastro();
				menuCadastro.apresentarMenuCadastro();
				break;
			}
			case 2: {
				MenuVenda menuVenda = new MenuVenda();
				menuVenda.apresentarMenuVenda();
				break;
			}
			case 3: {
				MenuRelatorios menuRelatorios = new MenuRelatorios();
				menuRelatorios.apresentarMenuRelatorios();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\nFood Truck - Xis da Xuxa");
			System.out.println("\nOpções:");
			System.out.println("\n1 - Cadastro");
			System.out.println("2 - Venda");
			System.out.println("3 - Relatórios");
			System.out.println("0 - Sair");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
		
	}

}
