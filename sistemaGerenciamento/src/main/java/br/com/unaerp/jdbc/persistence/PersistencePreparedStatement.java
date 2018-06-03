package br.com.unaerp.jdbc.persistence;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

class PersistencePreparedStatement
{
  public static PreparedStatement listar(Connection conn, Class clazz)
    throws SQLException
  {
    String nomeTabela = getNomeTabela(clazz);
    if (!nomeTabela.isEmpty())
    {
      StringBuilder SQL = new StringBuilder();
      SQL.append("SELECT * FROM ");
      SQL.append(nomeTabela);
      PreparedStatement stmt = conn.prepareStatement(SQL.toString());
      return stmt;
    }
    System.err.println("Persistence ERRO: Classe modelo n�o contem a anota��o Tabela");
    return null;
  }
  
  public static PreparedStatement deletarTodos(Connection conn, Class clazz)
    throws SQLException
  {
    String nomeTabela = getNomeTabela(clazz);
    if (!nomeTabela.isEmpty())
    {
      StringBuilder SQL = new StringBuilder();
      SQL.append("DELETE FROM ");
      SQL.append(nomeTabela);
      PreparedStatement stmt = conn.prepareStatement(SQL.toString());
      return stmt;
    }
    System.err.println("Persistence ERRO: Classe modelo n�o contem a anota��o Tabela");
    return null;
  }
  
  private static String getNomeTabela(Class clazz)
  {
    String retorno = "";
    if (clazz.isAnnotationPresent(Tabela.class))
    {
      Tabela tabela = (Tabela)clazz.getAnnotation(Tabela.class);
      retorno = tabela.nome();
    }
    return retorno;
  }
  
  public static PreparedStatement atualizar(Object obj, Connection conn)
    throws SQLException, IllegalArgumentException, IllegalAccessException
  {
    StringBuilder SQL_UPDATE = new StringBuilder();
    StringBuilder SQL_COLUMN = new StringBuilder();
    StringBuilder SQL_WHERE = new StringBuilder();
    String nomeTabela = getNomeTabela(obj.getClass());
    List<Object> listaParam = new ArrayList();
    List<Object> listaWhere = new ArrayList();
    if (!nomeTabela.isEmpty())
    {
      SQL_UPDATE.append("UPDATE ");
      SQL_UPDATE.append(nomeTabela);
      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field field : fields) {
        if (field.isAnnotationPresent(Coluna.class))
        {
          if (field.isAnnotationPresent(Chave.class))
          {
            Coluna column = (Coluna)field.getAnnotation(Coluna.class);
            if (SQL_WHERE.toString().isEmpty())
            {
              SQL_WHERE.append("WHERE ");
              SQL_WHERE.append(column.nome());
              SQL_WHERE.append(" =?");
            }
            else
            {
              SQL_WHERE.append(" AND ");
              SQL_WHERE.append(column.nome());
              SQL_WHERE.append(" =?");
            }
            listaWhere.add(getObject(field, obj));
          }else{
        	  Coluna column = (Coluna)field.getAnnotation(Coluna.class);
        	  if (SQL_COLUMN.toString().isEmpty())
        	  {
        		  SQL_COLUMN.append("SET ");
        		  SQL_COLUMN.append(column.nome());
        		  SQL_COLUMN.append(" =?");
        	  }
        	  else
        	  {
        		  SQL_COLUMN.append(",");
        		  SQL_COLUMN.append(column.nome());
        		  SQL_COLUMN.append(" =?");
        	  }
        	  listaParam.add(getObject(field, obj));
          }
        }
      }
      StringBuilder SQL = new StringBuilder();
      SQL.append(SQL_UPDATE);
      SQL.append(" ");
      SQL.append(SQL_COLUMN);
      SQL.append(" ");
      SQL.append(SQL_WHERE);
      
      PreparedStatement stmt = conn.prepareStatement(SQL.toString());
      int posicao = 1;
      for (Object o : listaParam) {
        inserirObjetoParametros(stmt, o, posicao++);
      }
      for (Object o : listaWhere) {
        inserirObjetoParametros(stmt, o, posicao++);
      }
      return stmt;
    }
    System.err.println("Persistence ERRO: Classe modelo n�o contem a anota��o Tabela");
    return null;
  }
  
  public static PreparedStatement inserir(Object obj, Connection conn)
    throws SQLException, IllegalArgumentException, IllegalAccessException
  {
    StringBuilder SQL_INSERT = new StringBuilder();
    StringBuilder SQL_COLUMN = new StringBuilder();
    StringBuilder SQL_VALUES = new StringBuilder();
    String nomeTabela = getNomeTabela(obj.getClass());
    List<Object> listaObjetos = new ArrayList();
    if (!nomeTabela.isEmpty())
    {
      SQL_INSERT.append("INSERT INTO ");
      SQL_INSERT.append(nomeTabela);
      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field field : fields) {
        if ((field.isAnnotationPresent(Coluna.class)) && (!field.isAnnotationPresent(AutoIncrement.class)))
        {
          Coluna column = (Coluna)field.getAnnotation(Coluna.class);
          if (SQL_COLUMN.toString().isEmpty())
          {
            SQL_COLUMN.append("(");
            SQL_COLUMN.append(column.nome());
          }
          else
          {
            SQL_COLUMN.append(",");
            SQL_COLUMN.append(column.nome());
          }
          if (SQL_VALUES.toString().isEmpty()) {
            SQL_VALUES.append("VALUES (?");
          } else {
            SQL_VALUES.append(",?");
          }
          listaObjetos.add(getObject(field, obj));
        }
      }
      SQL_COLUMN.append(")");
      SQL_VALUES.append(")");
      StringBuilder SQL_CONSULTA = new StringBuilder();
      SQL_CONSULTA.append(SQL_INSERT);
      SQL_CONSULTA.append("");
      SQL_CONSULTA.append(SQL_COLUMN);
      SQL_CONSULTA.append("");
      SQL_CONSULTA.append(SQL_VALUES);
      
      PreparedStatement stmt = conn.prepareStatement(SQL_CONSULTA.toString());
      int posicao = 1;
      for (Object o : listaObjetos) {
        inserirObjetoParametros(stmt, o, posicao++);
      }
      return stmt;
    }
    System.err.println("Persistence ERRO: Classe modelo n�o contem a anota��o Tabela");
    return null;
  }
  
  public static PreparedStatement isExiste(Object obj, Connection conn)
    throws SQLException, IllegalArgumentException, IllegalAccessException
  {
    StringBuilder SQL_CONSULTA = new StringBuilder();
    StringBuilder SQL_WHERE = new StringBuilder();
    String nomeTabela = getNomeTabela(obj.getClass());
    List<Object> listaObjetos = new ArrayList();
    if (!nomeTabela.isEmpty())
    {
      SQL_CONSULTA.append("SELECT * FROM ");
      SQL_CONSULTA.append(nomeTabela);
      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field field : fields) {
        if ((field.isAnnotationPresent(Chave.class)) && (field.isAnnotationPresent(Coluna.class)))
        {
          Coluna column = (Coluna)field.getAnnotation(Coluna.class);
          if (SQL_WHERE.toString().isEmpty())
          {
            SQL_WHERE.append(" WHERE ");
            SQL_WHERE.append(column.nome());
            SQL_WHERE.append(" = ?");
          }
          else
          {
            SQL_WHERE.append(" AND ");
            SQL_WHERE.append(column.nome());
            SQL_WHERE.append(" = ?");
          }
          listaObjetos.add(getObject(field, obj));
        }
      }
      StringBuilder SQL = new StringBuilder();
      SQL.append(SQL_CONSULTA);
      SQL.append(SQL_WHERE);
      PreparedStatement stmt = conn.prepareStatement(SQL.toString());
      int posicao = 1;
      for (Object o : listaObjetos) {
        inserirObjetoParametros(stmt, o, posicao++);
      }
      return stmt;
    }
    System.err.println("Persistence ERRO: Classe modelo n�o contem a anota��o Tabela");
    return null;
  }
  
  private static Object getObject(Field field, Object obj)
    throws IllegalArgumentException, IllegalAccessException
  {
    field.setAccessible(true);
    return field.get(obj);
  }
  
  public static void inserirObjetoParametros(PreparedStatement stmt, Object obj, int posicao)
    throws SQLException
  {
    boolean isConvert = false;
    if ((obj instanceof LocalDate))
    {
      stmt.setObject(posicao, PersistenceConvert.LocalDateToSQLDate((LocalDate)obj));
      isConvert = true;
    }
    if ((obj instanceof LocalDateTime))
    {
      stmt.setObject(posicao, PersistenceConvert.LocalDateTimeToSQLDateTime((LocalDateTime)obj));
      isConvert = true;
    }
    
    if ((obj instanceof LocalTime))
    {
      stmt.setObject(posicao, PersistenceConvert.LocalTimeToSQLTime((LocalTime)obj));
      isConvert = true;
    }
    
    if ((obj instanceof BufferedImage))
    {
      stmt.setObject(posicao, PersistenceConvert.BufferedImageToInputStream((BufferedImage)obj));
      isConvert = true;
    }
    if (!isConvert) {
      stmt.setObject(posicao, obj);
    }
  }
}
