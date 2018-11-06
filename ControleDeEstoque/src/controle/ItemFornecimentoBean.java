package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.FabricaConexao;
import dao.ItemFornecimentoDAO;
import modelo.ItemFornecimento;



@ManagedBean
@ViewScoped
public class ItemFornecimentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ItemFornecimento> listaItemsFornecimentos;
	public ItemFornecimento itemfornecimento;
	private ListDataModel<ItemFornecimento> itemsfornecimentos;
	
	
	public ListDataModel<ItemFornecimento> getItemsfornecimentos() {
		return itemsfornecimentos;
	}

	public void setItemsfornecimentos(ListDataModel<ItemFornecimento> itemsfornecimentos) {
		this.itemsfornecimentos = itemsfornecimentos;
	}
	
	public ItemFornecimentoBean(){
		listaItemsFornecimentos = new ArrayList<>();
	}
	
	public ItemFornecimento getItemFornecimento() {
		return itemfornecimento;
	}
	
	public void setItemfornecimento(ItemFornecimento itemfornecimento) {
		this.itemfornecimento = itemfornecimento;
	}

	public List<ItemFornecimento> getListaItemsFornecimentos() {
		return listaItemsFornecimentos;
	}

	public void setListaItemsFornecimentos(List<ItemFornecimento> listaItemsFornecimentos) {
		this.listaItemsFornecimentos = listaItemsFornecimentos;
	
	}
	
	public void prepararNovo(){
		try {
			this.itemfornecimento = new ItemFornecimento();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void cadastrarItemFornecimento(){
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			
			ItemFornecimentoDAO dao = new ItemFornecimentoDAO(conexao);
			dao.inserir(this.itemfornecimento);
			
			this.listaItemsFornecimentos = dao.listarTodos();
			
			this.itemsfornecimentos = new ListDataModel<ItemFornecimento>(this.listaItemsFornecimentos);
			
			fabrica.fecharConexao();
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void prepararEditar(){
		
		try{
			
			this.itemfornecimento = this.itemsfornecimentos.getRowData();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
public void editarItemFornecimento(){
		
		try{
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ItemFornecimentoDAO dao = new ItemFornecimentoDAO(conexao);
			dao.atualizar(this.itemfornecimento);
			
			this.listaItemsFornecimentos = dao.listarTodos();
			
			this.itemsfornecimentos = new ListDataModel<ItemFornecimento>(this.listaItemsFornecimentos); 
			
			fabrica.fecharConexao();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

public void prepararExcluir(){
	
	try{
		
		this.itemfornecimento = this.itemsfornecimentos.getRowData();
		
	}catch (Exception e){
		e.printStackTrace();
	}
}

public void excluirItemFornecimento(){
	
	try{
		
		FabricaConexao fabrica = new FabricaConexao();
		Connection conexao = fabrica.fazerConexao();
		
		ItemFornecimentoDAO dao = new ItemFornecimentoDAO(conexao);
		dao.deletar(this.itemfornecimento);
		
		this.listaItemsFornecimentos = dao.listarTodos();
		
		this.itemsfornecimentos = new ListDataModel<ItemFornecimento>(this.listaItemsFornecimentos);  
		
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
			
			ItemFornecimentoDAO dao = new ItemFornecimentoDAO(conn);
			this.listaItemsFornecimentos = dao.listarTodos();
			
			this.itemsfornecimentos = new ListDataModel<ItemFornecimento>(this.listaItemsFornecimentos);
			
			fabrica.fecharConexao();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
	}
}
