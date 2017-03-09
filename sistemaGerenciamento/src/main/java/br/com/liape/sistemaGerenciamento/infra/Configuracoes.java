package br.com.liape.sistemaGerenciamento.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Configuracoes {
	private int tempoAntesDaAula;
	public static final Configuracoes configuracao = iniciarConfiguracao();

	public Configuracoes(int tempoAntesDaAula) {
		this.tempoAntesDaAula = tempoAntesDaAula;
	}

	private static Configuracoes iniciarConfiguracao() {
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("configuracoes", Configuracoes.class);
			String pasta = System.getenv("SYSLIAPE");
			InputStream in = new FileInputStream(pasta + "\\configuracoes.xml");
			return (Configuracoes) xstream.fromXML(in);
		} catch (FileNotFoundException e) {
			System.err.println("ERRO: " + e.getMessage());
		}
		return null;
	}

	public int getTempoAntesDaAula() {
		return tempoAntesDaAula;
	}

	public void setTempoAntesDaAula(int tempoAntesDaAula) {
		this.tempoAntesDaAula = tempoAntesDaAula;
	}

}
