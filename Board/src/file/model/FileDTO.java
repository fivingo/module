package file.model;

public class FileDTO {
	private int fileId;
	private String maskName;
	private String originalName;
	private int boardId;
	
	public FileDTO() {
	}

	public FileDTO(int fileId, String maskName, String originalName, int boardId) {
		super();
		this.fileId = fileId;
		this.maskName = maskName;
		this.originalName = originalName;
		this.boardId = boardId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getMaskName() {
		return maskName;
	}

	public void setMaskName(String maskName) {
		this.maskName = maskName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
}
