package com.thinking.machines.uic.servlets;
import com.thinking.machines.uic.dl.*;
import java.sql.*;
import com.thinking.machines.uic.pojo.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetDepartmentsByPage extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
ResponseWrapper responseWrapper=null;
Vector<Department> departments=null;
Department department=null;
PrintWriter pw=null;
HttpSession session=request.getSession(false);
PageQuery pageQuery=null;
Connection connection=null;
Statement statement=null;
ResultSet resultSet=null;
ResultSet rs=null;
Gson gson=null;
PreparedStatement ps=null;
int pageSize=0;
int pageNumber=0;
int totalCount=0;
int count=0;
int i=0;
boolean found=false;
try
{
response.setContentType("application/json");
pw=response.getWriter();
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
gson=new Gson();
pageQuery=gson.fromJson(jsonString,PageQuery.class);
pageSize=pageQuery.getPageSize();
pageNumber=pageQuery.getPageNumber();
departments=new Vector<>();
int from=(pageSize*(pageNumber-1));
System.out.println("from:"+from);
try
{
connection=DAOConnection.getConnection();
ps=connection.prepareStatement("select * from department order by name LIMIT ?,?");
ps.setInt(1,from);
ps.setInt(2,pageSize);
rs=ps.executeQuery();
count=0;
while(rs.next())
{
department=new Department();
department.setCode(rs.getInt("code"));
department.setName(rs.getString("name").trim());
departments.add(department);
count++;
}
rs.close();
ps.close();
connection.close();
}catch(DAOException dao)
{
System.out.println(dao);
}
responseWrapper=new ResponseWrapper();
responseWrapper.setResponse(departments);
pw.print(responseWrapper);
}
catch(Exception exception)
{
exception.printStackTrace();
responseWrapper=new ResponseWrapper();
responseWrapper.setException(exception.getMessage());
pw.print(responseWrapper);
}
System.out.println("------------END-----------");
}
}
