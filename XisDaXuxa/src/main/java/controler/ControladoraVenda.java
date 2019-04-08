package controler;

import model.bo.VendaBO;
import model.vo.VendaVO;

public class ControladoraVenda {

	public void cadastrarVendaController(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		vendaBO.cadastrarVendaBO(vendaVO);
	}

	public void cancelarVendaController(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		vendaBO.cancelarVendaBO(vendaVO);
	}

}
