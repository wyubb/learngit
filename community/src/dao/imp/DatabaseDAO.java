package dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDAO {
	private Connection conn = null;
	/**
	 * 获取数据库连接对象
	 * @return 数据库连接对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException,SQLException{
		Connection conn = null;
		//加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		//初始化数据库连接符并指定字符串编码集
		String connstr = "jdbc:mysql://localhost:3306/community?useUnicode = true&characterEncoding = utf8";
		//声明并初始化数据库用户名和密码
		String user = "root";
		String password = "123456";
		//连接数据库并验证数据
		conn =DriverManager.getConnection(connstr,user,password);
		System.out.println("Connection created!");
		return conn;
	}
	
	/**
	 * 关闭类中的连接对象
	 * @throws SQLException
	 */
	public void releaseConnection() throws SQLException{
		this.conn.close();
		this.conn = null;
	}
	
		/**
		 * 执行一个SQL查询语句，并返回查询的结果集
		 * @param SQL查询语句
		 * @return 数据库查询结果集
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		public ResultSet getResultSet(String querySQL) throws SQLException,ClassNotFoundException{
			if(conn == null) {
				this.conn = getConnection();
			}
			Statement stm = conn.createStatement();
			//执行SQL查询语句并返回查询结果集
			ResultSet thisRST = stm.executeQuery(querySQL);
			return thisRST;
		}
	
		/**
		 * 直接执行一条对数据库修改（包括增删查改）的SQL语句
		 * @param SQL语句
		 * @throws SQLException
		 * @throw ClassNotFoundException
		 */
		public void executeSQL(String SQL) throws SQLException,ClassNotFoundException{
			if(conn == null) {
				this.conn = getConnection();
			}
			Statement stm = conn.createStatement();
			//执行给定的SQL语句
			stm.executeUpdate(SQL);
			//关闭连接对象
			this.conn.close();
			this.conn = null;
		}
		
		/**
		 * 测试主函数
		 * @param args
		 */
		public static void main(String[] args) {
			//声明数据库连接类对象
			DatabaseDAO myDb = new DatabaseDAO();
			 try {
				 //获取数据库连接对象
				 myDb.conn = myDb.getConnection();
				 //释放连接对象
				 myDb.releaseConnection();
				 //处理抛出的异常
			 }catch(SQLException e) {
				 e.printStackTrace();
			 }catch(ClassNotFoundException e) {
				 e.printStackTrace();
			 }
		}
}
