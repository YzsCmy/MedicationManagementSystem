package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import model.Agency;
import model.Medicine;
import utils.StringUtils;

public class MedicineDao {
	private QueryRunner query = new QueryRunner();
	
	public void save(Connection con,Medicine medicine){
		String sql = "INSERT INTO medicine VALUE(?,?,?,?)";
		Object []params={medicine.getMno(),medicine.getMname(),medicine.getMmode(),medicine.getMefficacy()};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void modify(Connection con,Medicine medicine,String mno){
		String sql = "update medicine set mno=?,mname=?,mmode=?,mefficacy=? where mno=?";
		Object []params={medicine.getMno(),medicine.getMname(),medicine.getMmode(),medicine.getMefficacy(),mno};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void delete(Connection con,String mno){
		String sql = "delete from medicine where mno=?";
		Object []params={mno};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<Medicine> findAll(Connection con){
		String sql = "select * from medicine";
		try {
			return query.query(con, sql, new BeanListHandler<>(Medicine.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<Medicine> findByName(Connection con,String name){
		String sql = "select * from medicine where mname like '%"+name+"%'";
		try {
			return query.query(con, sql, new BeanListHandler<>(Medicine.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Medicine findById(Connection con, String mno) {
		String sql = "select * from medicine where mno=?";
		try {
			return query.query(con,sql, new BeanHandler<>(Medicine.class),mno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<Medicine> search(Connection con, Medicine medicine) {
		if(StringUtils.isEmpty(medicine.getMmode())){
			return findAll(con);
		}
		String sql = "select * from medicine where mno LIKE '%"+medicine.getMno()+"%' AND mname LIKE '%"+medicine.getMname()+"%' AND mmode=?";
		try {
			return query.query(con, sql, new BeanListHandler<>(Medicine.class),medicine.getMmode());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String countMedicine(Connection con) {
		String sql = "SELECT count(*) from medicine";
		try {
			 String num = query.query(con, sql, new ScalarHandler()).toString();
			 return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public String getNumByMno(Connection con, String mno) {
		String sql = "SELECT count(*) from client where mno=?";
		try {
			String num = query.query(con, sql, new ScalarHandler(),mno).toString();
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public String countOrders(Connection con) {
		String sql = "SELECT count(*) from client";
		try {
			String num = query.query(con, sql, new ScalarHandler()).toString();
			return num;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
