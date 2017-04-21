package com.zd.biz;

import java.util.List;

import com.zd.bean.Item;
import com.zd.bean.Subject;
import com.zd.bean.User;

public interface ItemBiz {
	public void addItem(Item item)throws Exception;
	public List<Item> findItemBySubjectid(String string) throws Exception;
	public List<Integer> finOptionsCount(Subject sub) throws Exception;
}
