package list.model;

import java.sql.Date;

public class ListDTO {
	private int boardId;
	private String title;
	private String userId;
	private Date createDate;
	private String content;
	private char deleteYN;
	private int viewCount;
	private int ref;
	private int step;
	private int position;
	
	public ListDTO() {
	}

	public ListDTO(int boardId, String title, String userId, Date createDate, String content, char deleteYN,
			int viewCount, int ref, int step, int position) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.userId = userId;
		this.createDate = createDate;
		this.content = content;
		this.deleteYN = deleteYN;
		this.viewCount = viewCount;
		this.ref = ref;
		this.step = step;
		this.position = position;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titile) {
		this.title = titile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
