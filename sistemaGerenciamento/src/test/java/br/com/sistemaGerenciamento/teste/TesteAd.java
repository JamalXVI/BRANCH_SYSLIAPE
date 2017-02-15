package br.com.sistemaGerenciamento.teste;

import java.util.ArrayList;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import br.com.liape.sistemaGerenciamento.adServerModel.OrganizationalUnit;
import br.com.sistemaGerenciamento.conexao.WebService;
import junit.framework.Assert;

public class TesteAd {

	@Test
	public void teste()
	{
		ArrayList<OrganizationalUnit> cursos = WebService.ListarCursos();
		Assert.assertTrue(cursos.size() > 0);
	}
}
