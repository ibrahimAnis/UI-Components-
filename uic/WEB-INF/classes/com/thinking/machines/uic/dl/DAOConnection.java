package com.thinking.machines.uic.dl;
import java.sql.*;
public class DAOConnection
{
public static Connection getConnection() throws DAOException
{
Connection connection=null;
try
{
Class.forName("com.mysql.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/stage3db","root","ibrahimAnis@001");
}catch(Throwable e)
{
System.out.println("Exception e"+e);
System.out.println(e.getMessage());
}
return connection;
}
}