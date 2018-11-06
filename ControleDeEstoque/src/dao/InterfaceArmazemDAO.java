package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Armazem;


public interface InterfaceArmazemDAO {

	public void inserir(Armazem _armazem) throws SQLException;
	public Boolean deletar(Armazem _armazem) throws SQLException;
	public Boolean atualizar(Armazem _armazem) throws SQLException;
	public Armazem buscarPorID(int _id) throws SQLException;
	public List<Armazem> listarTodos() throws SQLException;
}
