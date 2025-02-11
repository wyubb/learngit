package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.IInfoDAO;
import domain.*;

/**
 * Information类对应的DAO 
 */
public class InfoDAO  implements IInfoDAO {
	/**
	 * 默认构造函数
	 */
	public InfoDAO() {
		
	}

	/**
	 * 往数据库中添加信息
	 */
	public void addInfo(Information information) {
		
		//初始化数据库访问类
		DatabaseDAO myDB = new DatabaseDAO();
		//构造SQL语句
		String sql = "insert into information"+
"(Title,Content,Publishinguser) values('"
		+information.getTitle()+"','"+information.getContent()+"','"+information.getPublishiuser()+"')";
		
		System.out.println("sql语句  "+sql);
		try
		{
			//执行SQL语句
			myDB.executeSQL(sql);
		}
		//处理SQL语句执行过程中可能产生的异常
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
		catch(ClassNotFoundException cnfEx)
		{
			cnfEx.printStackTrace();
		}
	}

	/**
	 * 从数据库中获得所有信息
	 */
	public List<Information> getAllInfo() {
		
		//初始化sql查询语句
		String sql = "select * from information";
		//调用相关方法，并返回查询结果
		return getInfoBySqlString(sql);
	}
	
	
	
	/**
	 * 该方法通过执行一条查询的SQL语句来获取查询结果，并把查询结果封装成信息列表返
	 * 
	 * @param sql
	 * @return 
	 */
	public List<Information> getInfoBySqlString(String sql){
		//初始化数据库访问类
		DatabaseDAO myDB = new DatabaseDAO();
		//定义信息列表，预备返回对象
		List<Information> list = new ArrayList<Information>();
		try{
			//执行sql查询语句，并获取查询结果集
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next())
			{
				//定义信息类对象，用来封装查询数据
				Information information = new Information();
				//设置信息类对象的Id字段值
				information.setId(mySet.getInt("Id"));
				//设置信息类对象的标题字段值
				information.setTitle(mySet.getString("Title"));
				//设置信息类对象的信息内容字段值
				information.setContent(mySet.getString("Content"));
				//设置信息类对象的发布时间字段值
				
				information.setPublishingtime(mySet.getString("PublishingTime"));
				//定义一个用户类对象，该对象用来为信息类对象的用户字段赋值
				
				//设置信息类对象的用户字段值
				information.setPublishiuser(mySet.getString("PublishingUser"));
				
				//把信息类对象加入返回数据集中
				list.add(information);
			}
		}
		//处理异常
		catch(ClassNotFoundException cnfEx)
		{
			cnfEx.printStackTrace();
		}
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
		finally
		{
			try
			{
				//释放数据库操作类的数据库连接对象
				myDB.releaseConnection();
			}
			catch(SQLException sqlEx)
			{
				sqlEx.printStackTrace();
			}	
		}
		//返回结果
		return list;
	}
}
