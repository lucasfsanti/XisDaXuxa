package controler;

import java.util.ArrayList;

import model.bo.PratoBO;
import model.vo.PratoVO;

public class ControladoraPrato {	
	
	public void cadastrarPratoController(PratoVO pratoVO) {
		PratoBO pratoBO = new PratoBO();
		pratoBO.cadastrarPratoBO(pratoVO);
	}

	public void excluirPratoController(PratoVO pratoVO) {
		PratoBO pratoBO = new PratoBO();
		pratoBO.excluirPratoBO(pratoVO);
	}

	public void atualizarPratoController(PratoVO pratoVO) {
		PratoBO pratoBO = new PratoBO();
		pratoBO.atualizarPratoBO(pratoVO);
	}

	public ArrayList<PratoVO> consultarTodosPratosController() {
		PratoBO pratoBO = new PratoBO();
		return pratoBO.consultarTodosPratosBO();
	}

	public PratoVO consultarPratoController(PratoVO pratoVO) {
		PratoBO pratoBO = new PratoBO();
		return pratoBO.consultarPratoBO(pratoVO);
	}

}
