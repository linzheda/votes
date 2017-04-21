package com.zd.biz;

import com.zd.bean.User;

public interface UserBiz {

	public int registerUser(User user) throws Exception;

	public User loginUser(User user) throws Exception;

}
