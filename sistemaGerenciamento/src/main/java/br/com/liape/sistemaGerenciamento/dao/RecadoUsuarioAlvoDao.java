package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Recado;
import br.com.liape.sistemaGerenciamento.model.RecadoUsuarioAlvo;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class RecadoUsuarioAlvoDao extends PersistenceJDBC<RecadoUsuarioAlvo> {

	private static final String LISTAR_ID = "SELECT * FROM recados_usuario_alvo WHERE "
			+ "APAGADO_RUA = 0 AND LOGIN_USR = ? ORDER BY ID_REC";
	private static final String LISTAR_ID_REC = "SELECT * FROM recados_usuario_alvo WHERE "
			+ "APAGADO_RUA = 0 AND ID_REC = ? ORDER BY ID_REC";
	private static final String LISTAR_NOVAS = "SELECT * FROM recados_usuario_alvo WHERE "
			+ "LOGIN_USR=? AND VISUALIZADO_RUA = 0 AND APAGADO_RUA = 0 ORDER BY ID_REC DESC";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<RecadoUsuarioAlvo> listarRecadosUsuario(String loginUsr){
		return super.consultarLista(LISTAR_ID, loginUsr);
	}
	public List<RecadoUsuarioAlvo> listarNovas(String loginUsr){
		return super.consultarLista(LISTAR_NOVAS, loginUsr);
	}
	public List<RecadoUsuarioAlvo> listarRecadosUsuario(int idRec){
		return super.consultarLista(LISTAR_ID_REC, idRec);
	}

}
