package modelo;

import java.io.Serializable;

public class ItemFornecimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	Produto produto;
	private int qtdproduto;
	private int idfornecimento;
	
	public ItemFornecimento(){
		
	}
	

	public ItemFornecimento(int _id, Produto _produto, int _qtdproduto, int _idfornecimento) {
		super();
		this.id = _id;
		this.produto = _produto;
		this.qtdproduto = _qtdproduto;
		this.idfornecimento = _idfornecimento;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdfornecimento() {
		return idfornecimento;
	}



	public void setIdfornecimento(int idfornecimento) {
		this.idfornecimento = idfornecimento;
	}


	

	public Produto getProduto() {
		return produto;
	}



	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	public int getQtdproduto() {
		return qtdproduto;
	}

	public void setQtdproduto(int qtdproduto) {
		this.qtdproduto = qtdproduto;
	}



	
	

}
