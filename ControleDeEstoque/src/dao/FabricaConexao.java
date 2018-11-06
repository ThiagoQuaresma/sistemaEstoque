package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaConexao {

	private Connection conexao;
	
	public Connection fazerConexao() {
		try {
			
			String strConexao = "jdbc:postgresql://localhost:5432/Estoque";
			String user = "postgres";
			String password = "letmein";
			
			Class.forName("org.postgresql.Driver");
			this.conexao = DriverManager.getConnection(strConexao, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.conexao;
	}
	
	public void fecharConexao() {
		try {
			this.conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
