package br.com.liape.sistemaGerenciamento.dao;

public class GerDAO {

	private GerDAO dao;
	
	public GerDAO getInstance(){
		if(dao == null){
			dao = new GerDAO();
		}
		return dao;
	}
	
}
