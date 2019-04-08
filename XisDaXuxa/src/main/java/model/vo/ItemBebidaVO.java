package model.vo;

public class ItemBebidaVO {
	
	private int idItemBebida;
	private int idBebida;
	private int idVenda;
	private int quantidade;

	public ItemBebidaVO() {
		super();
	}
	
	public ItemBebidaVO(int idItemBebida, int idBebida, int idVenda, int quantidade) {
		super();
		this.idItemBebida = idItemBebida;
		this.idBebida = idBebida;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
	}

	public int getIdItemBebida() {
		return idItemBebida;
	}

	public void setIdItemBebida(int idItemBebida) {
		this.idItemBebida = idItemBebida;
	}

	public int getIdBebida() {
		return idBebida;
	}

	public void setIdBebida(int idBebida) {
		this.idBebida = idBebida;
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
