package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int cnpj;
	private String nome;
	private String endereco;
	private Date dtCadastro;
	private List<Produto> produtos;
	
	public Fornecedor() {
		this.produtos = new ArrayList<Produto>();
	}
	

	
	public Fornecedor(int _id, int _cnpj, String _nome, String _endereco, Date _dtCadastro, List<Produto> _produtos) {
		super();
		this.id = _id;
		this.cnpj = _cnpj;
		this.nome = _nome;
		this.endereco = _endereco;
		this.dtCadastro = _dtCadastro;
		this.produtos = _produtos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCnpj() {
		return cnpj;
	}
	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void AdicionarProduto(Produto _produto) {
		this.produtos.add(_produto);
	}
	
	public void RemoverProduto(Produto _produto) {
		this.produtos.remove(_produto);
	}
}
