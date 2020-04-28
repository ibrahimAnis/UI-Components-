package com.thinking.machines.uic.pojo;
import java.io.*;
public class Department implements Serializable,Comparable<Department>
{
private int code;
private String name;
public Department()
{
this.code=0;
this.name="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public boolean equals(Object object)
{
if(!(object instanceof Department)) return false;
Department other=(Department)object;
return this.code==code;
}
public int compareTo(Department other)
{
return this.name.toUpperCase().compareTo(other.name.toUpperCase());
}
public int hashCode()
{
return this.code;
}
}