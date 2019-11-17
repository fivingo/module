package member.model;

public class MemberDTO {
	private String userId;
	private String pwd;
	private String name;
	
	public MemberDTO() {
	}
	
	public MemberDTO(String userId, String pwd, String name) {
		super();
		this.userId = userId;
		this.pwd = pwd;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
