package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Armazem;

import modelo.Fornecimento;
import modelo.ItemFornecimento;



public class FornecimentoDAO implements InterfaceFornecimentoDAO{
	
	private Connection conexao;
	
	public FornecimentoDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	public void inserir(Fornecimento _fornecimento) throws SQLException {
		String comando = "insert into \"Fornecimento\" (nome, total, \"dtCadastro\", idarmazem) " + "values (?, ?, ?,?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		//ps.setInt(1, _produto.getId());//Configurado para auto-incremento
		ps.setString(1, _fornecimento.getNome());
		ps.setInt(2, _fornecimento.getTotal());
		ps.setDate(3, new Date(_fornecimento.getDtCadastro().getTime()));
		ps.setInt(4, _fornecimento.getIdarmazem());
		ps.execute();
		
	for (ItemFornecimento item : _fornecimento.getItemsfornecimento()) {
				
		ItemFornecimentoDAO daoItemFornecimento = new ItemFornecimentoDAO(this.conexao);
				daoItemFornecimento.inserir(item);		
			}			
	}
	@Override
	public Boolean deletar(Fornecimento _fornecimento) throws SQLException {
		
		String comando = "delete from \"Fornecimento\" where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _fornecimento.getId());
		
		ItemFornecimentoDAO daoItemFornecimento = new ItemFornecimentoDAO(this.conexao);
		List<ItemFornecimento> listaItemFornecimentos = daoItemFornecimento.buscarItemsDeFornecimentoDeFornecimento(_fornecimento);
		
		for (int i = 0; i < listaItemFornecimentos.size(); i++) {
			
			ItemFornecimento end = listaItemFornecimentos.get(i);
			daoItemFornecimento.deletar(end);		
		}
		
		return ps.execute();
	}
	@Override
	public Boolean atualizar(Fornecimento _fornecimento) throws SQLException {
		String comando = "update \"Fornecimento\" set nome = ?, total = ?, idarmazem = ?, where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setString(1, _fornecimento.getNome());
		ps.setInt(2, _fornecimento.getTotal());
		ps.setInt(3, _fornecimento.getIdarmazem());
		ps.setInt(4, _fornecimento.getId());
		
		return ps.execute();
	}
	@Override
	public Fornecimento buscarPorID(int _id) throws SQLException {
		Fornecimento f = null;
		
		String comando = "select * from \"Fornecimento\" where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			f = new Fornecimento();
			f.setId(rs.getInt(1));
			f.setNome(rs.getString(2));
			f.setTotal(rs.getInt(3));
			f.setDtCadastro(rs.getDate(4));
			f.setIdarmazem(rs.getInt(5));
			
			ItemFornecimentoDAO daoItemFornecimento = new ItemFornecimentoDAO(this.conexao);
			List<ItemFornecimento> itemsfornecimento = daoItemFornecimento.buscarItemsDeFornecimentoDeFornecimento(f);
			
			f.setItemsfornecimento(itemsfornecimento);
		}
		
		return f;
	}
	@Override
	public List<Fornecimento> listarTodos() throws SQLException {
		List<Fornecimento> listaFornecimentos = new ArrayList<Fornecimento>();
		
		String comando = "select * from \"Fornecimento\"";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Fornecimento f  = new Fornecimento();
			f.setId(rs.getInt(1));
			f.setNome(rs.getString(2));
			f.setTotal(rs.getInt(3));
			f.setDtCadastro(rs.getDate(4));
			f.setIdarmazem(rs.getInt(5));
			
			ItemFornecimentoDAO daoItemFornecimento = new ItemFornecimentoDAO(this.conexao);
			List<ItemFornecimento> itemsfornecimento = daoItemFornecimento.buscarItemsDeFornecimentoDeFornecimento(f);
			
			f.setItemsfornecimento(itemsfornecimento);
			listaFornecimentos.add(f);
		}
		
		return listaFornecimentos;
	}
	@Override
	public List<Fornecimento> buscarFornecimentosDeArmazens(Armazem _armazem) throws SQLException {
		
		List<Fornecimento> listaFornecimentos = new ArrayList<Fornecimento>();
		
		String comando = "select * from \"Fornecimento\" where idarmazem = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _armazem.getId());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {

			Fornecimento f = new Fornecimento();
			f.setId(rs.getInt(1));
			f.setNome(rs.getString(2));
			f.setTotal(rs.getInt(3));
			f.setDtCadastro(rs.getDate(4));
			f.setIdarmazem(rs.getInt(5));
			
			listaFornecimentos.add(f);
		
		}
		
		return listaFornecimentos;
	}
}
