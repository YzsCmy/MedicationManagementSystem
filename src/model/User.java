package model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {


	private Integer id;
	private String username;
	private String password;
	private String query;
	private String update;
	private String browser;
	private String report;

	public User() {
	}

	public User(String username, String password, String query, String update,
			String browser, String report) {
		this.username = username;
		this.password = password;
		this.query = query;
		this.update = update;
		this.browser = browser;
		this.report = report;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUpdate() {
		return this.update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", query=" + query + ", update="
				+ update + ", browser=" + browser + ", report=" + report + "]";
	}
	

}