package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IDatabaseDAO;
import dao.IStudentDAO;
import domain.Student;
import domain.User;

public class StudentDAO implements IStudentDAO {

	public StudentDAO() {
		// TODO Auto-generated constructor stub
	}

	//添加一个学生用户到数据库
	public void addStudent(Student student) {
		//初始化数据库访问类
				IDatabaseDAO myDB = new DatabaseDAO();
				//构造SQL语句
				String sql = "insert into student(student_id,student_name,student_sex,student_tel,student_class,student_password,grade) values "
						+ "('"+student.getStudent_id()+"','"+student.getStudent_name()+"','"+student.getStudent_sex()+"','"+student.getStudent_tel()+"',"
								+ "'"+student.getStudent_class()+"','"+student.getStudent_password()+"','"+student.getGrade()+"')";
				try {
					//执行SQL语句
					myDB.executeSQL(sql);
				}catch(SQLException sqlEx){
					sqlEx.printStackTrace();
				}catch(ClassNotFoundException cnfEx) {
					cnfEx.printStackTrace();
				}	
	}
	//根据用户编号获取用户信息，以Student对象的形式返回
	public Student getStudentByID(String id) {
				//初始化数据库操作类
				IDatabaseDAO myDB = new DatabaseDAO();
				//初始化一个Student对象，该对象最后会作为返回值
				Student student = new Student();
				//初始化SQL语句
				String sql = "select * from student where ID ='"+ id +"'";
				try {
					//执行SQL语句，并获取查询的结果集
					ResultSet mySet = myDB.getResultSet(sql);
					//循环结果集，并对user对象赋值
					while(mySet.next()) {
						//为student对象赋值
						student.setStudent_id(mySet.getString("student_id"));
						student.setStudent_name( mySet.getString("student_name"));
						student.setStudent_sex(mySet.getString("student_sex"));
						student.setStudent_tel(mySet.getString("student_tel"));
						student.setStudent_class(mySet.getString("student_class"));
						student.setStudent_password(mySet.getString("student_password"));
						student.setGrade(mySet.getInt("grade"));
						student.setGpa(mySet.getString("gpa"));
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
				//返回获取的学生用户对象
				return student;		
	}
	//根据用户名(学号)获取用户信息，以Student对象的形式返回
			public List<Student> getStudentByUserName(String userName){
				IDatabaseDAO myDB = new DatabaseDAO();
				Student student = new Student();
				List<Student> list = new ArrayList<Student>();
				String sql = "select * from student where student_id ='"+ userName +"'";
				try {
					ResultSet mySet = myDB.getResultSet(sql);
					while(mySet.next()) {
						student.setStudent_id(mySet.getString("student_id"));
						student.setStudent_name(mySet.getString("student_name"));
						student.setStudent_sex(mySet.getString("student_sex"));
						student.setStudent_tel(mySet.getString("student_tel"));
						student.setStudent_class(mySet.getString("student_class"));
						student.setStudent_password(mySet.getString("student_password"));
						student.setGrade(mySet.getInt("grade"));
						student.setGpa(mySet.getString("gpa"));
						list.add(student);
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
									
			public List<Student> getAllStudent(){
				//初始化SQL语句				
				//按绩点排序
				String sql = "select * from student order by gpa desc";
				System.out.println("绩点排序");
				//调用相关方法，并返回查询结果
				return getStudentBySqlString(sql);
			}
			//查询数据表某专业所有学生信息，按照绩点排序
			public List<Student> getSdeptStudent(String speciality){
				String sql = "select * from student,classes where student_class = classes_id  and classes.classes_speciality ='"+speciality+"' order by student.gpa desc";
				return getStudentBySqlString(sql);
			}
			
			//查询数据表里某班级所有学生信息，按照绩点排序
			public List<Student> getClassStudent(String student_class){				
				String sql = "select * from student where student_class  = '"+student_class+"' order by gpa desc";				
				return getStudentBySqlString(sql);
			}
				
			
			
			private List<Student> getStudentBySqlString(String sql) {	
				//初始化数据库访问类
				DatabaseDAO myDB = new DatabaseDAO();
				int i = 0;
				//定义学生列表，预备返回对象
				List<Student> list = new ArrayList<Student>();
				try {
					ResultSet mySet = myDB.getResultSet(sql);
					while(mySet.next()) {	
						i++;
						Student student = new Student();
						student.setStudent_id(mySet.getString("student_id"));
						student.setStudent_name(mySet.getString("student_name"));
						student.setStudent_sex(mySet.getString("student_sex"));
						student.setStudent_tel(mySet.getString("student_tel"));
						student.setStudent_class(mySet.getString("student_class"));
						student.setStudent_password(mySet.getString("student_password"));
						student.setGrade(mySet.getInt("grade"));
						student.setGpa(mySet.getString("gpa"));
						student.setClass_gpa(i);
						list.add(student);
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
			//根据学生学号查询绩点
			public Student getGpa(String student_id) {
				IDatabaseDAO myDB = new DatabaseDAO();
				Student student = new Student();
				String sql = "select * from gpa where student_id ="+student_id+"'";
				try {
					ResultSet gpa = myDB.getResultSet(sql);
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
				return student;
			}
			
			//更新学生用户信息
			public void updateStudent(Student student ) {
				//初始化数据库访问类
				IDatabaseDAO myDB = new DatabaseDAO();
				//构造SQL语句
				String sql = "update student set student_name = '"+student.getStudent_name()+"',"
						+ "student_tel = '"+student.getStudent_tel()+"',student_password = '"+student.getStudent_password()+"',"
						+ "student_sex = '"+student.getStudent_sex()+"',student_class = '"+student.getStudent_class()+"',"
								+ "grade = '"+student.getGrade()+"' where student_id ='"+student.getStudent_id()+"'";
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
				
			//删除学生用户
			public void deleteStudent(String student_id) {
				//初始化数据库访问类
				IDatabaseDAO myDB = new DatabaseDAO();
				
				//构造SQL语句
				String sql = "delete from student where student_id = '"+student_id+"'";
				try {
					//执行SQL语句
					myDB.executeSQL(sql);
				}catch(SQLException sqlEx){
					sqlEx.printStackTrace();
				}catch(ClassNotFoundException cnfEx) {
					cnfEx.printStackTrace();
				}		
			}
			//根据班级编号获取该班级所有学生的名单,根据学号排序
			public List<Student> getStuedntByClass(String student_class){
				IDatabaseDAO myDB = new DatabaseDAO();
				Student student = new Student();
				List<Student> list = new ArrayList<Student>();
				String sql = "select * from student where student_class ='"+ student_class +"' order by student_id";
				try {
					ResultSet mySet = myDB.getResultSet(sql);
					while(mySet.next()) {
						student.setStudent_id(mySet.getString("student_id"));
						student.setStudent_name(mySet.getString("student_name"));
						student.setStudent_sex(mySet.getString("student_sex"));
						student.setStudent_tel(mySet.getString("student_tel"));
						student.setStudent_class(mySet.getString("student_class"));
						student.setStudent_password(mySet.getString("student_password"));
						student.setGrade(mySet.getInt("grade"));
						student.setGpa(mySet.getString("gpa"));
						list.add(student);
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
			//根据专业编号获取该专业所有学生名单
			public List<Student> getStudentBySdept(String speciality){
				IDatabaseDAO myDB = new DatabaseDAO();
				Student student = new Student();
				List<Student> list = new ArrayList<Student>();
				String sql = "select * from student,classes where student.student_class = classes.classes.id where classes.speciality  ='"+speciality +"' order by student.student_id";
				try {
					ResultSet mySet = myDB.getResultSet(sql);
					while(mySet.next()) {
						student.setStudent_id(mySet.getString("student_id"));
						student.setStudent_name(mySet.getString("student_name"));
						student.setStudent_sex(mySet.getString("student_sex"));
						student.setStudent_tel(mySet.getString("student_tel"));
						student.setStudent_class(mySet.getString("student_class"));
						student.setStudent_password(mySet.getString("student_password"));
						student.setGrade(mySet.getInt("grade"));
						student.setGpa(mySet.getString("gpa"));
						list.add(student);
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
			
}
