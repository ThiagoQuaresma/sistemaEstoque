package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Fornecimento;
import modelo.ItemFornecimento;

public interface InterfaceItemFornecimentoDAO {

	public void inserir(ItemFornecimento _itemfornecimento) throws SQLException;
	public Boolean deletar(ItemFornecimento _itemfornecimento) throws SQLException;
	public Boolean atualizar(ItemFornecimento _itemfornecimento) throws SQLException;
	ItemFornecimento buscarPorID(int _id) throws SQLException;
	public List<ItemFornecimento> listarTodos() throws SQLException;
	List<ItemFornecimento> buscarItemsDeFornecimentoDeFornecimento(Fornecimento fornecimento) throws SQLException;
	
	
}
