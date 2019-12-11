package project.model;

import java.sql.Date;

public class Issue {
	private String code;
	private String customerCode;
	private String agentCode;
	private Date date;
	private String desc;
	private String statusCode;
	
	public Issue(String code, String customerCode, String agentCode, Date date, String desc, String statusCode) {
		super();
		this.code = code;
		this.customerCode = customerCode;
		this.agentCode = agentCode;
		this.date = date;
		this.desc = desc;
		this.statusCode = statusCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
