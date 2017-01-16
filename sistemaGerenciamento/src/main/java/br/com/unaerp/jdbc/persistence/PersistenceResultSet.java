package br.com.unaerp.jdbc.persistence;

import br.com.unaerp.jdbc.anotation.Boolean;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Image;
import br.com.unaerp.jdbc.anotation.Tabela;
import br.com.unaerp.jdbc.anotation.Time;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class PersistenceResultSet<T>
{
  public List<T> mapRersultSetToObject(ResultSet rs, Class outputClass)
  {
    List<T> outputList = new ArrayList();
    try
    {
      if (rs != null)
      {
        if (outputClass.isAnnotationPresent(Tabela.class))
        {
          ResultSetMetaData rsmd = rs.getMetaData();
          Field[] fields = outputClass.getDeclaredFields();
          while (rs.next())
          {
            T bean = (T) outputClass.newInstance();
            for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++)
            {
              String columnName = rsmd.getColumnName(_iterator + 1);
              Object columnValue = rs.getObject(_iterator + 1);
              for (Field field : fields) {
                if (field.isAnnotationPresent(Coluna.class))
                {
                  Coluna column = (Coluna)field.getAnnotation(Coluna.class);
                  if ((column.nome().equalsIgnoreCase(columnName)) && (columnValue != null))
                  {
                    if (field.isAnnotationPresent(br.com.unaerp.jdbc.anotation.Date.class))
                    {
                      setPropertyDate(bean, field.getName(), columnValue); break;
                    }
                    if (field.isAnnotationPresent(DateTime.class))
                    {
                      setPropertyDateTime(bean, field.getName(), columnValue); break;
                    }
                    if (field.isAnnotationPresent(Time.class))
                    {
                    	setPropertyTime(bean, field.getName(), columnValue); break;
                    }
                    if(field.isAnnotationPresent(Boolean.class))
                    {
                    	setPropertyBoolean(bean, field.getName(), columnValue);break;
                    }
                    if (field.isAnnotationPresent(Image.class))
                    {
                      setPropertyImage(bean, field.getName(), columnValue); break;
                    }
                    setProperty(bean, field.getName(), columnValue);
                    
                    break;
                  }
                }
              }
            }
            outputList.add(bean);
          }
        }
        else
        {
          System.err.println("Persistence ERRO: Classe modelo n�o contem a anota��o Tabela");
          return outputList;
        }
      }
      else
      {
        System.err.println("Persistence ERRO: ResultSet NULL");
        return outputList;
      }
    }
    catch (IllegalAccessException|SQLException|InstantiationException e)
    {
      System.err.println(e.getMessage());
    }
    return outputList;
  }
  
  private void setPropertyBoolean(Object clazz, String fieldName, Object columnValue) {
	// TODO Auto-generated method stub
	  int valor = ((boolean) columnValue ? 1 : 0 );
	  setProperty(clazz, fieldName, columnValue);
	
  }

private void setPropertyDateTime(Object clazz, String fieldName, Object columnValue)
  {
    Timestamp data = (Timestamp)columnValue;
    LocalDateTime localDateTime = PersistenceConvert.SQLDateTimeToLocalDateTime(data);
    setProperty(clazz, fieldName, localDateTime);
  }
private void setPropertyTime(Object clazz, String fieldName, Object columnValue)
{
  java.sql.Time data = (java.sql.Time)columnValue;
  LocalTime localDateTime = PersistenceConvert.SQLDateTimeToLocalTime(data);
  setProperty(clazz, fieldName, localDateTime);
}
  private void setPropertyDate(Object clazz, String fieldName, Object columnValue)
  {
    java.sql.Date data = (java.sql.Date)columnValue;
    LocalDate localDate = PersistenceConvert.SQLDateToLocalDate(data);
    setProperty(clazz, fieldName, localDate);
  }
  
	private void setPropertyImage(Object clazz, String fieldName, Object columnValue)
  {
    byte[] img = (byte[])columnValue;
    BufferedImage buffer = PersistenceConvert.InputStreamToBufferedImage(img);
    setProperty(clazz, fieldName, buffer);
  }
  
  private void setProperty(Object clazz, String fieldName, Object columnValue)
  {
    try
    {
      Field field = clazz.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(clazz, columnValue);
    }
    catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e)
    {
      System.err.println(e.getMessage());
    }
  }
}
