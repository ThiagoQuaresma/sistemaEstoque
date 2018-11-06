package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Fornecedor;

public interface InterfaceFornecedorDAO {
	
	public void inserir(Fornecedor _fornecedor) throws SQLException;
	public Boolean deletar(Fornecedor _fornecedor) throws SQLException;
	public Boolean atualizar(Fornecedor _fornecedor) throws SQLException;
	public Fornecedor buscarPorID(int _id) throws SQLException;
	public List<Fornecedor> listarTodos() throws SQLException;
}
