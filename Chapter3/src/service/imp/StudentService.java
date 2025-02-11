package service.imp;

import java.util.List;

import dao.IStudentDAO;
import dao.imp.StudentDAO;
import domain.Student;
import service.IStudentService;

public class StudentService implements IStudentService{
	private 	IStudentDAO studentDAO = new StudentDAO();
	public StudentService() {
		// TODO Auto-generated constructor stub
	}
	//添加学生信息到数据库
		public void addStudent(Student student) {
			if(student.getStudent_id()+"" == null) {
				return ;
			}
			//获取用户名
			String student_id = student.getStudent_id();
			List<Student> students = studentDAO.getStudentByUserName(student_id);
			if(students.size() <= 0) {
				studentDAO.addStudent(student);
			}else {
				throw new RuntimeException("该用户名已存在，请更换用户名！");
			}
			
		}
		//根据学生编号获取学生信息
		public Student getStudentByID(String student_id) {
			if(student_id == null) 
				return null;
			return studentDAO.getStudentByID(student_id);
			
		}
		
		
		//验证学生用户登录名和密码是否合法
		public Student validateStudent(String student_id,String student_password) {
			//调用数据访问方法获取用户名学号对应的用户信息
			List<Student> students = studentDAO.getStudentByUserName(student_id);
			if (students != null && students.size() != 0) {
				if (students.get(0).getStudent_password().equals(student_password)) {
					//如果用户名和密码合法，则返回用户的完整信息，否则返回null
					return students.get(0);
				}
				else
				{
					return null;
				}
			}
			else	
			{
				return null;
			}
		}
		//验证学生用户名是否已经存在
		public boolean isStudentExist(String student_id) {
			List<Student> students = studentDAO.getStudentByUserName(student_id);
			if(students.size()!=0)
				return true;
			else
				return false;
		}
		
		//根据学生学号获取绩点
		public Student getGpa(String student_id) {
			if(student_id == null) 
				return null;
			return studentDAO.getStudentByID(student_id);
		}
		
		//获取所有学生列表
		public List<Student> getAllStudent(){
			return studentDAO.getAllStudent();			
		}
		//获取某专业学生列表
		public List<Student> getSdeptStudent(String speciality){
			return studentDAO.getSdeptStudent(speciality);
		}
		//获取某班级学生列表
		public List<Student> getClassStudent(String classes_id){
			return studentDAO.getClassStudent(classes_id);
		}
		//更新个人资料
		public void updateStudent(Student student) {
			//获取学生用户名(学号)
			String student_id = student.getStudent_id();
			//从数据库中查询用户名为student_id的用户
			List<Student> students = studentDAO.getStudentByUserName(student_id);
			if(students.size() > 0) {
				studentDAO.updateStudent(student);
			}else {
				// 抛出异常
				throw new RuntimeException("更新失败！");
			}
			
		}
		//删除管理员用户
		public void deleteStudent(String student_id) {
			studentDAO.deleteStudent(student_id);
			System.out.println("删除学生："+student_id);
		}
}
