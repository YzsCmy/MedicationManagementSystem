package model;

import java.util.HashSet;
import java.util.Set;


public class Medicine implements java.io.Serializable {


	private String mno;
	private String mname;
	private String mmode;
	private String mefficacy;

	private Set clients = new HashSet(0);

	public Medicine() {
	}

	public Medicine(String mno) {
		this.mno = mno;
	}

	public Medicine(String mno, String mname, String mmode, String mefficacy,
			Set clients) {
		this.mno = mno;
		this.mname = mname;
		this.mmode = mmode;
		this.mefficacy = mefficacy;
		this.clients = clients;
	}


	public String getMno() {
		return this.mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMmode() {
		return this.mmode;
	}

	public void setMmode(String mmode) {
		this.mmode = mmode;
	}

	public String getMefficacy() {
		return this.mefficacy;
	}

	public void setMefficacy(String mefficacy) {
		this.mefficacy = mefficacy;
	}

	public Set getClients() {
		return this.clients;
	}

	public void setClients(Set clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return this.mname;
	}

	
}