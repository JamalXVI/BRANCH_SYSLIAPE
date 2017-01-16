package sistemaGerenciamento.teste;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.dao.ExporadicaDao;
import br.com.liape.sistemaGerenciamento.dao.PermissaoDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoUsuarioAlvoDao;
import br.com.liape.sistemaGerenciamento.dao.ReservaDao;
import br.com.liape.sistemaGerenciamento.dao.SemestralDao;
import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.liape.sistemaGerenciamento.model.Exporadico;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.model.Recado;
import br.com.liape.sistemaGerenciamento.model.RecadoUsuarioAlvo;
import br.com.liape.sistemaGerenciamento.model.Reserva;
import br.com.liape.sistemaGerenciamento.model.Semestral;
import br.com.liape.sistemaGerenciamento.modelView.RecadoUsuario;
import br.com.liape.sistemaGerenciamento.modelView.TodasDaReserva;
import junit.framework.Assert;

public class PermisaoTesteDAO {
	
	private ReservaDao dao;
	private AnoSemestreDao anoSemestreDao;
	private SemestralDao semestralDao;
	private ExporadicaDao exporadicaDao;
	@Before
	public void inicializar() {
		dao = new ReservaDao();
		anoSemestreDao = new AnoSemestreDao();
		semestralDao = new SemestralDao();
		exporadicaDao =  new ExporadicaDao();
	}
	
	@Test
	public void inserir(){
		
//		System.out.println(todasRes);
	}



}
