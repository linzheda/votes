package com.zd.test;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zd.bean.Item;
import com.zd.bean.Subject;
import com.zd.bean.User;
import com.zd.dao.MyBatisHelper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
   
    public AppTest( String testName )
    {
        super( testName );
    }

    
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    public void testApps()
    {
        assertTrue( true );
    }
    public void testApp() throws Exception
    {
    	User u=new User();
    	u.setUserId("lzd");
    	u.setPassword("a");
    	u.setUserName("林哲达");
    	u.setVersion(1);
    	u.setStatus(1);
    	SqlSession session=MyBatisHelper.getSession();
		List<Item>list=session.selectList("com.zd.mapper.ItemMapper.selectItemByUser");
		System.out.println(list);
    }
    public void testApp2() throws Exception
    {
    	SqlSession session=MyBatisHelper.getSession();
		List<Subject>list=session.selectList("com.zd.mapper.SubjectMapper.selectSubjectAll");
		System.out.println(list.get(0).getOptions().size());
		System.out.println(list);
    }
    
}
