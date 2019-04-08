package model.bo;

import model.dao.VendaDAO;
import model.vo.VendaVO;

public class VendaBO {

	public void cadastrarVendaBO(VendaVO vendaVO) {
		
		VendaDAO vendaDAO = new VendaDAO();
		if(vendaDAO.existeRegistroVenda(vendaVO)) {
			System.out.println("\nVenda já cadastrada!");
		} else {
			int resultado = vendaDAO.cadastrarVendaDAO(vendaVO);
			if(resultado != 0) {
				vendaVO.setIdVenda(resultado);
				boolean resultadoPrato = vendaDAO.cadastrarItemPratoDAO(vendaVO);
				if(resultadoPrato) {
					boolean resultadoBebida = vendaDAO.cadastrarItemBebidaDAO(vendaVO);
					if(resultadoBebida) {
						System.out.println("\nVenda Cadastrada com Sucesso!");
					} else {
						System.out.println("\nNão foi possível incluir o pedido das bebidas!");
					}
				} else {
					System.out.println("\nNão foi possível incluir o pedido dos pratos!");
				}
			} else {
				System.out.println("\nNão foi possível cadastrar a Venda!");
			}
		}
		
	}

	public void cancelarVendaBO(VendaVO vendaVO) {
		VendaDAO vendaDAO = new VendaDAO();
		if(!vendaDAO.existeRegistroPorIdVenda(vendaVO.getIdVenda())) {
			System.out.println("\nVenda não existe na base de dados!");
		} else if(!vendaDAO.vendaEstaCancelada(vendaVO)){
			int resultado = vendaDAO.cancelarVendaDAO(vendaVO);
			if(resultado == 1) {
				System.out.println("\nVenda Cancelada com Sucesso!");
			} else {
				System.out.println("\nNão foi possível cancelar Venda!");
			}
		} else {
			System.out.println("\nVenda já está cancelada!");
		}
	}

}
