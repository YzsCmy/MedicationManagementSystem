package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import model.User;

public class UserDao {
	private QueryRunner query = new QueryRunner();
	
	public User login(Connection con,User u){
		String sql = "select * from user where username=? and password=?";
		try {
			return query.query(con, sql, new BeanHandler<User>(User.class),u.getUsername(),u.getPassword());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void save(Connection con,User u){
		String sql = "INSERT INTO USER VALUE(NULL,?,?,?,?,?,?)";
		Object []params={u.getUsername(),u.getPassword(),u.getQuery(),
				u.getUpdate(),u.getBrowser(),u.getReport()};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void modify(Connection con,User u){
		String sql = "update user set username=?,password=?,query=?,`update`=?,browser=?,report=? where id=?";
		Object []params={u.getUsername(),u.getPassword(),u.getQuery(),
				u.getUpdate(),u.getBrowser(),u.getReport(),u.getId()};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(Connection con,String id){
		String sql = "delete from user where id=?";
		Object []params={Integer.parseInt(id)};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<User> findAll(Connection con){
		String sql = "select * from user";
		try {
			return query.query(con, sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<User> findByName(Connection con,String name){
		String sql = "select * from user where username like '%"+name+"%'";
		try {
			return query.query(con, sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
