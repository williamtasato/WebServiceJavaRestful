package br.com.database;



import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {
	
	private static String servidor;
	private static String database;
	private static String usuario;
	private static String senha;
	
	public Conexao(String servidor,String database,String usuario,String senha){
	this.servidor=	servidor;
	this.database=database;
	this.usuario=usuario;
	this.senha=senha;
	}
	
	
	public Connection getConexao() {  
        try {  
            // Carregando o JDBC Driver padrão  
            String driverName = "com.mysql.jdbc.Driver";                          
            Class.forName(driverName);  
            // Configurando a nossa conexão com um banco de dados//  
                  //nome do seu banco de dados  
            String url = "jdbc:mysql://" + servidor + "/" + database;  
         //sua senha de acesso  
            Connection connection = (Connection) DriverManager.getConnection(url, usuario, senha);  
            return connection;  
        }  catch (ClassNotFoundException e) {  //Driver não encontrado  
            System.out.println("O driver expecificado nao foi encontrado.");  
            return null;  
        } catch (SQLException e) {  
            //Não conseguindo se conectar ao banco  
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");  
            return null;  
        }  
}  
}
