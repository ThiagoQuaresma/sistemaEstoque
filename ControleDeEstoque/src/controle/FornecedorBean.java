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
import dao.FornecedorDAO;

import modelo.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Fornecedor> listaFornecedores;
	public Fornecedor fornecedor;
	private ListDataModel<Fornecedor> fornecedores;
	
	
	public ListDataModel<Fornecedor> getFornecedores() {
		return fornecedores;
	}


	public void setFornecedores(ListDataModel<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public FornecedorBean(){
		listaFornecedores = new ArrayList<>();
	}
	
	
	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}




	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}



	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}
	
	public void prepararNovo(){
		try {
			this.fornecedor = new Fornecedor();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void cadastrarFornecedor(){
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			this.fornecedor.setDtCadastro(new Date());
			
			FornecedorDAO dao = new FornecedorDAO(conexao);
			dao.inserir(this.fornecedor);
			
			this.listaFornecedores = dao.listarTodos();
			
			this.fornecedores = new ListDataModel<Fornecedor>(this.listaFornecedores);
			
			fabrica.fecharConexao();
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void prepararEditar(){
		
		try{
			
			this.fornecedor = this.fornecedores.getRowData();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
public void editarFornecedor(){
		
		try{
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FornecedorDAO dao = new FornecedorDAO(conexao);
			dao.atualizar(this.fornecedor);
			
			this.listaFornecedores = dao.listarTodos();
			
			this.fornecedores = new ListDataModel<Fornecedor>(this.listaFornecedores); 
			
			fabrica.fecharConexao();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

public void prepararExcluir(){
	
	try{
		
		this.fornecedor = this.fornecedores.getRowData();
		
	}catch (Exception e){
		e.printStackTrace();
	}
}

public void excluirFornecedor(){
	
	try{
		
		FabricaConexao fabrica = new FabricaConexao();
		Connection conexao = fabrica.fazerConexao();
		
		FornecedorDAO dao = new FornecedorDAO(conexao);
		dao.deletar(this.fornecedor);
		
		this.listaFornecedores = dao.listarTodos();
		
		this.fornecedores = new ListDataModel<Fornecedor>(this.listaFornecedores);  
		
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
			FornecedorDAO dao = new FornecedorDAO(conn);

			this.listaFornecedores = dao.listarTodos();
			
			this.fornecedores = new ListDataModel<Fornecedor>(this.listaFornecedores);
			
			fabrica.fecharConexao();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
	}
}
