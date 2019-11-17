package comment.model;

import java.sql.Date;

public class CommentDTO {
	private int replyId;
	private int boardId;
	private String userId;
	private Date createDate;
	private String content;
	private char deleteYN;
	private int ref;
	private int step;
	private int position;
	
	public CommentDTO() {
	}

	public CommentDTO(int replyId, int boardId, String userId, Date createDate, String content,
			char deleteYN, int ref, int step, int position) {
		super();
		this.replyId = replyId;
		this.boardId = boardId;
		this.userId = userId;
		this.createDate = createDate;
		this.content = content;
		this.deleteYN = deleteYN;
		this.ref = ref;
		this.step = step;
		this.position = position;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
