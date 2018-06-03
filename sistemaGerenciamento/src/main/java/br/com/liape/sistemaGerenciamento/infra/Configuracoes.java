]package br.com.liape.sistemaGerenciamento.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.liape.sistemaGerenciamento.model.ConfEmail;
import br.com.liape.sistemaGerenciamento.model.MenuUsuarioItem;

public class Configuracoes {
	private int tempoAntesDaAula;
	private String caminhoSalvarArquivo;
	private String caminhoRelatorios;
	@XStreamImplicit(itemFieldName = "menuOrdens")
	private List<MenuUsuarioItem> menuOrdens;
	private ConfEmail email;
	private String urlSite;
	private String horaEnvioEmailEscala;
	private int tempoIntervalo;
	public static final Configuracoes configuracao = iniciarConfiguracao();

	public Configuracoes(int tempoAntesDaAula, String caminhoSalvarArquivo) {
		this.tempoAntesDaAula = tempoAntesDaAula;
		this.caminhoSalvarArquivo = caminhoSalvarArquivo;
	}

	private static Configuracoes iniciarConfiguracao() {
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("configuracoes", Configuracoes.class);
	        xstream.processAnnotations(MenuUsuarioItem.class);
			String pasta = System.getenv("SYSLIAPE");
			InputStream in = new FileInputStream(pasta + "\\configuracoes.xml");
			return (Configuracoes) xstream.fromXML(in);
		} catch (FileNotFoundException e) {
			System.err.println("ERRO: " + e.getMessage());
		} catch (Exception e){
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

	public String getCaminhoSalvarArquivo() {
		return caminhoSalvarArquivo;
	}

	public void setCaminhoSalvarArquivo(String caminhoSalvarArquivo) {
		this.caminhoSalvarArquivo = caminhoSalvarArquivo;
	}

	public List<MenuUsuarioItem> getMenuOrdens() {
		return menuOrdens;
	}

	public void setMenuOrdens(List<MenuUsuarioItem> menuOrdens) {
		this.menuOrdens = menuOrdens;
	}

	public ConfEmail getEmail() {
		return email;
	}

	public void setEmail(ConfEmail email) {
		this.email = email;
	}

	public String getUrlSite() {
		return urlSite;
	}

	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}

	public String getHoraEnvioEmailEscala() {
		return horaEnvioEmailEscala;
	}

	public void setHoraEnvioEmailEscala(String horaEnvioEmailEscala) {
		this.horaEnvioEmailEscala = horaEnvioEmailEscala;
	}

	public String getCaminhoRelatorios() {
		return caminhoRelatorios;
	}

	public void setCaminhoRelatorios(String caminhoRelatorios) {
		this.caminhoRelatorios = caminhoRelatorios;
	}

	public int getTempoIntervalo() {
		return tempoIntervalo;
	}

	public void setTempoIntervalo(int tempoIntervalo) {
		this.tempoIntervalo = tempoIntervalo;
	}
	
	
	
	
}
