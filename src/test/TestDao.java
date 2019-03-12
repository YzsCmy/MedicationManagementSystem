package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.AgencyDao;
import dao.ClientDao;
import dao.UserDao;
import model.Client;
import utils.Dbutils;

public class TestDao {
	@Test
	public void testName() throws Exception {
		Connection con = Dbutils.getCon();
//		UserDao dao = new UserDao();
		ClientDao dao = new ClientDao();
		List all = dao.search(con, new Client());
		System.out.println(all);
	}
	@Test
	public void testData() throws Exception {
		Date date = new Date();
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf2.format(date);
		
		System.out.println(s);
		Timestamp da = Timestamp.valueOf(s);
		System.out.println(da);
	}
	@Test
	public void testData1() throws Exception {
		Date date = new Date();
		
		Timestamp da = new Timestamp(date.getTime());
		System.out.println(da.toString().substring(0, 19));
//		String[] string = da.toString().split(".");
//		System.out.println(string[0]);
	}
	
	@Test
	public void testBatch() throws Exception {
		Connection con = Dbutils.getCon();
//		UserDao dao = new UserDao();
		AgencyDao dao = new AgencyDao();
		String[] strings = {"003","004"};
		try {
			dao.delete(con, strings[0]);
			dao.delete(con, strings[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
