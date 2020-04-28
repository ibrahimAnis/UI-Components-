package com.thinking.machines.uic.servlets;
import com.thinking.machines.uic.dl.*;
import java.sql.*;
import com.thinking.machines.uic.pojo.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetDepartmentsCount extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
ResponseWrapper responseWrapper;
PrintWriter pw=null;
int count=0;
try
{
pw=response.getWriter();
response.setContentType("application/json");
responseWrapper=new ResponseWrapper();
try
{
Connection connection=DAOConnection.getConnection();
System.out.println("Connection established..");
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select count(*) as cnt from department");
resultSet.next();
count=resultSet.getInt(1);
resultSet.close();
statement.close();
connection.close();
}catch(DAOException daoException)
{
System.out.println(daoException);
}
responseWrapper=new ResponseWrapper();
HashMap<String,Integer> map=new HashMap<>();
map.put("numberOfRecords",count);
responseWrapper.setResponse(map);
pw.print(responseWrapper);
}catch(Exception exception)
{
exception.printStackTrace();
responseWrapper=new ResponseWrapper();
responseWrapper.setException(exception.getMessage());
pw.print(responseWrapper);
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{

}
}