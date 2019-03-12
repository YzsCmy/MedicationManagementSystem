package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import model.Agency;
import utils.StringUtils;

public class AgencyDao {
	private QueryRunner query = new QueryRunner();
	
	public void save(Connection con,Agency agency){
		String sql = "INSERT INTO agency VALUE(?,?,?,?,?)";
		Object []params={agency.getAno(),agency.getAname(),agency.getAsex(),agency.getAphone(),agency.getAremark()};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void modify(Connection con,Agency agency,String ano){
		String sql = "update agency set ano=?,aname=?,asex=?,aphone=?,aremark=? where ano=?";
		Object []params={agency.getAno(),agency.getAname(),agency.getAsex(),agency.getAphone(),agency.getAremark(),ano};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void delete(Connection con,String ano){
		String sql = "delete from agency where ano=?";
		Object []params={ano};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<Agency> findAll(Connection con){
		String sql = "select * from agency";
		try {
			return query.query(con, sql, new BeanListHandler<>(Agency.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<Agency> findByName(Connection con,String name){
		String sql = "select * from agency where aname like '%"+name+"%'";
		try {
			return query.query(con, sql, new BeanListHandler<>(Agency.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Agency findById(Connection con, String ano) {
		String sql = "select * from agency where ano=?";
		try {
			return query.query(con, sql, new BeanHandler<>(Agency.class),ano);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public List<Agency> search(Connection con,Agency agency){
		if(StringUtils.isEmpty(agency.getAsex())){
			return findAll(con);
		}
		String sql = "select * from agency where ano LIKE '%"+agency.getAno()+"%' AND aname LIKE '%"+agency.getAname()+"%' AND asex=?";
		try {
			return query.query(con, sql, new BeanListHandler<>(Agency.class),agency.getAsex());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//
	
	public int getNumByAgency(Connection con,String ano){
		String sql = "select count(*) from client where ano = ?";
		try {
			Long res = (Long)query.query(con, sql, new ScalarHandler(), ano);
			return res.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getTotal(Connection con){
		String sql = "select count(*) from client";
		try {
			Long res = (Long)query.query(con, sql, new ScalarHandler());
			return res.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getTotalAgency(Connection con){
		String sql = "select count(*) from agency";
		try {
			Long res = (Long)query.query(con, sql, new ScalarHandler());
			return res.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
