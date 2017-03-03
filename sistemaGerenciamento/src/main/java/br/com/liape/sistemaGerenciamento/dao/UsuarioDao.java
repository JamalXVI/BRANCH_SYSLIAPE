package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class UsuarioDao extends PersistenceJDBC<Usuario> {

	private final String SQL_LISTAR = "SELECT * FROM usuario WHERE LOGIN_USR = ?";
	private final String SQL_LISTAR_ID_PES = "SELECT * FROM usuario WHERE ID_PES = ?";
	private final String SQL_USUARIO_SENHA = "SELECT * FROM usuario WHERE " 
	+ "LOGIN_USR = ? AND SENHA_USR = MD5(?)";
	private final String SQL_DELETAR_POR_ID = "DELETE FROM usuario WHERE ID_PES = ?";
	
	private final String SQL_ATUALIZAR_GRUPO = "UPDATE USUARIO SET ID_GRP=? WHERE LOGIN_USR = ?";
	public UsuarioDao() {
	}
	@Override
	public boolean inserir(Usuario usuario) {
		usuario.setSenha(MD5(usuario.getSenha()));
		return super.inserir(usuario);
	}
	public List<Usuario> listarPorLogin(String login) {
		return super.consultarLista(SQL_LISTAR, login);
	}

	public List<Usuario> logou(String usuario, String senha) {
		return super.consultarLista(SQL_USUARIO_SENHA, usuario, senha);
	}
	public boolean atualizarGrupo(int idGrp, String login){
		return atualizar(SQL_ATUALIZAR_GRUPO, idGrp, login);
	}
	public boolean deletarPorID(int id) {
		return super.inserir(SQL_DELETAR_POR_ID, id);
	}
	public List<Usuario> listarIdPessoa(int idPes) {
		// TODO Auto-generated method stub
		return super.consultarLista(SQL_LISTAR_ID_PES, idPes);
	}
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	@Override
	public boolean atualizar(Usuario obj) {
		obj.setSenha(MD5(obj.getSenha()));
		return super.atualizar(obj);
	}
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
}
