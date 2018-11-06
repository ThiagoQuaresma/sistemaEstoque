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
import dao.ProdutoDAO;
import modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Produto> listaProdutos;
	public Produto produto;
	private ListDataModel<Produto> produtos;
	
	
	public ListDataModel<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(ListDataModel<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoBean(){
		listaProdutos = new ArrayList<>();
	}
	
	
	
	
	public Produto getProduto() {
		return produto;
	}




	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public void prepararNovo(){
		try {
			this.produto = new Produto();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void cadastrarProduto(){
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			this.produto.setDtCadastro(new Date());
			
			ProdutoDAO dao = new ProdutoDAO(conexao);
			dao.inserir(this.produto);
			
			this.listaProdutos = dao.listarTodos();
			
			this.produtos = new ListDataModel<Produto>(this.listaProdutos);
			
			fabrica.fecharConexao();
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public void prepararEditar(){
		
		try{
			
			this.produto = this.produtos.getRowData();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
public void editarProduto(){
		
		try{
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ProdutoDAO dao = new ProdutoDAO(conexao);
			dao.atualizar(this.produto);
			
			this.listaProdutos = dao.listarTodos();
			
			this.produtos = new ListDataModel<Produto>(this.listaProdutos); 
			
			fabrica.fecharConexao();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

public void prepararExcluir(){
	
	try{
		
		this.produto = this.produtos.getRowData();
		
	}catch (Exception e){
		e.printStackTrace();
	}
}

public void excluirProduto(){
	
	try{
		
		FabricaConexao fabrica = new FabricaConexao();
		Connection conexao = fabrica.fazerConexao();
		
		ProdutoDAO dao = new ProdutoDAO(conexao);
		dao.deletar(this.produto);
		
		this.listaProdutos = dao.listarTodos();
		
		this.produtos = new ListDataModel<Produto>(this.listaProdutos);  
		
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
			ProdutoDAO dao = new ProdutoDAO(conn);

			this.listaProdutos = dao.listarTodos();
			
			this.produtos = new ListDataModel<Produto>(this.listaProdutos);
			
			fabrica.fecharConexao();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
	}
}
