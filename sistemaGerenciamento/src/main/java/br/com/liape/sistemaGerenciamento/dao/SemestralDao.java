package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Semestral;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SemestralDao extends PersistenceJDBC<Semestral> {
	private static String SQL_LISTAR_DIA_SEMANA = "SELECT * FROM SEMESTRAL WHERE DIA_SEMANA_SEM = ? AND ATIVO_SEM = TRUE";
	private static String SQL_LISTAR = "SELECT * FROM SEMESTRAL WHERE ATIVO_SEM = ?";
	private static String SQL_LISTAR_ID_RES = "SELECT * FROM SEMESTRAL WHERE ID_RES = ? AND ATIVO_SEM = TRUE";
	private static String SQL_LISTAR_ID_SEM = "SELECT * FROM SEMESTRAL WHERE ID_SEM = ? AND ATIVO_SEM = TRUE";
	private static String SQL_LISTAR_HORA = "SELECT * FROM SEMESTRAL"
			+ " WHERE (HORA_INICIO_SEM >= ? AND HORA_INICIO_SEM <= ? "
			+ "AND ID_RES = ? AND ((DAYOFWEEK(?) + 5) % 7) = DIA_SEMANA_SEM AND ATIVO_SEM = TRUE)"
			+ " OR (HORA_TERMINO_SEM >= ? AND HORA_INICIO_SEM <= ?"
			+ "AND ID_RES = ? AND ((DAYOFWEEK(?) + 5) % 7) = DIA_SEMANA_SEM AND ATIVO_SEM = TRUE) ";
	private static String SQL_LISTAR_DIA_SEMANA_PROX = "SELECT * FROM SEMESTRAL WHERE"
			+ " DIA_SEMANA_SEM = ((DAYOFWEEK(?) + 5) % 7) AND ATIVO_SEM = TRUE";
			
	private static String LISTAR_ULTIMO = "SELECT ID_SEM FROM SEMESTRAL ORDER BY ID_SEM DESC LIMIT 1";
	public SemestralDao() {
	}
	public List<Semestral> listarHora(LocalTime horaAtu, LocalTime horaLim, int idRes, LocalDate data) {
		return super.consultarLista(SQL_LISTAR_HORA, horaAtu, horaLim, idRes, data,
				horaAtu, horaAtu, idRes, data);
	}
	public List<Semestral> listarDiaSemana(int dia) {
		return super.consultarLista(SQL_LISTAR_DIA_SEMANA, dia);
	}
	public List<Semestral> listarDiaSemanaProx(LocalDate dia) {
		return super.consultarLista(SQL_LISTAR_DIA_SEMANA_PROX, dia);
	}
	public List<Semestral> listaIdReserva(int idRes) {
		return super.consultarLista(SQL_LISTAR_ID_RES, idRes);
	}

	public List<Semestral> listaIdSem(int idSem) {
		return super.consultarLista(SQL_LISTAR_ID_SEM, idSem);
	}

	@Override
	public List<Semestral> listar() {
		return super.consultarLista(SQL_LISTAR, true);
	}
	
	public int listarUltimo(){
		return (int)super.consultarParametros(LISTAR_ULTIMO);
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
