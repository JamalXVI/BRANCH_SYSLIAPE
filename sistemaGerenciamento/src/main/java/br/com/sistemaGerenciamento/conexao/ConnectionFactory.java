	package br.com.sistemaGerenciamento.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.liape.sistemaGerenciamento.model.Conexao;

public class ConnectionFactory {
	
	private static Conexao connXML = ConexaoXML.getInstance();
	private static final String URL = "jdbc:mysql://" + connXML.getHost() + "/" + connXML.getDatabase();
	/*
	private static final String DATABASE = "sisger";
	private static final String HOST = "localhost";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE;
	private static final String USR = "root";
	private static final String PWD = "(L10p3*NoV0G37!";
	
*/

	public static Connection getConnections() {
		try {
			Class.forName(connXML.getDriver());
			return DriverManager.getConnection(URL, connXML.getUsuario(), connXML.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("ERRO: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnections(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

}
