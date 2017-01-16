package br.com.unaerp.jdbc.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class PersistenceSQLFacate
{
  public int getInt(ResultSet rs, String coluna)
    throws SQLException
  {
    return rs.getInt(coluna);
  }
  
  public int getInt(ResultSet rs, int index)
    throws SQLException
  {
    return rs.getInt(index);
  }
  
  public long getLong(ResultSet rs, String coluna)
    throws SQLException
  {
    return rs.getLong(coluna);
  }
  
  public long getLong(ResultSet rs, int index)
    throws SQLException
  {
    return rs.getLong(index);
  }
  
  public double getDouble(ResultSet rs, String coluna)
    throws SQLException
  {
    return rs.getDouble(coluna);
  }
  
  public double getDouble(ResultSet rs, int index)
    throws SQLException
  {
    return rs.getDouble(index);
  }
  
  public String getString(ResultSet rs, String coluna)
    throws SQLException
  {
    return rs.getString(coluna);
  }
  
  public String getString(ResultSet rs, int index)
    throws SQLException
  {
    return rs.getString(index);
  }
  
  public Object getObject(ResultSet rs, String coluna)
    throws SQLException
  {
    return rs.getObject(coluna);
  }
  
  public Object getObject(ResultSet rs, int index)
    throws SQLException
  {
    return rs.getObject(index);
  }
  
  public LocalDate getLocalDate(ResultSet rs, String coluna)
    throws SQLException
  {
    return PersistenceConvert.SQLDateToLocalDate(rs.getDate(coluna));
  }
  
  public LocalDate getLocalDate(ResultSet rs, int index)
    throws SQLException
  {
    return PersistenceConvert.SQLDateToLocalDate(rs.getDate(index));
  }
  
  public LocalDateTime getLocalDateTime(ResultSet rs, String coluna)
    throws SQLException
  {
    return PersistenceConvert.SQLDateTimeToLocalDateTime(rs.getTimestamp(coluna));
  }
  
  public LocalDateTime getLocalDateTime(ResultSet rs, int index)
    throws SQLException
  {
    return PersistenceConvert.SQLDateTimeToLocalDateTime(rs.getTimestamp(index));
  }
  
}
