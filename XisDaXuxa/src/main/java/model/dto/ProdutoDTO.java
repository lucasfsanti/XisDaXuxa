package model.dto;

public class ProdutoDTO {
	
	private int idProduto;
	private String nome;
	private double preco;
	
	public ProdutoDTO() {
		super();
	}
	
	public ProdutoDTO(int idProduto, String nome, double preco) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.preco = preco;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void imprimir() {
		System.out.printf("\n%3s   %-20s   %-10s   ", this.getIdProduto(), this.getNome(), this.getPreco());
	}

}
