package model.vo;

public class ItemPratoVO {
	
	private int idItemPrato;
	private int idPrato;
	private int idVenda;
	private int quantidade;
	
	public ItemPratoVO() {
		super();
	}

	public ItemPratoVO(int idItemPrato, int idPrato, int idVenda, int quantidade) {
		super();
		this.idItemPrato = idItemPrato;
		this.idPrato = idPrato;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
	}

	public int getIdItemPrato() {
		return idItemPrato;
	}

	public void setIdItemPrato(int idItemPrato) {
		this.idItemPrato = idItemPrato;
	}

	public int getIdPrato() {
		return idPrato;
	}

	public void setIdPrato(int idPrato) {
		this.idPrato = idPrato;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
