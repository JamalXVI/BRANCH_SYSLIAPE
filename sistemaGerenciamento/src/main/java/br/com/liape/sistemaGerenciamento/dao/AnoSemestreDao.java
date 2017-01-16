package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class AnoSemestreDao extends PersistenceJDBC<AnoSemestre> {
	
	private static String SELECT_ANO = "SELECT * FROM anosemestre WHERE ATIVO_ANS = TRUE order by ano_ans DESC, SEMESTRE_ANS DESC";
	private static String SELECIONAR_CHAVE = "SELECT * FROM anosemestre WHERE ano_ans = ? AND semestre_ans = ? LIMIT 1";
	private static String SELECIONAR_DATA = "SELECT * FROM anosemestre WHERE DATAINI_ANS <= ? AND "
			+ "DATAFIM_ANS >= ? AND ATIVO_ANS = TRUE";

	public AnoSemestreDao() {
	}

	public List<AnoSemestre> listarAnoSemestre() {
		return consultarLista(SELECT_ANO);
	}

	public List<AnoSemestre> pesquisarPorChave(int ano, int semestre) {
		return super.consultarLista(SELECIONAR_CHAVE, ano, semestre);
	}

	public List<AnoSemestre> pesquisarPorData(LocalDate data) {
		return super.consultarLista(SELECIONAR_DATA, data, data);
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

}
