package com.thinking.machines.uic.servlets;
import com.thinking.machines.uic.dl.*;
import java.sql.*;
import com.thinking.machines.uic.pojo.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetPageByDepartment extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
ResponseWrapper responseWrapper;
PrintWriter pw=null;
try
{
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
Thread.sleep(1000);
String jsonString=sb.toString();
Gson gson=new Gson();
NameQuery nameQuery=gson.fromJson(jsonString,NameQuery.class);
Department department;
String name=nameQuery.getName();
System.out.println(name);
int pageSize=nameQuery.getPageSize();
int pageNumber=0;
int count=0;
int index=0;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement ps=connection.prepareStatement("select name from department order by name");
ResultSet rs=ps.executeQuery();
boolean found=false;
while(rs.next())
{
if(name.equals(rs.getString("name").trim()))
{
found=true;
break;
}
System.out.println(rs.getString("name").trim());
count++;
}
if(!found) throw new DAOException(name+" doesn't exist");
pageNumber=(count/pageSize)+1;
index=(count)%pageSize;

rs.close();
ps.close();
connection.close();
}catch(DAOException dao)
{
System.out.println(dao);
}
// Assume that I am extracting records from database
pw=response.getWriter();
response.setContentType("application/json");
responseWrapper=new ResponseWrapper();
HashMap<String,Integer> map=new HashMap<>();
map.put("pageNumber",pageNumber);
map.put("index",index);
responseWrapper.setResponse(map);
pw.print(responseWrapper);
}catch(Exception exception)
{
responseWrapper=new ResponseWrapper();
responseWrapper.setException(exception.getMessage());
pw.print(responseWrapper);
}
}
}