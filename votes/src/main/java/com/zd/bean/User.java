package com.zd.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	public static int USER_TYPE_ORDINARY=1;//普通用户
	public static int USER_TYPE_ADMIN=2;//管理员
	
	private String userId;//用户编号
	private String userName;//用户名
	private String password;//密码
	private int version=1;//用户是否有效
	private int status=1;//用户角色  1普通用户  2管理员
	public static int getUSER_TYPE_ORDINARY() {
		return USER_TYPE_ORDINARY;
	}
	public static void setUSER_TYPE_ORDINARY(int uSER_TYPE_ORDINARY) {
		USER_TYPE_ORDINARY = uSER_TYPE_ORDINARY;
	}
	public static int getUSER_TYPE_ADMIN() {
		return USER_TYPE_ADMIN;
	}
	public static void setUSER_TYPE_ADMIN(int uSER_TYPE_ADMIN) {
		USER_TYPE_ADMIN = uSER_TYPE_ADMIN;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", version=" + version
				+ ", status=" + status + "]";
	}
	
	

}
