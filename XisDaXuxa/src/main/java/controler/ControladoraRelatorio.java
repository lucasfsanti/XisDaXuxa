package controler;

import java.util.ArrayList;

import model.bo.RelatorioBO;
import model.dto.ProdutoDTO;
import model.vo.VendaVO;


public class ControladoraRelatorio {

	public ArrayList<ProdutoDTO> consultarTodosProdutosController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.consultarTodosProdutosBO();
	}

	public ArrayList<VendaVO> consultarTodasVendasCanceladasController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.consultarTodasVendasCanceladasBO();
	}

	public ArrayList<VendaVO> consultarTodasVendasController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.consultarTodasVendasBO();
	}

	public VendaVO consultarVendaPorIdVendaController(VendaVO vendaVO) {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.consultarVendaPorIdVendaBO(vendaVO);
	}

}