package model;

import java.util.HashSet;
import java.util.Set;


public class Agency implements java.io.Serializable {


	private String ano;
	private String aname;
	private String asex;
	private String aphone;
	private String aremark;
	
	private Set clients = new HashSet(0);

	public Agency() {
	}

	public Agency(String ano) {
		this.ano = ano;
	}

	public Agency(String ano, String aname, String asex, String aphone,
			String aremark, Set clients) {
		this.ano = ano;
		this.aname = aname;
		this.asex = asex;
		this.aphone = aphone;
		this.aremark = aremark;
		this.clients = clients;
	}


	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAsex() {
		return this.asex;
	}

	public void setAsex(String asex) {
		this.asex = asex;
	}

	public String getAphone() {
		return this.aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public String getAremark() {
		return this.aremark;
	}

	public void setAremark(String aremark) {
		this.aremark = aremark;
	}

	public Set getClients() {
		return this.clients;
	}

	public void setClients(Set clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return this.aname;
	}

	
}