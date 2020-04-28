package com.thinking.machines.uic.servlets;
import com.thinking.machines.uic.dl.*;
import java.sql.*;
import com.thinking.machines.uic.pojo.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetDepartmentsThatStartWith extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
ResponseWrapper responseWrapper;
PrintWriter pw=null;
try
{
System.out.println("Chala");
InputStream is=request.getInputStream();
InputStreamReader isr=new InputStreamReader(is);
StringBuilder sb=new StringBuilder();
int x;
while(true)
{
x=isr.read();
if(x==-1) break;
sb.append((char)x);
}
String jsonString=sb.toString();
System.out.println(jsonString);
Gson gson=new Gson();
SearchQuery searchQuery=gson.fromJson(jsonString,SearchQuery.class);
List<String> departments=new LinkedList<>();
String prefix=searchQuery.getPrefix();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select name from department where upper(name) like ? order by name");
preparedStatement.setString(1,prefix.toUpperCase()+"%");
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
System.out.println(resultSet.getString("name").trim());
departments.add(resultSet.getString("name").trim());
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(DAOException daoException)
{
System.out.println(daoException);
}
pw=response.getWriter();
response.setContentType("application/json");
responseWrapper=new ResponseWrapper();
responseWrapper.setResponse(departments);
pw.print(responseWrapper);
}catch(Exception exception)
{
System.out.println(exception);
responseWrapper=new ResponseWrapper();
responseWrapper.setException(exception.getMessage());
pw.print(responseWrapper);
}
}
}