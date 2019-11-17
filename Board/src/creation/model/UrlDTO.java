package creation.model;

public class UrlDTO {
	private int bod_idx;
	private String or_URL;
	private String sh_URL;
	private String cr_ID;
	
	public UrlDTO() {
	}
	public UrlDTO(int bod_idx, String or_URL, String sh_URL, String cr_ID) {
		super();
		this.bod_idx = bod_idx;
		this.or_URL = or_URL;
		this.sh_URL = sh_URL;
		this.cr_ID = cr_ID;
	}
	
	public int getBod_idx() {
		return bod_idx;
	}
	public void setBod_idx(int bod_idx) {
		this.bod_idx = bod_idx;
	}
	public String getOr_URL() {
		return or_URL;
	}
	public void setOr_URL(String or_URL) {
		this.or_URL = or_URL;
	}
	public String getSh_URL() {
		return sh_URL;
	}
	public void setSh_URL(String sh_URL) {
		this.sh_URL = sh_URL;
	}
	public String getCr_ID() {
		return cr_ID;
	}
	public void setCr_ID(String cr_ID) {
		this.cr_ID = cr_ID;
	}
}
