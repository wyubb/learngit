package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IClassesDAO;
import domain.Classes;

//Classes类对应的DAO
public class ClassesDAO implements IClassesDAO{

	public ClassesDAO() {
		// TODO Auto-generated constructor stub
	}
	//往数据库中添加班级
	public void addClasses(Classes classes) {
		//初始化数据库访问类
		DatabaseDAO myDB = new DatabaseDAO();
		//构造SQL语句
		String sql = "insert into classes(classes_id,classes_name,classes_speciality,classes_teacher,grade) values('"+classes.getClasses_id()+"','"+classes.getClasses_name()+"','"+classes.getClasses_speciality()+"','"+classes.getClasses_teacher()+"','"+classes.getGrade()+"')";
	    //打印sql语句
		System.out.println("sql语句："+sql);
		
		try {
			myDB.executeSQL(sql);
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}		
	}
	
	/**
	 * 从数据库中获取所有信息
	 */
	public List<Classes> getAllClasses(){
		//初始化sql查询语句
		String sql = "select * from classes";
		//调用相关方法，并返回查询结果
		return getClassesBySqlString(sql);
	}
	
	/**
	 * 该方法通过执行一条查询的SQL语句来获取查询结果，并把查询结果封装成信息列表返回
	 * 
	 */
	public List<Classes> getClassesBySqlString(String sql){
		//初始化数据库访问类
		DatabaseDAO myDB = new DatabaseDAO();
		//定义信息列表，预备返回对象
		List<Classes> list = new ArrayList<Classes>();
		
		try {
			//执行sql查询语句，并获取查询结果集
			ResultSet mySet = myDB.getResultSet(sql);
			while(mySet.next()) {
				//定义班级类对象，用来封装查询数据
				Classes classes = new Classes();
				//设置班级类对象的classes_id字段值
				classes.setClasses_id(mySet.getString("classes_id"));
				//设置班级类对象的classes_name字段值
				classes.setClasses_name(mySet.getString("classes_name"));
				//设置班级类对象的classes_speciality字段值
				classes.setClasses_speciality(mySet.getString("classes_speciality"));
				//设置班级类对象的classes_teacher字段值
				classes.setClasses_teacher(mySet.getInt("classes_teacher"));
				//设置版积累对象的grade字段值
				classes.setGrade(mySet.getInt("grade"));
				//把班级类对象加入返回数据集中
				list.add(classes);
			}			
		}catch(ClassNotFoundException cnfEx){
			cnfEx.printStackTrace();
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}finally{			
			try{
				//释放数据库操作类的数据库连接对象
				myDB.releaseConnection();
			}catch(SQLException sqlEx){
				sqlEx.printStackTrace();
			}	
		}
		//返回结果
		return list;	
	}
}
