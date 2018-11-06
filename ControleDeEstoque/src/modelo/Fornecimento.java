package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fornecimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private int total;
	private Date dtCadastro;
	private List<ItemFornecimento> itemsfornecimento;
	private int idarmazem;
	
	public Fornecimento(){
		this.itemsfornecimento = new ArrayList<ItemFornecimento>();
			
		}

	public Fornecimento(int _id, String _nome, int _total, Date _dtCadastro, List<ItemFornecimento> _itemsfornecimento, int _idarmazem) {
		super();
		this.id = _id;
		this.nome = _nome;
		this.total = _total;
		this.dtCadastro = _dtCadastro;
		this.itemsfornecimento = _itemsfornecimento;
		this.idarmazem = _idarmazem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public List<ItemFornecimento> getItemsfornecimento() {
		return itemsfornecimento;
	}

	public void setItemsfornecimento(List<ItemFornecimento> itemsfornecimento) {
		this.itemsfornecimento = itemsfornecimento;
	}

	public int getIdarmazem() {
		return idarmazem;
	}

	public void setIdarmazem(int idarmazem) {
		this.idarmazem = idarmazem;
	}

	





	
	

}
