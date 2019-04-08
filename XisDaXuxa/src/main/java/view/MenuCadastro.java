package view;

import java.util.Scanner;

public class MenuCadastro {
	
	Scanner teclado = new Scanner(System.in);
	
	public void apresentarMenuCadastro() {
		System.out.println("\nFood Truck - Xis da Xuxa \n----- Menu Cadastro -----");
		System.out.println("\nOpções:");
		System.out.println("\n1 - Cadastrar Pratos");
		System.out.println("2 - Cadastrar Bebidas");
		System.out.println("0 - Voltar");
		System.out.print("\nDigite a Opção: ");
		int opcao = Integer.parseInt(teclado.nextLine());
		
		while(opcao != 0) {
			switch(opcao) {
			case 1: {
				MenuPratos menuPratos = new MenuPratos();
				menuPratos.apresentarMenuPratos();
				break;
			}
			case 2: {
				MenuBebidas menuBebidas = new MenuBebidas();
				menuBebidas.apresentarMenuBebidas();
				break;
			}
			default: {
				System.out.print("\nOpção Inválida. Digite novamente: ");
				break;
			}
			}
			System.out.println("\nFood Truck - Xis da Xuxa \n------ Menu Cadastro ------");
			System.out.println("\nOpções:");
			System.out.println("\n1 - Cadastrar Pratos");
			System.out.println("2 - Cadastrar Bebidas");
			System.out.println("0 - Voltar");
			System.out.print("\nDigite a Opção: ");
			opcao = Integer.parseInt(teclado.nextLine());
		}
	}

}
