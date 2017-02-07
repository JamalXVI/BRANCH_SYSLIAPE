package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.UsuarioEscala;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class UsuarioEscalaDao extends PersistenceJDBC<UsuarioEscala> {
	
	private static final String LISTAR_ESCALA = "SELECT * FROM usuario_escala WHERE ID_ESC = ?";
	public UsuarioEscalaDao() {
	}
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<UsuarioEscala> listar_ativo(int id) {
		return consultarLista(LISTAR_ESCALA, id);
	}
}
