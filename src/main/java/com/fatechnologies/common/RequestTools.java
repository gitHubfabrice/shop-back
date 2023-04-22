package com.fatechnologies.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;

/**
 * @author ASSAGOU FABRICE 16/04/2023
 */
public class RequestTools {

  private final static JdbcTemplate jdbcTemplate = new JdbcTemplate(new DriverManagerDataSource("jdbc:postgresql://localhost:5433/eled", "fassagou", "Plcit102019"));
  private static final String GET_SIZE = "getSize";

  public static Integer generateId(String tableName){

    int id;
    Map<String, Object> firstRow;
    Map<String, Object> tail;
    var sql = "SELECT COUNT(1) AS getSize FROM " + tableName;

    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    firstRow = rows.get(0);

    if ((Long)firstRow.get(GET_SIZE) == 0)
      id = 1;
    else{
      var sql2 = "SELECT Max(reference) AS getSize FROM " + tableName;
      List<Map<String, Object>> tails = jdbcTemplate.queryForList(sql2);
      tail = tails.get(0);
      id = (Integer) tail.get(GET_SIZE) + 1;
    }
    return id;
  }
}
