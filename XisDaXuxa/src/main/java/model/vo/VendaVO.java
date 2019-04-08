package model.vo;

import java.util.ArrayList;
import java.util.Date;

import controler.ControladoraBebida;
import controler.ControladoraPrato;
import model.dao.BebidaDAO;

public class VendaVO {
	
	private int idVenda;
	private ArrayList<ItemPratoVO> itensPratos;
	private ArrayList<ItemBebidaVO> itensBebidas;
	private Date dataVenda;
	private int senhaPedido;
	private boolean flagVendaCancelada;
	
	public VendaVO() {
		super();
	}
	
	public VendaVO(int idVenda, ArrayList<ItemPratoVO> itensPratos, ArrayList<ItemBebidaVO> itensBebidas,
			Date dataVenda, int senhaPedido, boolean flagVendaCancelada) {
		super();
		this.idVenda = idVenda;
		this.itensPratos = itensPratos;
		this.itensBebidas = itensBebidas;
		this.dataVenda = dataVenda;
		this.senhaPedido = senhaPedido;
		this.flagVendaCancelada = flagVendaCancelada;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public ArrayList<ItemPratoVO> getItensPratos() {
		return itensPratos;
	}

	public void setItensPratos(ArrayList<ItemPratoVO> itensPratos) {
		this.itensPratos = itensPratos;
	}

	public ArrayList<ItemBebidaVO> getItensBebidas() {
		return itensBebidas;
	}

	public void setItensBebidas(ArrayList<ItemBebidaVO> itensBebidas) {
		this.itensBebidas = itensBebidas;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public int getSenhaPedido() {
		return senhaPedido;
	}

	public void setSenhaPedido(int senhaPedido) {
		this.senhaPedido = senhaPedido;
	}

	public boolean isFlagVendaCancelada() {
		return flagVendaCancelada;
	}

	public void setFlagVendaCancelada(boolean flagVendaCancelada) {
		this.flagVendaCancelada = flagVendaCancelada;
	}
	
	public void imprimir() {
		double total = 0;
		System.out.print("\nCódigo da Venda: " + getIdVenda() + "     Data da Venda: " + getDataVenda());
		System.out.print("\n---------------------------------------------------------------------------------");
		System.out.printf("\n%6s %1s %-30s %1s %-10s %1s %-8s %1s %-13s %1s ", "TIPO", "|", "DESCRIÇÃO", "|",  "PREÇO", "|",  "QTDE", "|",  "SUBTOTAL", "|");
		System.out.print("\n---------------------------------------------------------------------------------");
		for(int i = 0; i < itensPratos.size(); i++) {
			if(itensPratos.get(i).getIdPrato() > 0) {
				PratoVO pratoVO = new PratoVO();
				pratoVO.setIdPrato(itensPratos.get(i).getIdPrato());
				ControladoraPrato controladoraPrato = new ControladoraPrato();
				PratoVO prato = controladoraPrato.consultarPratoController(pratoVO); 
				if(prato != null) {
					System.out.printf("\n%6s %1s %-30s %1s %-10s %1s %-8s %1s %-13s %1s", "PRATO", "|", prato.getNome(), "|", prato.getPreco(), "|", itensPratos.get(i).getQuantidade(), "|", (prato.getPreco() * itensPratos.get(i).getQuantidade()), "|");
					System.out.print("\n---------------------------------------------------------------------------------");
					total = total + itensPratos.get(i).getQuantidade() * prato.getPreco();
				}
			}
		}
		for(int i = 0; i < itensBebidas.size(); i++) {
			if(itensBebidas.get(i).getIdBebida() > 0) {
				BebidaVO bebidaVO = new BebidaVO();
				bebidaVO.setIdBebida(itensBebidas.get(i).getIdBebida());
				ControladoraBebida controladoraBebida = new ControladoraBebida();
				BebidaVO bebida = controladoraBebida.consultarBebidaController(bebidaVO);
				if(bebida != null) {
					System.out.printf("\n%6s %1s %-30s %1s %-10s %1s %-8s %1s %-13s %1s", "BEBIDA", "|", bebida.getNome(), "|", bebida.getPreco(), "|", itensBebidas.get(i).getQuantidade(), "|", (bebida.getPreco() * itensBebidas.get(i).getQuantidade()), "|");
					System.out.print("\n---------------------------------------------------------------------------------");
					total = total + itensBebidas.get(i).getQuantidade() * bebida.getPreco();
				}
			}
		}
		System.out.print("\nTotal: " + total + "                                                                     |");
	}
	
}
