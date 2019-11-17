package excel.model;

public class ExcelDTO {
	private int studentNum;
	private String name;
	private String birth;
	private int majorCode;
	private String admission;
	private int termCode;
	private String addr;
	private int proCode;
	private String beIn;
	private int probation;
	
	public ExcelDTO() {
	}
	public ExcelDTO(int studentNum, String name, String birth, int majorCode, String admission, int termCode,
			String addr, int proCode, String beIn, int probation) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.birth = birth;
		this.majorCode = majorCode;
		this.admission = admission;
		this.termCode = termCode;
		this.addr = addr;
		this.proCode = proCode;
		this.beIn = beIn;
		this.probation = probation;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getMajorCode() {
		return majorCode;
	}
	public void setMajorCode(int majorCode) {
		this.majorCode = majorCode;
	}
	public String getAdmission() {
		return admission;
	}
	public void setAdmission(String admission) {
		this.admission = admission;
	}
	public int getTermCode() {
		return termCode;
	}
	public void setTermCode(int termCode) {
		this.termCode = termCode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getProCode() {
		return proCode;
	}
	public void setProCode(int proCode) {
		this.proCode = proCode;
	}
	public String getBeIn() {
		return beIn;
	}
	public void setBeIn(String beIn) {
		this.beIn = beIn;
	}
	public int getProbation() {
		return probation;
	}
	public void setProbation(int probation) {
		this.probation = probation;
	}
}
