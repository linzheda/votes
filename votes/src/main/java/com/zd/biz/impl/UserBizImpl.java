package com.zd.biz.impl;

import org.apache.ibatis.session.SqlSession;

import com.zd.bean.User;
import com.zd.biz.UserBiz;
import com.zd.dao.MyBatisHelper;

public class UserBizImpl implements UserBiz {

	@Override
	public int registerUser(User user) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		int result= session.insert("com.zd.mapper.UserMapper.registerUser",user);
		session.commit();
		return result;
	}

	@Override
	public User loginUser(User user) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		User u=session.selectOne("com.zd.mapper.UserMapper.loginUser",user);
		return u;
	}

}
