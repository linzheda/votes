package com.zd.biz;

import java.util.List;

import com.zd.bean.Subject;

public interface SubjectBiz {

	public List<Subject> findSubjectAll() throws Exception;

	public Subject findSubjectById(String subjectid) throws Exception;

	public void addSubject(String title, String type, String userid, String[] options) throws Exception;

	public void addOptions(String subid, String[] addoptions) throws Exception;

	public List<Subject> findSubjectByName(String keywords) throws Exception;

}
