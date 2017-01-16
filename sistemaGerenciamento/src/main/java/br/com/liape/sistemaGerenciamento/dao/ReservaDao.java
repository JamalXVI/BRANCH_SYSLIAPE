package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Reserva;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class ReservaDao extends PersistenceJDBC<Reserva> {

	private static String ULTIMO_ID = "SELECT * FROM reserva ORDER BY ID_RES DESC LIMIT 1";
	private static String SELECT_ID = "SELECT * FROM reserva WHERE ID_RES = ?";
	private static String VERIFICAR_EXISTE = "SELECT * FROM reserva WHERE "
			+ " CODIGO_DIS = ? AND CODIGO_PRO = ? AND ANO_ANS = ? AND SEMESTRE_ANS = ? AND"
			+ " CODIGO_CUR = ? AND TURMA_RES = ? LIMIT 1";
	private static String MOSTRAR_PROXIMAS = "SELECT * FROM reserva WHERE ANO_ANS = ? AND "
			+ "SEMESTRE_ANS = ?";
	public ReservaDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<Reserva> listarPorId(int id)
	{
		return super.consultarLista(SELECT_ID, id);
	}
	public List<Reserva> ultimoId() {
		return super.consultarLista(ULTIMO_ID);
	}

	public boolean verificarSeExiste(Reserva reserva) {
		
		return !retornarReserva(reserva).isEmpty();
	}
	public List<Reserva> retornarReserva(Reserva reserva)
	{
		return consultarLista(VERIFICAR_EXISTE, reserva.getCodigoDis(), 
				reserva.getCodigoPro(), reserva.getAnoAns(), reserva.getSemestreAns(), reserva.getCodigoCur(), 
				reserva.getTurma());
	}
	public List<Reserva> proximasReservas(int ano, int semestre)
	{
		return super.consultarLista(MOSTRAR_PROXIMAS, ano, semestre);
	}
}
