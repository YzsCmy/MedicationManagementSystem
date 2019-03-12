package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import model.Guide;

public class GuideDao {
	private QueryRunner query = new QueryRunner();
	
	public Guide queryGuides(Connection con,String name){
		String sql = "";
		if(name.equals("add")){
			sql = "select `add` from instructions";
		}else if (name.equals("delete")) {
			sql = "select `delete` from instructions";
		}else if (name.equals("update")) {
			sql = "select `update` from instructions";
		}else if (name.equals("report")) {
			sql = "select `report` from instructions";
		}else if (name.equals("browser")) {
			sql = "select * from instructions";
		}
		try {
			return query.query(con, sql, new BeanHandler<>(Guide.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
