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

import dao.ArmazemDAO;
import modelo.Armazem;



@ManagedBean
@ViewScoped
public class ArmazemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Armazem> listaArmazems;
	public Armazem armazem;
	private ListDataModel<Armazem> armazems;
	
	
	
	public ListDataModel<Armazem> getArmazems() {
		return armazems;
	}

	public void setArmazems(ListDataModel<Armazem> armazems) {
		this.armazems = armazems;
	}

	public ArmazemBean(){
		listaArmazems = new ArrayList<>();
	}
	
	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

	public List<Armazem> getListaArmazems() {
		return listaArmazems;
	}

	public void setListaArmazems(List<Armazem> listaArmazems) {
		this.listaArmazems = listaArmazems;
	}
	
	public void prepararNovo(){
		try {
			this.armazem = new Armazem();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void cadastrarArmazem(){
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			this.armazem.setDtCadastro(new Date());
			
			ArmazemDAO dao = new ArmazemDAO(conexao);
			dao.inserir(this.armazem);
			
			this.listaArmazems = dao.listarTodos();
			
			this.armazems = new ListDataModel<Armazem>(this.listaArmazems);
			
			fabrica.fecharConexao();
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void prepararEditar(){
		
		try{
			
			this.armazem = this.armazems.getRowData();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
public void editarArmazem(){
		
		try{
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ArmazemDAO dao = new ArmazemDAO(conexao);
			dao.atualizar(this.armazem);
			
			this.listaArmazems = dao.listarTodos();
			
			this.armazems = new ListDataModel<Armazem>(this.listaArmazems); 
			
			fabrica.fecharConexao();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

public void prepararExcluir(){
	
	try{
		
		this.armazem = this.armazems.getRowData();
		
	}catch (Exception e){
		e.printStackTrace();
	}
}

public void excluirArmazem(){
	
	try{
		
		FabricaConexao fabrica = new FabricaConexao();
		Connection conexao = fabrica.fazerConexao();
		
		ArmazemDAO dao = new ArmazemDAO(conexao);
		dao.deletar(this.armazem);
		
		this.listaArmazems = dao.listarTodos();
		
		this.armazems = new ListDataModel<Armazem>(this.listaArmazems); 
		
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
			
			ArmazemDAO dao = new ArmazemDAO(conn);
			this.listaArmazems = dao.listarTodos();
			
			this.armazems = new ListDataModel<Armazem>(this.listaArmazems); 
			
			fabrica.fecharConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
