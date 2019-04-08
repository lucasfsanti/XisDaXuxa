package model.bo;

import java.util.ArrayList;

import model.dao.PratoDAO;
import model.vo.PratoVO;

public class PratoBO {

	public void cadastrarPratoBO(PratoVO pratoVO) {
		if(pratoVO.getNome() == null || pratoVO.getNome().equals("")) {
			System.out.println("Nome do prato não informado!");
		} else {
			if(pratoVO.getPreco() <= 0) {
				System.out.println("Preço do prato não pode ser menor que R$ 0,00");
			} else {
				PratoDAO pratoDAO = new PratoDAO();
				if(pratoDAO.existeRegistroPorNome(pratoVO.getNome()))  {
					System.out.println("Prato já cadastrado!");
				} else {
					int resultado = pratoDAO.cadastrarPratoDAO(pratoVO);
					if(resultado == 1) {
						System.out.println("\nPrato cadastrado com sucesso!");
					} else {
						System.out.println("\nNão foi possível cadastrar o Prato!");
					}
				}
			}	
		}
	}

	public void excluirPratoBO(PratoVO pratoVO) {
		PratoDAO pratoDAO = new PratoDAO();
		if(pratoDAO.existeRegistroPorIdPrato(pratoVO.getIdPrato())) {
			int resultado = pratoDAO.excluirPratoDAO(pratoVO);
			if(resultado == 1) {
				System.out.println("\nPrato excluído com sucesso!");
			} else {
				System.out.println("\nNão foi possível excluir o Prato!");
			}
		} else {
			System.out.println("Prato não existe na base de dados!");
		}
	}

	public void atualizarPratoBO(PratoVO pratoVO) {
		if(pratoVO.getIdPrato() == 0) {
			System.out.println("\nCódigo do Prato não informado!");
		} else {
			if(pratoVO.getNome() == null || pratoVO.getNome().equals("")) {
				System.out.println("Nome do prato não informado!");
			} else {
				if(pratoVO.getPreco() <= 0) {
					System.out.println("Preço do prato não pode ser menor que R$ 0,00");
				} else {
					PratoDAO pratoDAO = new PratoDAO();
					if(pratoDAO.existeRegistroPorIdPrato(pratoVO.getIdPrato())) {
						int resultado = pratoDAO.atualizarPratoDAO(pratoVO);
						if(resultado == 1) {
							System.out.println("Prato atualizado com sucesso!");
						} else {
							System.out.println("Não foi possível atualizar Prato!");
						}
					} else {
						System.out.println("Prato não existe na base de dados!");
					}
				}
			}
		}
	}

	public ArrayList<PratoVO> consultarTodosPratosBO() {
		PratoDAO pratoDAO = new PratoDAO();
		ArrayList<PratoVO> listaPratosVO = pratoDAO.consultarTodosPratosDAO();
		if(listaPratosVO.isEmpty()) {
			System.out.println("Lista de Pratos está vazia!");
		}
		return listaPratosVO;
	}

	public PratoVO consultarPratoBO(PratoVO pratoVO) {
		PratoVO prato = null;
		if(pratoVO.getIdPrato() == 0) {
			System.out.println("\nCódigo do Prato não informado!");
		} else {
			PratoDAO pratoDAO = new PratoDAO();
			if(pratoDAO.existeRegistroPorIdPrato(pratoVO.getIdPrato())) {
				prato = pratoDAO.consultarPratoDAO(pratoVO);
				if(prato == null) {
					System.out.println("Prato não localizado!");
				}
			} else {
				System.out.println("Prato não existe na base de dados!");
			}
		}
		return prato;
	}
	
	

}
