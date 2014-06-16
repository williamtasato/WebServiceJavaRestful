package br.com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class InteracaoDados {

	private static Connection conn;
	private Conexao con;
	


 public int Executar(String sql) throws SQLException{
	 con=new Conexao("localhost", "Teste", "root", "");
	 conn=con.getConexao();
	 
	Statement stmt =  conn.createStatement();
	boolean resultado= stmt.execute(sql);
	 stmt.close();
	 conn.close();
	 if(resultado == true){
		return 1; 
	 }else{
	 return 0;
	 }
	
	 
	 }
 
 public Pesquisa Pesquisar(String sql) throws SQLException{
	 con=new Conexao("localhost", "Teste", "root", "");
	 conn=con.getConexao();
	 PreparedStatement statement= conn.prepareStatement(sql);;
	 ResultSet set= statement.executeQuery();
	 Pesquisa pesquisa=new Pesquisa();
	 pesquisa.setConnection(conn);
	 pesquisa.setResultSet(set);
		
	 return pesquisa;
 }
 
}