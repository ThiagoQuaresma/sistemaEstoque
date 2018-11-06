package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.Fornecimento;
import modelo.ItemFornecimento;
import modelo.Produto;




public class ItemFornecimentoDAO implements InterfaceItemFornecimentoDAO{

private Connection conexao;
	
	public ItemFornecimentoDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	
	@Override
	public void inserir(ItemFornecimento _itemfornecimento) throws SQLException {
		String comando = "insert into \"ItemFornecimento\" (idproduto, qtdproduto, idfornecimento) " + "values (?, ?, ?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		//ps.setInt(1, _itemfornecimento.getId());//Configurado para auto-incremento
		ps.setInt(1, _itemfornecimento.getProduto().getId());
		ps.setInt(2, _itemfornecimento.getQtdproduto());
		ps.setInt(3, _itemfornecimento.getIdfornecimento());
		
		ps.execute();
			
	}
	@Override
	public Boolean deletar(ItemFornecimento _itemfornecimento) throws SQLException {
		
		String comando = "delete from \"ItemFornecimento\" where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _itemfornecimento.getId());
		
		return ps.execute();
	}
	@Override
	public Boolean atualizar(ItemFornecimento _itemfornecimento) throws SQLException {
		String comando = "update \"ItemFornecimento\" set idproduto = ?, qtdproduto = ?, idfornecimento = ? where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _itemfornecimento.getProduto().getId());
		ps.setInt(2, _itemfornecimento.getQtdproduto());
		ps.setInt(3, _itemfornecimento.getIdfornecimento());
		
		return ps.execute();
	}
	
	@Override
	public ItemFornecimento buscarPorID(int _id) throws SQLException {
		ItemFornecimento i = null;
		
		String comando = "select * from \"ItemFornecimento\" where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			i = new ItemFornecimento();
			i.setId(rs.getInt(1));
			i.setQtdproduto(rs.getInt(3));
			i.setIdfornecimento(rs.getInt(4));

			ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
			Produto p = daoProduto.buscarPorID(rs.getInt(2));
			
			i.setProduto(p);
			
		}
		
		return i;
	}
	
	@Override
	public List<ItemFornecimento> listarTodos() throws SQLException {
		
		List<ItemFornecimento> listaItemsFornecimento = new ArrayList<ItemFornecimento>();
		
		String comando = "select * from \"ItemFornecimento\"";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			ItemFornecimento i= new ItemFornecimento();
			i.setId(rs.getInt(1));
			i.setQtdproduto(rs.getInt(3));
			i.setIdfornecimento(rs.getInt(4));
			
			ProdutoDAO daoProduto = new ProdutoDAO(this.conexao);
			Produto p = daoProduto.buscarPorID(rs.getInt(2));
			
			i.setProduto(p);
			
			listaItemsFornecimento.add(i);//VOLTAR AQUI
		}
		
		return listaItemsFornecimento;
	}
	
	@Override
	public List<ItemFornecimento> buscarItemsDeFornecimentoDeFornecimento(Fornecimento fornecimento) throws SQLException {
		
		List<ItemFornecimento> listaItemsFornecimento = new ArrayList<ItemFornecimento>();
		
		String comando = "select * from \"ItemFornecimento\" where idfornecimento = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, fornecimento.getId());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {

			ItemFornecimento i = new ItemFornecimento();
			i.setId(rs.getInt(1));
			i.setQtdproduto(rs.getInt(2));
			i.setIdfornecimento(rs.getInt(3));

			
			listaItemsFornecimento.add(i);
		}
		
		return listaItemsFornecimento;
	}

}
