package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutils {
	private static String url="jdbc:mysql://localhost:3306/medidb";
	private static String jdbcName="com.mysql.jdbc.Driver";
	private static String userName="yzs";
	private static String password="root";
	static{
		try {
			Class.forName(jdbcName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getCon(){
		
		Connection con;
		try {
			con = DriverManager.getConnection(url, userName, password);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
		return  null;
	}
	public static void closeCon(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Connection con = Dbutils.getCon();
		if(con!=null){
			System.out.println("数据库连接成功！");
		}
		
		
	}
}

