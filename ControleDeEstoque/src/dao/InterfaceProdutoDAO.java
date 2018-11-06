package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Fornecedor;
import modelo.Produto;

public interface InterfaceProdutoDAO {
	
	public void inserir(Produto _produto) throws SQLException;
	public Boolean deletar(Produto _produto) throws SQLException;
	public Boolean atualizar(Produto _produto) throws SQLException;
	public Produto buscarPorID(int _id) throws SQLException;
	public List<Produto> listarTodos() throws SQLException;
	List<Produto> buscarProdutosDeFornecedores(Fornecedor _fornecedor) throws SQLException;

}
