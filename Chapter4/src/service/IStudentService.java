package service;

import java.util.List;

import domain.Student;

public interface IStudentService {
	//添加学生信息到数据库
	public void addStudent(Student student) ;
	//根据学生编号获取学生信息
	public Student getStudentByID(String student_id);
	//验证学生用户登录名和密码是否合法
	public Student validateStudent(String student_id,String student_password);
	//验证学生用户名是否已经存在
	public boolean isStudentExist(String student_id);
	//根据学生学号获取绩点
	public Student getGpa(String student_id);
	//获取所有学生列表
	public List<Student> getAllStudent();
}
