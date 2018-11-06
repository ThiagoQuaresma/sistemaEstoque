package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Armazem;
import modelo.Fornecimento;

public interface InterfaceFornecimentoDAO {
	public void inserir(Fornecimento _fornecimento) throws SQLException;
	public Boolean deletar(Fornecimento _fornecimento) throws SQLException;
	public Boolean atualizar(Fornecimento _fornecimento) throws SQLException;
	public Fornecimento buscarPorID(int _id) throws SQLException;
	public List<Fornecimento> listarTodos() throws SQLException;
	public List<Fornecimento> buscarFornecimentosDeArmazens(Armazem _armazem) throws SQLException;
}
