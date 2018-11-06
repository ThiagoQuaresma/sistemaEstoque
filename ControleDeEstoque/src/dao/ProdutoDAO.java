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

public class ProdutoDAO implements InterfaceProdutoDAO{
	
	private Connection conexao;
	
	public ProdutoDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	
	@Override
	public void inserir(Produto _produto) throws SQLException {
		String comando = "insert into \"Produto\" (codigo, descricao, preco, \"dtCadastro\", idFornecedor) " + "values (?, ?, ?, ?, ?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		//ps.setInt(1, _produto.getId());//Configurado para auto-incremento
		ps.setInt(1, _produto.getCodigo());
		ps.setString(2, _produto.getDescricao());
		ps.setInt(3, _produto.getPreco());
		ps.setDate(4, new Date(_produto.getDtCadastro().getTime()));
		ps.setInt(5, _produto.getIdfornecedor());
		
		ps.execute();
		
				
	}
	@Override
	public Boolean deletar(Produto _produto) throws SQLException {
		
		String comando = "delete from \"Produto\" where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _produto.getId());
		
		return ps.execute();
	}
	@Override
	public Boolean atualizar(Produto _produto) throws SQLException {
		String comando = "update \"Produto\" set codigo = ?, descricao = ?, preco = ?  where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _produto.getCodigo());
		ps.setString(2, _produto.getDescricao());
		ps.setInt(3, _produto.getPreco());
		ps.setInt(4, _produto.getId());

		return ps.execute();
	}
	@Override
	public Produto buscarPorID(int _id) throws SQLException {
		Produto p = null;
		
		String comando = "select * from \"Produto\" where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			p = new Produto();
			p.setId(rs.getInt(1));
			p.setCodigo(rs.getInt(2));
			p.setDescricao(rs.getString(3));
			p.setPreco(rs.getInt(4));
			p.setDtCadastro(rs.getDate(5));
			p.setIdfornecedor(rs.getInt(6));
			
		}
		
		return p;
	}
	@Override
	public List<Produto> listarTodos() throws SQLException {
		
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		String comando = "select * from \"Produto\"";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Produto p = new Produto();
			p.setId(rs.getInt(1));
			p.setCodigo(rs.getInt(2));
			p.setDescricao(rs.getString(3));
			p.setPreco(rs.getInt(4));
			p.setDtCadastro(rs.getDate(5));
			p.setIdfornecedor(rs.getInt(6));

			listaProduto.add(p);
		}
		
		return listaProduto;
	}

	@Override
	public List<Produto> buscarProdutosDeFornecedores(Fornecedor _fornecedor) throws SQLException {
		
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		String comando = "select * from \"Produto\" where idfornecedor = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _fornecedor.getId());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {

			Produto p = new Produto();
			p.setId(rs.getInt(1));
			p.setCodigo(rs.getInt(2));
			p.setDescricao(rs.getString(3));
			p.setPreco(rs.getInt(4));
			p.setDtCadastro(rs.getDate(5));
			p.setIdfornecedor(rs.getInt(6));
			
			
			listaProdutos.add(p);
		}
		
		return listaProdutos;
	}
}
