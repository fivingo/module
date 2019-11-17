package creation.model;

public class QrDTO {
	int bod_idx;
	String cr_ID;
	String fileName;
	
	public QrDTO() {
	}
	public QrDTO(int bod_idx, String cr_ID, String fileName) {
		super();
		this.bod_idx = bod_idx;
		this.cr_ID = cr_ID;
		this.fileName = fileName;
	}
	
	public int getBod_idx() {
		return bod_idx;
	}
	public void setBod_idx(int bod_idx) {
		this.bod_idx = bod_idx;
	}
	public String getCr_ID() {
		return cr_ID;
	}
	public void setCr_ID(String cr_ID) {
		this.cr_ID = cr_ID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
