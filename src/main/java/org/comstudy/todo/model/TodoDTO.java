package org.comstudy.todo.model;

public class TodoDTO {
	private int seq;
	private String title;
	private boolean done;
	
	public TodoDTO() {
		// TODO Auto-generated constructor stub
	}

	public TodoDTO(int seq, String title, boolean done) {
		this.seq = seq;
		this.title = title;
		this.done = done;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		// JSON은 속성이 쌍따옴표로 되어야 한다.
		// 자바는 문자열 안에 쌍표를 표시할때 역슬래시를 사용 해야 한다.(쌍따옴표도 특수문자)
		// 구글에서 개발한 JSONObject 라이브러리를 사용하면 더 편리하다.
		return "{\"seq\":" + seq + ", \"title\":\"" + title + "\", \"done\":" + done + "}";
	}
}
