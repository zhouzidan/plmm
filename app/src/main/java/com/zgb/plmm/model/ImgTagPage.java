package com.zgb.plmm.model;

public class ImgTagPage {
	private int id ;
	private int tagId;
	private String url ;
	
	public ImgTagPage(int tagId, String url) {
		super();
		this.tagId = tagId;
		this.url = url;
	}

	public ImgTagPage(int id, int tagId, String url) {
		super();
		this.id = id;
		this.tagId = tagId;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	
	
}
