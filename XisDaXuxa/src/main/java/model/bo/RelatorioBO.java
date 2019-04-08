package model.bo;

import java.util.ArrayList;

import model.dao.BebidaDAO;
import model.dao.RelatorioDAO;
import model.dao.VendaDAO;
import model.dto.ProdutoDTO;
import model.vo.BebidaVO;
import model.vo.VendaVO;

public class RelatorioBO {

	public ArrayList<ProdutoDTO> consultarTodosProdutosBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<ProdutoDTO> listaProdutosDTO = relatorioDAO.consultarTodosProdutosDAO();
		if(listaProdutosDTO.isEmpty()) {
			System.out.println("\nLista de Produtos está vazia!");
		}
		return listaProdutosDTO;
	}

	public ArrayList<VendaVO> consultarTodasVendasCanceladasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<VendaVO> listaVendasVO = relatorioDAO.consultarTodasVendasCanceladasDAO();
		if(listaVendasVO.isEmpty()) { 
			System.out.println("\nLista de Vendas Canceladas está vazia!");
		}
		return listaVendasVO;
	}

	public ArrayList<VendaVO> consultarTodasVendasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<VendaVO> listaVendasVO = relatorioDAO.consultarTodasVendasDAO();
		if(listaVendasVO.isEmpty()) { 
			System.out.println("\nLista de Vendas está vazia!");
		}
		return listaVendasVO;
	}

	public VendaVO consultarVendaPorIdVendaBO(VendaVO vendaVO) {
		VendaVO venda = null;
		if(vendaVO.getIdVenda() == 0) {
			System.out.println("\nCódigo da Venda não informado!");
		} else {
			VendaDAO vendaDAO = new VendaDAO();
			if(vendaDAO.existeRegistroPorIdVenda(vendaVO.getIdVenda())) {
				RelatorioDAO relatorioDAO = new RelatorioDAO();
				venda = relatorioDAO.consultarVendaPorIdVendaDAO(vendaVO);
				if(venda == null) {
					System.out.println("\nVenda não localizada!");
				}
			} else {
				System.out.println("\nVenda não existe na base de dados!");
			}
		}
		return venda;
	}

}
