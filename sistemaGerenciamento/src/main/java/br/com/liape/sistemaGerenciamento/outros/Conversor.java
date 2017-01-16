package br.com.liape.sistemaGerenciamento.outros;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.joda.time.LocalDate;

/*
 * ESTA CLASSE É RESPONSÁVEL POR CONVERSÕES BÁSICAS ANTES DE SEREM ENVIADAS PARA O BANCO DE DADOS
 */
public class Conversor {
	/*
	 * CONVERSÃO DE STRING PARA LOCALDATE
	 */
	public static java.time.LocalDate converterLocalDate(String data)
	{
		LocalDate joda = new LocalDate(data);
		return converterLocaDateJoda(joda);
	}
	public static java.time.LocalDate converterLocaDateJoda(LocalDate joda)
	{
		return java.time.LocalDate.of(joda.getYear(), joda.getMonthOfYear(), joda.getDayOfMonth());
	}
	public static java.time.LocalTime converterLocalTime(String horario)
	{
		return java.time.LocalTime.parse(horario);
	}
	public static LocalDate converterJodaTime(java.time.LocalDate data)
	{
		return new LocalDate(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
	}
	public static Timestamp converterLocalDateTimeParaTimeStamp(LocalDateTime ldt)
	{
		return Timestamp.valueOf(ldt);
	}
}
