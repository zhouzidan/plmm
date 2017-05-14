package com.zgb.plmm.model;

import java.sql.Date;

/**
 * 图片的model
 * @author zhou
 *
 */
public class ImgModel {
	private int id ;
	private String url ;
	private int star = 0 ;
	private Date createTime;
	private Date updateTime ;
	private int groupId ;
	private int failureNum = 0 ;
	
	public ImgModel(String url , int groupId){
		this.groupId = groupId;
		this.url = url;
	}

	public ImgModel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getFailureNum() {
		return failureNum;
	}

	public void setFailureNum(int failureNum) {
		this.failureNum = failureNum;
	}
	
	
}
