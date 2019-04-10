package com.power.using.commons;

import java.util.List;

public class Page {
	private List records;
	private int pageNum;
	private int totalPageNum;
	private int prepageNum;
	private int nextPageNum;
	
	private int pageSize=3;
	private int totalRecordsNum;
	private int startIndex;
	private String url;
	
	
	
	
	
	
	public Page(int pageNum, int totalRecordsNum) {
		super();
		this.pageNum = pageNum;
		this.totalRecordsNum = totalRecordsNum;
		
		totalPageNum=(totalRecordsNum%pageSize==0?totalRecordsNum/pageSize:totalRecordsNum/pageSize+1);
		startIndex=(pageNum-1)*pageSize;
	}
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getPrepageNum() {
		prepageNum=pageNum-1;
		if(prepageNum<1){
			prepageNum=1;
		}
		return prepageNum;
	}
	public void setPrepageNum(int prepageNum) {
		this.prepageNum = prepageNum;
	}
	public int getNextPageNum() {
		nextPageNum=pageNum+1;
		if(nextPageNum>totalPageNum){
			nextPageNum=totalPageNum;
		}
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}
	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
