package com.zd.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zd.bean.Options;
import com.zd.bean.Subject;
import com.zd.biz.SubjectBiz;
import com.zd.dao.MyBatisHelper;

public class SubjectBizImpl implements SubjectBiz{

	@Override
	public List<Subject> findSubjectAll() throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		List<Subject> list=session.selectList("com.zd.mapper.SubjectMapper.selectSubjectAll");
		session.commit();
		return list;
	}

	@Override
	public Subject findSubjectById(String subjectid) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		Subject sub=session.selectOne("com.zd.mapper.SubjectMapper.selectSubjectOne",subjectid);
		session.commit();
		return sub;
	}

	@Override
	public void addSubject(String title, String type, String userid, String[] options) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		session.commit();
		try {
			Subject sub=new Subject();
			sub.setTitle(title);
			sub.setType(Integer.parseInt(type));
			sub.setUserid(userid);
			session.insert("com.zd.mapper.SubjectMapper.addSubject",sub);
			int subjectid=Integer.parseInt(sub.getId().toString());
			System.out.println("返回的id"+subjectid);
			System.out.println("userid:"+userid);
			List<Options> list=new ArrayList<Options>();
			Options op=null;
			for(int i=0;i<options.length;i++){
				op=new Options();
				op.setOrders(1);
				op.setName(options[i]);
				op.setSubjectid(subjectid);
				list.add(op);
			}
			session.insert("com.zd.mapper.OptionsMapper.addOptions",list);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addOptions(String subid, String[] addoptions) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		session.commit();
		List<Options> list=new ArrayList<Options>();
		Options op=null;
		for(int i=0;i<addoptions.length;i++){
			op=new Options();
			op.setOrders(1);
			op.setName(addoptions[i]);
			op.setSubjectid(Integer.parseInt(subid));
			list.add(op);
		}
		session.insert("com.zd.mapper.OptionsMapper.addOptions",list);
		session.commit();
	}

	@Override
	public List<Subject> findSubjectByName(String keywords) throws Exception {
		SqlSession session=MyBatisHelper.getSession();
		String words="%"+keywords+"%";
		List<Subject> list=session.selectList("com.zd.mapper.SubjectMapper.selectSubjectByName",words);
		session.commit();
		return list;
	}

	

}
