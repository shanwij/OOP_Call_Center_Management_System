package project.model;

public class Complaint {
	private String code;
	private String issueCode;
	private String desc;
	
	public Complaint(String code, String issueCode, String desc) {
		super();
		this.code = code;
		this.issueCode = issueCode;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
