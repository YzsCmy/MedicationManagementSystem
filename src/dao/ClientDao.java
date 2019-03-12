package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.regexp.internal.recompile;

import model.Agency;
import model.Client;
import model.Medicine;
import utils.Dbutils;
import utils.StringUtils;

public class ClientDao {
	private MedicineDao mDao = new MedicineDao();
	private AgencyDao aDao = new AgencyDao();
	private QueryRunner query = new QueryRunner();
	
	public void save(Connection con,Client client){
		String sql = "INSERT INTO client VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		Object []params={client.getCno(),client.getCname(),client.getCsex(),
				client.getCage(),client.getCaddress(),client.getCphone(),
				client.getCsymptom(),client.getMedicine().getMno(),client.getAgency().getAno(),client.getCdate(),client.getCremark()};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void modify(Connection con,Client client,String cno,Timestamp timestamp){
		String sql = "update client set cno=?,cname=?,csex=?,cage=?,caddress=?,"
				+ "cphone=?,csymptom=?,mno=?,ano=?,cdate=?,cremark=? where cno=? and cdate=?";
		Object []params={client.getCno(),client.getCname(),client.getCsex(),
				client.getCage(),client.getCaddress(),client.getCphone(),
				client.getCsymptom(),client.getMedicine().getMno(),client.getAgency().getAno(),
				client.getCdate(),client.getCremark(),cno,timestamp};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void delete(Connection con,String cno,Timestamp timestamp){
		String sql = "delete from client where cno=? and cdate=?";
		Object []params={cno,timestamp};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	//........
	public List<Client> findAll(Connection con){
		String sql = "select * from client";
		try {
			List<Map<String,Object>> list = query.query(con, sql, new MapListHandler());
			List<Client> res = new ArrayList<>();
			for(Map m:list){
				Client temp = new Client();
				temp.setCno((String) m.get("cno"));
				temp.setCname((String) m.get("cname"));
				temp.setCsex((String)m.get("csex"));
				temp.setCage((Integer) m.get("cage"));
				temp.setCaddress((String)m.get("caddress"));
				temp.setCphone((String)m.get("cphone"));
				temp.setCsymptom((String)m.get("csymptom"));
				String mno = (String) m.get("mno");
				String ano = (String) m.get("ano");
				Medicine med = mDao.findById(Dbutils.getCon(),mno);
				Agency agen = aDao.findById(Dbutils.getCon(),ano);
				temp.setMedicine(med);
				temp.setAgency(agen);
				temp.setCdate((Timestamp) m.get("cdate"));
				temp.setCremark((String) m.get("cremark"));
				res.add(temp);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Client> findAllClient(Connection con){
		String sql = "select distinct cno, cname from client";
		try {
			List<Client> list = query.query(con, sql, new BeanListHandler<>(Client.class));
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Client> findByName(Connection con,String name){
		String sql = "select * from client where cname like '%"+name+"%'";
		try {
			List<Map<String,Object>> list = query.query(con, sql, new MapListHandler());
			List<Client> res = new ArrayList<>();
			for(Map m:list){
				Client temp = new Client();
				temp.setCno((String) m.get("cno"));
				temp.setCname((String) m.get("cname"));
				temp.setCsex((String)m.get("csex"));
				temp.setCage((Integer) m.get("cage"));
				temp.setCaddress((String)m.get("caddress"));
				temp.setCphone((String)m.get("cphone"));
				temp.setCsymptom((String)m.get("csymptom"));
				String mno = (String) m.get("mno");
				String ano = (String) m.get("ano");
				Medicine med = mDao.findById(Dbutils.getCon(),mno);
				Agency agen = aDao.findById(Dbutils.getCon(),ano);
				temp.setMedicine(med);
				temp.setAgency(agen);
				temp.setCdate((Timestamp) m.get("cdate"));
				temp.setCremark((String) m.get("cremark"));
				res.add(temp);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Client findById(Connection con, String cno) {
		String sql = "select * from client where cno=?";
		try {
			return query.query(con, sql, new BeanHandler<>(Client.class),cno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public List<Client> search(Connection con, Client client) {
		if(StringUtils.isEmpty(client.getCsex())){
			return findAll(con);
		}
		String sql = "select * from client where cno like '%"+client.getCno()+"%'and cname like '%"+client.getCname()+"%' and csex='"+client.getCsex()+"'";
		try {
			List<Map<String,Object>> list = query.query(con, sql, new MapListHandler());
			List<Client> res = new ArrayList<>();
			for(Map m:list){
				Client temp = new Client();
				temp.setCno((String) m.get("cno"));
				temp.setCname((String) m.get("cname"));
				temp.setCsex((String)m.get("csex"));
				temp.setCage((Integer) m.get("cage"));
				temp.setCaddress((String)m.get("caddress"));
				temp.setCphone((String)m.get("cphone"));
				temp.setCsymptom((String)m.get("csymptom"));
				String mno = (String) m.get("mno");
				String ano = (String) m.get("ano");
				Medicine med = mDao.findById(Dbutils.getCon(),mno);
				Agency agen = aDao.findById(Dbutils.getCon(),ano);
				temp.setMedicine(med);
				temp.setAgency(agen);
				temp.setCdate((Timestamp) m.get("cdate"));
				temp.setCremark((String) m.get("cremark"));
				res.add(temp);
			}
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String countClient(Connection con) {
		String sql = "SELECT count(DISTINCT cno) from client";
		try {
			 String num = query.query(con, sql, new ScalarHandler()).toString();
			 return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public String getNumByClient(Connection con, String cno) {
		String sql = "SELECT count(*) from client where cno=?";
		try {
			String num = query.query(con, sql, new ScalarHandler(),cno).toString();
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
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
