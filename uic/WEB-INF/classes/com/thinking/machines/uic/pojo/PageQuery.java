package com.thinking.machines.uic.pojo;
public class PageQuery
{
private int pageSize;
private int pageNumber;
private int firstPageNumber;
private int lastPageNumber;
public void setFirstPageNumber(int firstPageNumber)
{
this.firstPageNumber=firstPageNumber;
}
public int getFirstPageNumber()
{
return this.firstPageNumber;
}
public void setLastPageNumber(int lastPageNumber)
{
this.lastPageNumber=lastPageNumber;
}
public int getLastPageNumber()
{
return this.lastPageNumber;
}

public void setPageSize(int pageSize)
{
this.pageSize=pageSize;
}
public int getPageSize()
{
return this.pageSize;
}
public void setPageNumber(int pageNumber)
{
this.pageNumber=pageNumber;
}
public int getPageNumber()
{
return this.pageNumber;
}

}