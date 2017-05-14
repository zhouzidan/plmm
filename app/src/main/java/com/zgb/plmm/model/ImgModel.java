package com.zgb.plmm.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 图片的model
 *
 * @author zhou
 */
public class ImgModel implements Serializable{
    private int id;
    private String url;
    private int star = 0;
    private Date createTime;
    private Date updateTime;
    private int failureNum = 0;
    private ImgGroup imgGroup = null;

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

    public ImgGroup getImgGroup() {
        return imgGroup;
    }

    public void setImgGroup(ImgGroup imgGroup) {
        this.imgGroup = imgGroup;
    }

    public int getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(int failureNum) {
        this.failureNum = failureNum;
    }


}
