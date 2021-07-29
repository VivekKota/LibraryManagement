package com.library.model;

public class Record {

	private int RecordId;
	private int userId;
	private int BookId;
	private String status;
	private java.sql.Timestamp date;

	public Record(int recordId, int userId, int bookId, String status, java.sql.Timestamp date) {
		super();
		RecordId = recordId;
		this.userId = userId;
		BookId = bookId;
		this.status = status;
		this.date = date;
	}

	public Record() {
		super();
	}

	public int getRecordId() {
		return RecordId;
	}

	public void setRecordId(int recordId) {
		RecordId = recordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Timestamp getDate() {
		return date;
	}

	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Record [RecordId=" + RecordId + ", userId=" + userId + ", BookId=" + BookId + ", status=" + status
				+ ", date=" + date + "]";
	}

}
