package model;

import java.sql.Timestamp;

public class Client implements java.io.Serializable {

	private String cno;
	private Medicine medicine;
	private Agency agency;
	private String cname;
	private String csex;
	private Integer cage;
	private String caddress;
	private String cphone;
	private String csymptom;
	private Timestamp cdate;
	private String cremark;

	public Client() {
	}

	public Client(String cno) {
		this.cno = cno;
	}

	public Client(String cno, Medicine medicine, Agency agency, String cname,
			String csex, Integer cage, String caddress, String cphone,
			String csymptom, Timestamp cdate, String cremark) {
		this.cno = cno;
		this.medicine = medicine;
		this.agency = agency;
		this.cname = cname;
		this.csex = csex;
		this.cage = cage;
		this.caddress = caddress;
		this.cphone = cphone;
		this.csymptom = csymptom;
		this.cdate = cdate;
		this.cremark = cremark;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Agency getAgency() {
		return this.agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCsex() {
		return this.csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	public Integer getCage() {
		return this.cage;
	}

	public void setCage(Integer cage) {
		this.cage = cage;
	}

	public String getCaddress() {
		return this.caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getCphone() {
		return this.cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getCsymptom() {
		return this.csymptom;
	}

	public void setCsymptom(String csymptom) {
		this.csymptom = csymptom;
	}

	public Timestamp getCdate() {
		return this.cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}

	public String getCremark() {
		return this.cremark;
	}

	public void setCremark(String cremark) {
		this.cremark = cremark;
	}

	@Override
	public String toString() {
		return "Client [cno=" + cno + ", medicine=" + medicine + ", agency=" + agency + ", cname=" + cname + ", csex="
				+ csex + ", cage=" + cage + ", caddress=" + caddress + ", cphone=" + cphone + ", csymptom=" + csymptom
				+ ", cdate=" + cdate + ", cremark=" + cremark + "]";
	}

	
}