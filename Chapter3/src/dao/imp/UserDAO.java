package dao.imp;

import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IDatabaseDAO;
import dao.IUserDAO;

public class UserDAO implements IUserDAO{
	/**
	 * 默认构造函数
	 */	
	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 往数据库中添加用户
	 * @param user
	 */
	public void addUser(User user) {
		//初始化数据库访问类
		IDatabaseDAO myDB = new DatabaseDAO();
		//构造SQL语句
		String sql = "insert into users(Username,password,Realname,Gender,Age,PersonalSignature) values( '"+ user.getUserName() +"','"+ user.getPassword() +"','"+ user.getRealName() +"','"+ user.getGender()+"','"+ user.getAge()+"','"+ user.getPersonalSignature()+"')";
		try {
			//执行SQL语句
			myDB.executeSQL(sql);
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}			
	}
	
	/**
	 * 从数据库中查询id为参数值id的用户
	 * @param id
	 * @return 返回查询所得的用户	 
	 */
	public User getUserByID(String id) {
		//初始化数据库操作类
		IDatabaseDAO myDB = new DatabaseDAO();
		//初始化一个User对象，该对象最后会作为返回值
		User user = new User();
		//初始化SQL语句
		String sql = "select * from users where ID ='"+ id +"'";
		try {
			//执行SQL语句，并获取查询的结果集
			ResultSet mySet = myDB.getResultSet(sql);
			//循环结果集，并对user对象赋值
			while(mySet.next()) {
				//为user对象赋值
				user.setId(mySet.getString("Id"));
				user.setUserName(mySet.getString("UserName"));
				user.setPassword(mySet.getString("Password"));
				user.setRealName(mySet.getString("RealName"));
				user.setGender(mySet.getString("Gender"));
				user.setAge(mySet.getString("Age"));
				user.setPersonalSignature(mySet.getString("PersonalSignature"));
			}
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}finally {
			try {
				//结果集使用完毕，关闭数据集操作对象的数据库连接对象
				myDB.releaseConnection();
			}catch(SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		//返回获取的用户对象
		return user;
	}
	
	/**
	 * 根据userName查询数据库中的用户信息。在本项目中，userName具有唯一性，
	 * 因此，该函数的返回值类型可以是User.
	 * 返回List<User>的原因是以防万一。该方法的代码和方法getUserByID()基本相同
	 * 
	 */
	public List<User> getUserByUserName(String userName){
		IDatabaseDAO myDB = new DatabaseDAO();
		User user = new User();
		List<User> list = new ArrayList<User>();
		String sql = "select * from users where Username = '"+ userName +"'";
		try {
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next()) {
				user.setId(mySet.getString("Id"));
				user.setUserName(mySet.getString("UserName"));
				user.setPassword(mySet.getString("Password"));
				user.setRealName(mySet.getString("RealName"));
				user.setGender(mySet.getString("Gender"));
				user.setAge(mySet.getString("Age"));
				user.setPersonalSignature(mySet.getString("PersonalSignature"));
				list.add(user);
			}
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}finally {
			try {
				//结果集使用完毕，关闭数据集操作对象的数据库连接对象
				myDB.releaseConnection();
			}catch(SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		System.out.println(list.size());
		return list;
	}
	//更新user表信息
	public void updateUser(User user) {
		//初始化数据库访问类
		IDatabaseDAO myDB = new DatabaseDAO();
		//构造SQL语句
		String sql = "update users set RealName = '"+user.getRealName()+"',Gender = '"+user.getGender()+"',"
				+ "Age = '"+ user.getAge()+"' ,PersonalSignature = '"+ user.getPersonalSignature()+"',"
						+ " password = '"+ user.getPassword() +"'   where Username = '"+user.getUserName()+"'  ";
		System.out.println(sql);
		try {
			//执行SQL语句
			myDB.executeSQL(sql);
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}			
	}
	
	//删除用户
		public void deleteUser(String username) {
			//初始化数据库访问类
			IDatabaseDAO myDB = new DatabaseDAO();
			
			//构造SQL语句
			String sql = "delete from users where Username = '"+username+"'";
			try {
				//执行SQL语句
				myDB.executeSQL(sql);
			}catch(SQLException sqlEx){
				sqlEx.printStackTrace();
			}catch(ClassNotFoundException cnfEx) {
				cnfEx.printStackTrace();
			}		
		}
	
}
