package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Fornecimento;

import modelo.Armazem;


public class ArmazemDAO implements InterfaceArmazemDAO{

	private Connection conexao;
	
	public ArmazemDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	
	@Override
	public void inserir(Armazem _armazem) throws SQLException {
		String comando = "insert into \"Armazem\" (nome, endereco, tel, \"dtCadastro\") " + "values (?, ?, ?, ?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		//ps.setInt(1, _pessoa.getId());//Configurado para auto-incremento
		ps.setString(1, _armazem.getNome());
		ps.setString(2, _armazem.getEndereco());
		ps.setString(3, _armazem.getTel());
		ps.setDate(4, new Date(_armazem.getDtCadastro().getTime()));

		ps.execute();
		
		for (Fornecimento item : _armazem.getFornecimento()) {
					
			FornecimentoDAO daoFornecimento = new FornecimentoDAO(this.conexao);
				daoFornecimento.inserir(item);		
			}		
	}

	@Override
	public Boolean deletar(Armazem _armazem) throws SQLException {
		
		String comando = "delete from \"Armazem\" where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _armazem.getId());
		
		FornecimentoDAO daoFornecimento = new FornecimentoDAO(this.conexao);
		List<Fornecimento> listaFornecimentos = daoFornecimento.buscarFornecimentosDeArmazens(_armazem);
		
		for (int i = 0; i < listaFornecimentos.size(); i++) {
					
					Fornecimento end = listaFornecimentos.get(i);
					daoFornecimento.deletar(end);		
		}		
		return ps.execute();
	}

	@Override
	public Boolean atualizar(Armazem _armazem) throws SQLException {
		String comando = "update \"Armazem\" set nome = ?, endereco = ?, tel = ? where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setString(1, _armazem.getNome());
		ps.setString(2, _armazem.getEndereco());
		ps.setString(3, _armazem.getTel());
		ps.setInt(4, _armazem.getId());

		for (Fornecimento item : _armazem.getFornecimento()) {
			
			FornecimentoDAO daoFornecimento = new FornecimentoDAO(this.conexao);
			daoFornecimento.atualizar(item);		
		}
		
		return ps.execute();
	}

	@Override
	public Armazem buscarPorID(int _id) throws SQLException {
		
		Armazem p = null;
		
		String comando = "select * from \"Armazem\" where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			p = new Armazem();
			p.setId(rs.getInt(1));
			p.setNome(rs.getString(2));
			p.setEndereco(rs.getString(3));
			p.setTel(rs.getString(4));
			p.setDtCadastro(rs.getDate(5));
			
			FornecimentoDAO daoFornecimento = new FornecimentoDAO(this.conexao);
			List<Fornecimento> fornecimentos = daoFornecimento.buscarFornecimentosDeArmazens(p);
			
			p.setFornecimento(fornecimentos);
		}
		
		return p;
	}

	@Override
	public List<Armazem> listarTodos() throws SQLException {
		
		List<Armazem> listaArmazem = new ArrayList<Armazem>();
		
		String comando = "select * from \"Armazem\"";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Armazem a = new Armazem();
			a.setId(rs.getInt(1));
			a.setNome(rs.getString(2));
			a.setEndereco(rs.getString(3));
			a.setTel(rs.getString(4));
			a.setDtCadastro(rs.getDate(5));

			
			FornecimentoDAO daoFornecimento = new FornecimentoDAO(this.conexao);
			List<Fornecimento> fornecimentos = daoFornecimento.buscarFornecimentosDeArmazens(a);
			
			a.setFornecimento(fornecimentos);
			listaArmazem.add(a);
		}
		
		return listaArmazem;
	}

}
