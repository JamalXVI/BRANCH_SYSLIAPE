package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.sql.ANSICaseFragment;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class AnoSemestreController extends AbstractController{
	private AnoSemestreDao anoSemestreDao;

	@Inject
	public AnoSemestreController(AnoSemestreDao anoSemestreDao) {
		this.anoSemestreDao = anoSemestreDao;
	}

	public AnoSemestreController() {
		this(null);
	}

	/*
	 * SEÇÃO DE REQUISIÇÕES AJAX
	 */
	

	// ENVIO DE CADASTRO DE ANO/SEMESTRE
	@Post("/Cadastro/AnoSemestre/")

	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 7 
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=7)
	public void postar(@Valid AnoSemestre ans, String ini, String fim) {
		ans.setDataIni(Conversor.converterLocalDate(ini));
		ans.setDataFim(Conversor.converterLocalDate(fim));
		ans.setAtivo(true);
		if (anoSemestreDao.pesquisarPorChave(ans.getAno(), ans.getSemestre()).size() <= 0) {
			registrarLog(FlagsLogAcao.CADASTRO_ANO_SEMESTRE.getCodigo(), ans.toString());
			anoSemestreDao.inserir(ans);
		}else{
			registrarLog(FlagsLogAcao.ATUALIZAR_ANO_SEMESTRE.getCodigo(), ans.toString());
			anoSemestreDao.atualizar(ans);
		};
		
		result.use(Results.json()).withoutRoot().from(anoSemestreDao.listarAnoSemestre()).serialize();
	}
	@NivelPermissao(idPermissao=7)
	@Post("/Remover/AnoSemestre/")
	public void remover(int ano, int semestre)
	{
		List<AnoSemestre> pesquisarPorChave = anoSemestreDao.pesquisarPorChave(ano, semestre);
		MensagemSistema msg = new MensagemSistema("Erro: Ao Deletar o Ano/Semestre");
		if (pesquisarPorChave.size() > 0) {
			AnoSemestre anoSemestre = pesquisarPorChave.get(0);
			anoSemestre.setAtivo(false);
			anoSemestreDao.atualizar(anoSemestre);
			registrarLog(FlagsLogAcao.REMOVER_ANO_SEMESTRE.getCodigo(), anoSemestre.toString());
			msg.setMensagem("Sucesso!!");
		}
	}
	// LISTA TODOS OS ANOS/SEMESTRES
	@Post("/Listar/AnoSemestre/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(anoSemestreDao.listarAnoSemestre())
		.include("DataIni").include("DataFim").serialize();

	}
}
