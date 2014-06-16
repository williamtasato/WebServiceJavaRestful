package br.com.database;

import java.sql.Connection;
import java.sql.ResultSet;

public class Pesquisa {
	private Connection connection;
	private ResultSet resultSet;
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}
