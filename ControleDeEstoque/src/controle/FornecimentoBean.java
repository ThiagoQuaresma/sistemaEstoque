package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.FabricaConexao;

import dao.FornecimentoDAO;
import modelo.Fornecimento;



@ManagedBean
@ViewScoped
public class FornecimentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Fornecimento> listaFornecimentos;
	public Fornecimento fornecimento;
	private ListDataModel<Fornecimento> fornecimentos;
	
	
	
	public ListDataModel<Fornecimento> getFornecimentos() {
		return fornecimentos;
	}

	public void setFornecimentos(ListDataModel<Fornecimento> fornecimentos) {
		this.fornecimentos = fornecimentos;
	}

	public FornecimentoBean(){
		listaFornecimentos = new ArrayList<>();
	}
	
	public Fornecimento getFornecimento() {
		return fornecimento;
	}

	public void setFornecimento(Fornecimento fornecimento) {
		this.fornecimento = fornecimento;
	}

	public List<Fornecimento> getListaFornecimentos() {
		return listaFornecimentos;
	}

	public void setListaFornecimentos(List<Fornecimento> listaFornecimentos) {
		this.listaFornecimentos = listaFornecimentos;
	}
	
	public void prepararNovo(){
		try {
			this.fornecimento = new Fornecimento();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void cadastrarFornecimento(){
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			this.fornecimento.setDtCadastro(new Date());
			
			FornecimentoDAO dao = new FornecimentoDAO(conexao);
			dao.inserir(this.fornecimento);
			
			this.listaFornecimentos = dao.listarTodos();
			
			this.fornecimentos = new ListDataModel<Fornecimento>(this.listaFornecimentos);
			
			fabrica.fecharConexao();
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void prepararEditar(){
		
		try{
			
			this.fornecimento = this.fornecimentos.getRowData();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
public void editarFornecimento(){
		
		try{
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FornecimentoDAO dao = new FornecimentoDAO(conexao);
			dao.atualizar(this.fornecimento);
			
			this.listaFornecimentos = dao.listarTodos();
			
			this.fornecimentos = new ListDataModel<Fornecimento>(this.listaFornecimentos); 
			
			fabrica.fecharConexao();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

public void prepararExcluir(){
	
	try{
		
		this.fornecimento = this.fornecimentos.getRowData();
		
	}catch (Exception e){
		e.printStackTrace();
	}
}

public void excluirFornecimento(){
	
	try{
		
		FabricaConexao fabrica = new FabricaConexao();
		Connection conexao = fabrica.fazerConexao();
		
		FornecimentoDAO dao = new FornecimentoDAO(conexao);
		dao.deletar(this.fornecimento);
		
		this.listaFornecimentos = dao.listarTodos();
		
		this.fornecimentos = new ListDataModel<Fornecimento>(this.listaFornecimentos); 
		
		fabrica.fecharConexao();
		
	}catch (Exception e){
		e.printStackTrace();
	}
}


	@PostConstruct
	private void inicializar() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			
			FornecimentoDAO dao = new FornecimentoDAO(conn);
			this.listaFornecimentos = dao.listarTodos();
			
			this.fornecimentos = new ListDataModel<Fornecimento>(this.listaFornecimentos); 
			
			fabrica.fecharConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
