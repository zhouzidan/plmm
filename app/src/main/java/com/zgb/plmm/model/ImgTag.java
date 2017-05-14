package com.zgb.plmm.model;

public class ImgTag {
	private int id ;
	private String tag ;
	private String url ;
	
	public ImgTag(int id, String tag, String url) {
		super();
		this.id = id;
		this.tag = tag;
		this.url = url;
	}
	public ImgTag(String tag, String url) {
		super();
		this.tag = tag;
		this.url = url;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
