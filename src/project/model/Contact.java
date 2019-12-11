package project.model;

public class Contact {
	private String code;
	private String type;
	private String desc;
	
	public Contact(String code, String type, String desc) {
		super();
		this.code = code;
		this.type = type;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
