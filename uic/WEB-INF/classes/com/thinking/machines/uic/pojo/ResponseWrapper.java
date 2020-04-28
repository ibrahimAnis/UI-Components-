package com.thinking.machines.uic.pojo;
import com.google.gson.*;
import java.io.*;
public class ResponseWrapper implements Serializable
{
private Object response;
private boolean success;
private Object exception;
public ResponseWrapper()
{
this.response="";
this.success=true;
this.exception="";

}
public void setResponse(Object object)
{
this.response=object;
this.success=true;
this.exception="";
}
public void setException(String exception)
{
this.response="";
this.exception=exception;
this.success=false;
}
public String toString()
{
Gson gson=new Gson();
return gson.toJson(this);
}
}