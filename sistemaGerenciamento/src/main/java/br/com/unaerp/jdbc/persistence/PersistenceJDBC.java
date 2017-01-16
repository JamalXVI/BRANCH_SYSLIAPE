package br.com.unaerp.jdbc.persistence;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class PersistenceJDBC<M> {
	private Class<M> clazz;
	private PreparedStatement stmt;
	private PersistenceSQLFacate sqlFacate;

	public boolean inserir(M obj) {
		Connection con = null;
		try {
			con = getConnection();
			this.stmt = PersistencePreparedStatement.inserir(obj, con);
			if (this.stmt != null) {
				imprimirSQL(this.stmt);
				if (this.stmt.executeUpdate() > 0) {
					return true;
				}
				return false;
			}
			return false;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			boolean bool;
			System.err.println("ERRO: " + e.getMessage());
			return false;
		} finally {
			closeConnections(con);
		}
	}

	protected boolean inserir(String sql, Object... params) {
		return actionGeneric(sql, params);
	}

	protected boolean inserir(String sql, Connection con, Object... params) {
		return actionGeneric(sql, con, params);
	}

	public List<M> listar() {
		ResultSet rs;
		Connection con = null;
		try {
			con = getConnection();
			this.stmt = PersistencePreparedStatement.listar(con, getGenericClass());
			if (this.stmt != null) {
				imprimirSQL(this.stmt);
				rs = this.stmt.executeQuery();
				List<M> lista = new PersistenceResultSet().mapRersultSetToObject(rs, getGenericClass());
				return lista;
			}
			return new ArrayList();
		} catch (SQLException e) {
			System.err.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			closeConnections(con);
		}
	}

	public boolean isExiste(M obj) {
		ResultSet rs;
		Connection con = null;
		try {
			this.stmt = PersistencePreparedStatement.isExiste(obj, con);
			if (this.stmt != null) {
				imprimirSQL(this.stmt);
				rs = this.stmt.executeQuery();
				return rs.next();
			}
			return false;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			System.err.println("ERRO: " + e.getMessage());
			return false;
		} finally {
			closeConnections(con);
		}
	}

	protected List<M> consultarLista(String sql, Object... params) {
		ResultSet rs;
		Connection con = null;
		try {
			con = getConnection();
			this.stmt = con.prepareStatement(sql);
			setObjectoParametros(this.stmt, params);
			imprimirSQL(this.stmt);
			rs = this.stmt.executeQuery();
			List<M> lista = new PersistenceResultSet().mapRersultSetToObject(rs, getGenericClass());
			return lista;
		} catch (SQLException e) {
			System.err.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			closeConnections(con);
		}
	}

	protected M retornarObj(String sql, Object... params) {
		List<M> lista;
		Connection con = null;
		try {
			con = getConnection();
			this.stmt = con.prepareStatement(sql);
			setObjectoParametros(this.stmt, params);
			imprimirSQL(this.stmt);
			ResultSet rs = this.stmt.executeQuery();
			if (rs.next()) {
				lista = new PersistenceResultSet().mapRersultSetToObject(rs, getGenericClass());
				if ((lista != null) && (lista.size() > 0)) {
					return (M) lista.get(0);
				}
			}
			return null;
		} catch (SQLException e) {
			System.err.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			closeConnections(con);
		}
	}

	protected Object consultarParametros(String sql, Object... params) {
		Connection con = null;
		try {
			con = getConnection();
			this.stmt = con.prepareStatement(sql);
			setObjectoParametros(this.stmt, params);
			imprimirSQL(this.stmt);
			ResultSet rs = this.stmt.executeQuery();
			if (rs.next()) {
				return rs.getObject(1);
			}
			return null;
		} catch (SQLException e) {
			Object localObject1;
			System.err.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			closeConnections(con);
		}
	}

	protected ResultSet getResultSetConsulta(String sql, Object... params) {
		Connection con = null;
		try {
			con = getConnection();
			this.stmt = con.prepareStatement(sql);
			setObjectoParametros(this.stmt, params);
			imprimirSQL(this.stmt);
			ResultSet rs = this.stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			ResultSet localResultSet1;
			System.err.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			closeConnections(con);
		}
	}

	public boolean atualizar(M obj) {
		Connection con = null;
		boolean retorno = false;
		try {
			con = getConnection();
			retorno = atualizar(obj, con);
			con.close();
		} catch (SQLException e) {
			retorno = false;
		} finally {
			closeConnections(con);
			return retorno;
		}
	}

	public boolean atualizar(M obj, Connection con) {
		try {
			this.stmt = PersistencePreparedStatement.atualizar(obj, con);
			if (this.stmt != null) {
				imprimirSQL(this.stmt);
				if (this.stmt.executeUpdate() > 0) {
					return true;
				}
				return false;
			}
			return false;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			System.err.println("ERRO: " + e.getMessage());
		}
		return false;
	}

	protected boolean atualizar(String sql, Object... params) {
		return actionGeneric(sql, params);
	}

	protected boolean atualizar(String sql, Connection con, Object... params) {
		return actionGeneric(sql, con, params);
	}

	public boolean deletarTodos() {
		boolean retorno = false;
		Connection con = null;
		try {
			con = getConnection();
			retorno = deletarTodos(con);
			con.close();
		} catch (SQLException e) {
			retorno = false;
		} finally {
			closeConnections(con);
			return retorno;
		}

	}

	public boolean deletarTodos(Connection con) {
		try {
			PreparedStatement stmt = PersistencePreparedStatement.deletarTodos(con, getGenericClass());
			if (stmt != null) {
				imprimirSQL(stmt);
				if (stmt.executeUpdate() > 0) {
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			System.err.println("ERRO: " + e.getMessage());
		}
		return false;
	}

	protected boolean deletar(String sql, Object... params) {
		return actionGeneric(sql, params);
	}

	protected boolean deletar(String sql, Connection con, Object... params) {
		return actionGeneric(sql, con, params);
	}

	private boolean actionGeneric(String sql, Object... params) {
		boolean retorno = false;
		Connection con = null;
		try {
			con = getConnection();
			retorno = inserir(sql, con, params);
			con.close();
		} catch (SQLException e) {
			retorno = false;
		} finally {
			closeConnections(con);
			return retorno;
		}
	}

	private boolean actionGeneric(String sql, Connection con, Object... params) {
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			setObjectoParametros(stmt, params);
			imprimirSQL(stmt);
			if (stmt.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.err.println("ERRO: " + e.getMessage());
		}
		return false;
	}

	private void setObjectoParametros(PreparedStatement stmt, Object... params) throws SQLException {
		int i = 1;
		for (Object value : params) {
			PersistencePreparedStatement.inserirObjetoParametros(stmt, value, i++);
		}
	}

	public Class<M> getGenericClass() {
		try {
			if (this.clazz == null) {
				Type mySuperclass = getClass().getGenericSuperclass();
				Type tType = ((java.lang.reflect.ParameterizedType) mySuperclass).getActualTypeArguments()[0];
				String className = tType.toString().split(" ")[1];
				this.clazz = (Class<M>) Class.forName(className);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.clazz;
	}

	private void closeConnections(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("ERRO: " + e.getMessage());
		}
	}

	private void imprimirSQL(PreparedStatement stmt) throws SQLException {
		if (stmt != null) {
			System.out.println("SQL persistence: " + stmt);
		}
	}

	public PersistenceSQLFacate getSqlFacate() {
		if (this.sqlFacate == null) {
			this.sqlFacate = new PersistenceSQLFacate();
		}
		return this.sqlFacate;
	}

	protected abstract Connection getConnection() throws SQLException;
}
