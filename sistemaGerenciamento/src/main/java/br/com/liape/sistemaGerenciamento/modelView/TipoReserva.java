package br.com.liape.sistemaGerenciamento.modelView;

public class TipoReserva {
	private String horaInicio;
	private String horaFim;
	private String observacao;
	private String dataMarcada;
	private String diaSemana;
	private int idSala;
	
	

	public int getIdSala() {
		return idSala;
	}
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDataMarcada() {
		return dataMarcada;
	}
	public void setDataMarcada(String dataMarcada) {
		this.dataMarcada = dataMarcada;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
}
