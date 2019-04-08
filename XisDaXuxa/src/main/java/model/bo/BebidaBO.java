package model.bo;

import java.util.ArrayList;

import model.dao.BebidaDAO;
import model.vo.BebidaVO;

public class BebidaBO {

	public void cadastrarBebidaBO(BebidaVO bebidaVO) {
		if(bebidaVO.getNome() == null || bebidaVO.getNome().equals("")) {
			System.out.println("Nome da bebida não informado!");
		} else {
			if(bebidaVO.getPreco() <= 0) {
				System.out.println("Preço da bebida não pode ser menor que R$ 0,00");
			} else {
				BebidaDAO bebidaDAO = new BebidaDAO();
				if(bebidaDAO.existeRegistroPorNome(bebidaVO.getNome()))  {
					System.out.println("Bebida já cadastrada!");
				} else {
					int resultado = bebidaDAO.cadastrarBebidaDAO(bebidaVO);
					if(resultado == 1) {
						System.out.println("\nBebida cadastrada com sucesso!");
					} else {
						System.out.println("\nNão foi possível cadastrar a Bebida!");
					}
				}
			}	
		}		
	}

	public void excluirBebidaBO(BebidaVO bebidaVO) {
		BebidaDAO bebidaDAO = new BebidaDAO();
		if(bebidaDAO.existeRegistroPorIdBebida(bebidaVO.getIdBebida())) {
			int resultado = bebidaDAO.excluirBebidaDAO(bebidaVO);
			if(resultado == 1) {
				System.out.println("\nBebida excluída com sucesso!");
			} else {
				System.out.println("\nNão foi possível excluir a Bebida!");
			}
		} else {
			System.out.println("Bebida não existe na base de dados!");
		}
	}

	public void atualizarBebidaBO(BebidaVO bebidaVO) {
		if(bebidaVO.getIdBebida() == 0) {
			System.out.println("\nCódigo da Bebida não informado!");
		} else {
			if(bebidaVO.getNome() == null || bebidaVO.getNome().equals("")) {
				System.out.println("Nome da Bebida não informado!");
			} else {
				if(bebidaVO.getPreco() <= 0) {
					System.out.println("Preço da Bebida não pode ser menor que R$ 0,00");
				} else {
					BebidaDAO bebidaDAO = new BebidaDAO();
					if(bebidaDAO.existeRegistroPorIdBebida(bebidaVO.getIdBebida())) {
						int resultado = bebidaDAO.atualizarBebidaDAO(bebidaVO);
						if(resultado == 1) {
							System.out.println("Bebida atualizada com sucesso!");
						} else {
							System.out.println("Não foi possível atualizar Bebida!");
						}
					} else {
						System.out.println("Bebida não existe na base de dados!");
					}
				}
			}
		}
	}

	public ArrayList<BebidaVO> consultarTodasBebidasBO() {
		BebidaDAO bebidaDAO = new BebidaDAO();
		ArrayList<BebidaVO> listaBebidasVO = bebidaDAO.consultarTodasBebidasDAO();
		if(listaBebidasVO.isEmpty()) {
			System.out.println("Lista de Bebidas está vazia!");
		}
		return listaBebidasVO;
	}

	public BebidaVO consultarBebidaBO(BebidaVO bebidaVO) {
		BebidaVO bebida = null;
		if(bebidaVO.getIdBebida() == 0) {
			System.out.println("\nCódigo da Bebida não informado!");
		} else {
			BebidaDAO bebidaDAO = new BebidaDAO();
			if(bebidaDAO.existeRegistroPorIdBebida(bebidaVO.getIdBebida())) {
				bebida = bebidaDAO.consultarBebidaDAO(bebidaVO);
				if(bebida == null) {
					System.out.println("\nBebida não localizada!");
				}
			} else {
				System.out.println("\nBebida não existe na base de dados!");
			}
		}
		return bebida;
	}

}
