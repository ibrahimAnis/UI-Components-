package com.thinking.machines.uic.pojo;
public class NameQuery
{
private String name;
private int pageSize;
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setPageSize(int pageSize)
{
this.pageSize=pageSize;
}
public int getPageSize()
{
return this.pageSize;
}
}