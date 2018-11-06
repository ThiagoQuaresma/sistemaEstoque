package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.Fornecedor;
import modelo.Produto;


public class FornecedorDAO implements InterfaceFornecedorDAO{

	private Connection conexao;
	
	public FornecedorDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	
	@Override
	public void inserir(Fornecedor _fornecedor) throws SQLException {
		
		String comando = "insert into \"Fornecedor\" (cnpj, nome, endereco, \"dtCadastro\") " + "values (?, ?, ?, ?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		//ps.setInt(1, _fornecedor.getId());//Configurado para auto-incremento
		ps.setInt(1, _fornecedor.getCnpj());
		ps.setString(2, _fornecedor.getNome());
		ps.setString(3, _fornecedor.getEndereco());
		ps.setDate(4, new Date(_fornecedor.getDtCadastro().getTime()));

		ps.execute();
		
		for (Produto item : _fornecedor.getProdutos()) {
			
			ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
			daoProduto.inserir(item);		
		}	
	}
	@Override
	public Boolean deletar(Fornecedor _fornecedor) throws SQLException {
		
		String comando = "delete from \"Fornecedor\" where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _fornecedor.getId());
		
		ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
		List<Produto> listaProdutos = daoProduto.buscarProdutosDeFornecedores(_fornecedor);
		
		for (int i = 0; i < listaProdutos.size(); i++) {
					
					Produto end = listaProdutos.get(i);
					daoProduto.deletar(end);		
		}		
		return ps.execute();
	}
	@Override
	public Boolean atualizar(Fornecedor _fornecedor) throws SQLException {
		
		String comando = "update \"Fornecedor\" set cnpj = ?, nome = ?, endereco = ?  where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _fornecedor.getCnpj());
		ps.setString(2, _fornecedor.getNome());
		ps.setString(3, _fornecedor.getEndereco());
		ps.setInt(4, _fornecedor.getId());

		for (Produto item : _fornecedor.getProdutos()) {
					
					ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
					daoProduto.atualizar(item);		
				}
		
		return ps.execute();
	}
	
	@Override
	public Fornecedor buscarPorID(int _id) throws SQLException {
		
		Fornecedor f = null;
		
		String comando = "select * from \"Fornecedor\" where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			f = new Fornecedor();
			f.setId(rs.getInt(1));
			f.setCnpj(rs.getInt(2));
			f.setNome(rs.getString(3));
			f.setEndereco(rs.getString(4));
			f.setDtCadastro(rs.getDate(5));
			
			ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
			List<Produto> produtos = daoProduto.buscarProdutosDeFornecedores(f);
			
			f.setProdutos(produtos);
		}
		
		return f;
	}
	@Override
	public List<Fornecedor> listarTodos() throws SQLException {
		
		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
		
		String comando = "select * from \"Fornecedor\"";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Fornecedor f  = new Fornecedor();
			f.setId(rs.getInt(1));
			f.setCnpj(rs.getInt(2));
			f.setNome(rs.getString(3));
			f.setEndereco(rs.getString(4));
			f.setDtCadastro(rs.getDate(5));
			
			ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
			List<Produto> produtos = daoProduto.buscarProdutosDeFornecedores(f);
			
			f.setProdutos(produtos);
			listaFornecedor.add(f);
		}
		
		return listaFornecedor;
	}

}
