package br.com.sistemaGerenciamento.conexao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.liape.sistemaGerenciamento.model.Conexao;

public class ConexaoXML {

	private static Conexao conn = null;

	public static Conexao getInstance() {
		if (conn == null) {
			instanciarXMLConexao();
		}
		return conn;
	}

	private static void instanciarXMLConexao() {
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("conexao", Conexao.class);
			String pasta = System.getenv("SYSLIAPE");
			InputStream in = new FileInputStream(pasta + "\\conexao.xml");
			conn = (Conexao) xstream.fromXML(in);
		} catch (FileNotFoundException e) {
			System.err.println("ERRO: " + e.getMessage());
		}

	}

}
