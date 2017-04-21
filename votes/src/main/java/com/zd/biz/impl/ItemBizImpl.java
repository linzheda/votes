package com.zd.biz.impl;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.zd.bean.Item;
import com.zd.bean.Options;
import com.zd.bean.Subject;
import com.zd.biz.ItemBiz;
import com.zd.dao.MyBatisHelper;

public class ItemBizImpl implements ItemBiz{


	@Override
	public void addItem(Item item) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		session.insert("com.zd.mapper.ItemMapper.addItem",item);
		session.commit();
	}

	@Override
	public List<Item> findItemBySubjectid(String string) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		List<Item> item=session.selectList("com.zd.mapper.ItemMapper.findItem",string);
		session.commit();
		return item;
	}

	@Override
	public List<Integer> finOptionsCount(Subject sub) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		List<Options> opttions=sub.getOptions();
		List<Integer> list=session.selectList("com.zd.mapper.ItemMapper.finOptionsCount",opttions);
		session.commit();
		return list;
	}


}
