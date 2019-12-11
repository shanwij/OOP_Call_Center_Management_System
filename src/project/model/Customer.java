package project.model;

public class Customer {
	private String code;
	private String statusCode;
	
	public Customer(String code, String statusCode) {
		super();
		this.code = code;
		this.statusCode = statusCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatus(String statusCode) {
		this.statusCode = statusCode;
	}
}
