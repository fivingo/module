package gis.model;

public class GISDTO {
	private int idx;
	private String xCoord;
	private String yCoord;
	private String address;
	private String uName;
	private String uGroup;
	
	public GISDTO() {
	}
	public GISDTO(int idx, String xCoord, String yCoord, String address, String uName, String uGroup) {
		super();
		this.idx = idx;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.address = address;
		this.uName = uName;
		this.uGroup = uGroup;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getxCoord() {
		return xCoord;
	}
	public void setxCoord(String xCoord) {
		this.xCoord = xCoord;
	}
	public String getyCoord() {
		return yCoord;
	}
	public void setyCoord(String yCoord) {
		this.yCoord = yCoord;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuGroup() {
		return uGroup;
	}
	public void setuGroup(String uGroup) {
		this.uGroup = uGroup;
	}
}
