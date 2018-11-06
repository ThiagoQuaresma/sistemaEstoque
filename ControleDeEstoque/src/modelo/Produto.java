package modelo;

import java.io.Serializable;

import java.util.Date;


public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int codigo;
	private String descricao;
	private int preco;
	private Date dtCadastro;
	private int idfornecedor;
	
	public Produto(){
		
	}

	public Produto(int _id, int _codigo, String _descricao, int _preco, Date _dtCadastro, int _idfornecedor) {
		super();
		this.id = _id;
		this.codigo = _codigo;
		this.descricao = _descricao;
		this.preco = _preco;
		this.dtCadastro = _dtCadastro;
		this.idfornecedor = _idfornecedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public int getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(int idfornecedor) {
		this.idfornecedor = idfornecedor;
	}
	
	
	
}
