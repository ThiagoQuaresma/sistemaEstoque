package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Armazem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String endereco;
	private String tel;
	private Date dtCadastro;
	private List<Fornecimento> fornecimentos;
	
	public Armazem() {
		this.fornecimentos = new ArrayList<Fornecimento>();
	}
	
	public Armazem(int _id, String _nome, String _endereco, String _tel, Date _dtCadastro, List<Fornecimento> _fornecimentos) {
		super();
		this.id = _id;
		this.nome = _nome;
		this.endereco = _endereco;
		this.tel = _tel;
		this.dtCadastro = _dtCadastro;
		this.fornecimentos = _fornecimentos;
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
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public Date getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public List<Fornecimento> getFornecimento() {
		return fornecimentos;
	}

	public void setFornecimento(List<Fornecimento> fornecimentos) {
		this.fornecimentos = fornecimentos;
	}	
	
	public void AdicionarFornecimento(Fornecimento _fornecimento) {
		this.fornecimentos.add(_fornecimento);
	}
	
	public void RemoverFornecimento(Fornecimento _fornecimento) {
		this.fornecimentos.remove(_fornecimento);
	}
}
