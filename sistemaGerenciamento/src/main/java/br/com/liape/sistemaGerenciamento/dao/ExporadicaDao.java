package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Exporadico;
import br.com.liape.sistemaGerenciamento.model.Semestral;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class ExporadicaDao extends PersistenceJDBC<Exporadico> {
	private static String SQL_LISTAR_DATA = "SELECT * FROM EXPORADICO WHERE DATA_MARCADA_EXP = ? AND ATIVO_EXP =  TRUE";
	private static String SQL_LISTAR = "SELECT * FROM EXPORADICO WHERE ATIVO_EXP = ?";
	private static String SQL_LISTAR_ID_RES = "SELECT * FROM EXPORADICO WHERE ID_RES = ? AND ATIVO_EXP = TRUE";
	private static String SQL_LISTAR_ESPECIFICO = "SELECT * FROM EXPORADICO WHERE ID_RES = ?"
			+ " AND DATA_MARCADA_EXP = ? AND HORA_INICIO_EXP = ? AND HORA_FIM_EXP = ? AND ATIVO_EXP = TRUE";
	private static String SQL_VER_SE_EXISTE = "SELECT * FROM EXPORADICO WHERE ID_RES = ?"
			+ " AND DATA_MARCADA_EXP = ? AND HORA_INICIO_EXP = ? AND HORA_FIM_EXP = ?";
	private static String SQL_POR_MES = "SELECT * FROM EXPORADICO WHERE MONTH(DATA_MARCADA_EXP) = ? AND ATIVO_EXP = TRUE";
	private static String SQL_LISTAR_HORA = "SELECT * FROM EXPORADICO WHERE HORA_INICIO_EXP >= ? AND"
			+ " HORA_INICIO_EXP <= ?  AND ID_RES = ? AND DATA_MARCADA_EXP = ? AND ATIVO_EXP = TRUE";
	
	public ExporadicaDao() {
	}
	public List<Exporadico> listarDataMarcada(LocalDate data)
	{
		return super.consultarLista(SQL_LISTAR_DATA, data);
	}
	public List<Exporadico> listarIdReserva(int idRes)
	{
		return super.consultarLista(SQL_LISTAR_ID_RES, idRes);
	}
	public List<Exporadico> listarPorHora(LocalTime horaAtu, LocalTime horaLim, int idRes, LocalDate data)
	{
		return super.consultarLista(SQL_LISTAR_HORA, horaAtu, horaLim, idRes, data);
	}
	@Override
	public List<Exporadico> listar() {
		// TODO Auto-generated method stub
		return super.consultarLista(SQL_LISTAR, true);
	}
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<Exporadico> listarExporadico(int idResExp, LocalDate data, LocalTime horaIni,
			LocalTime horaFim) {
		return super.consultarLista(SQL_LISTAR_ESPECIFICO, idResExp, data, horaIni, horaFim);
		
	}
	public List<Exporadico> verSeExite(int idResExp, LocalDate data, LocalTime horaIni,
			LocalTime horaFim) {
		return super.consultarLista(SQL_VER_SE_EXISTE, idResExp, data, horaIni, horaFim);
		
	}
	public List<Exporadico> listarMes(int mes){
		return super.consultarLista(SQL_POR_MES, mes);
	}
}
