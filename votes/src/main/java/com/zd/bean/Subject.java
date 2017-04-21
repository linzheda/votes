package com.zd.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *主题类
 * @author Administrator
 *
 */
public class Subject implements Serializable{
	private static final long serialVersionUID = 1L;
	private static int TYPE_SINGLE=1;
	private static int TYPE_MULTIPLE=2;
	
	
	private Long id;//编号
	private String title;//标题
	private int type=1;//类型
	private int version;//是否有效
	private String userid;//属于那个用户发布的
	private Integer usercount;
	
	private List<Options> options=new ArrayList<Options>();//选项

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	
	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public Integer getUsercount() {
		return usercount;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + ", type=" + type + ", version=" + version + ", userid="
				+ userid + ", usercount=" + usercount + ", options=" + options + "]";
	}

	
	
	
	
	
}
