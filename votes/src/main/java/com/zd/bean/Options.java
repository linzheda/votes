package com.zd.bean;

import java.io.Serializable;

public class Options implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//编号
	private String name;//选项名
	private int orders;//显示顺序
	private int subjectid;//主题id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	@Override
	public String toString() {
		return "Options [id=" + id + ", name=" + name + ", orders=" + orders + ", subjectid=" + subjectid + "]";
	}
	
	
	
	
	
	
}
