package sistemaGerenciamento.teste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.liape.sistemaGerenciamento.model.Conexao;

public class TestarXtream {
	@Test
	public void main() {
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("conexao", Conexao.class);
			String pasta = System.getenv("SYSLIAPE");
			InputStream in = new FileInputStream(pasta + "\\conexao.xml");
			Conexao con = (Conexao) xstream.fromXML(in);
			System.out.println(con);
		} catch (FileNotFoundException e) {
			System.err.println("ERRO: "+e.getMessage());
		}
	}	
}
