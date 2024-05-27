package org.comstudy.todo.model;

import java.util.ArrayList;
import java.util.List;

public class TodoDAOList implements Dao {
	// 데이터베이스 대신 사용될 임시 리스트
	public static final List<TodoDTO> todoList = new ArrayList<TodoDTO>();
	static {
		todoList.add(new TodoDTO(10, "밥하고 빨래하기1", false));
		todoList.add(new TodoDTO(20, "스프링 공부하기1", true));
		todoList.add(new TodoDTO(30, "개인 프로젝트 수행1", false));
		todoList.add(new TodoDTO(40, "Github 연습하기1", false));
		todoList.add(new TodoDTO(11, "밥하고 빨래하기2", true));
		todoList.add(new TodoDTO(21, "스프링 공부하기2", true));
		todoList.add(new TodoDTO(31, "개인 프로젝트 수행2", false));
		todoList.add(new TodoDTO(41, "Github 연습하기2", false));
		todoList.add(new TodoDTO(12, "밥하고 빨래하기3", false));
		todoList.add(new TodoDTO(22, "스프링 공부하기3", true));
		todoList.add(new TodoDTO(32, "개인 프로젝트 수행3", true));
		todoList.add(new TodoDTO(42, "Github 연습하기3", false));
	}
	public static int seq = 5;

	@Override
	public List<TodoDTO> selectAll() {
		List<TodoDTO> newTodoList = new ArrayList<TodoDTO>();
		for (int i = 0; i < todoList.size(); i++) {
			int seq = todoList.get(i).getSeq();
			String title = todoList.get(i).getTitle();
			boolean done = todoList.get(i).isDone();
			newTodoList.add(new TodoDTO(seq, title, done));
		}
		return newTodoList;
	}

	private int findIndex(List<TodoDTO> todoList, TodoDTO dto) {
		int index = -1;
		for (int i = 0; i < todoList.size(); i++) {
			if (dto.getSeq() == todoList.get(i).getSeq()) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public TodoDTO selectOne(TodoDTO dto) {
		TodoDTO todo = null;

		int i = findIndex(todoList, dto);

		if (i != -1) {
			String title = todoList.get(i).getTitle();
			boolean done = todoList.get(i).isDone();
			todo = new TodoDTO(dto.getSeq(), title, done);
		}

		return todo;
	}

	@Override
	public void insert(TodoDTO dto) {
		dto.setSeq(seq++);
		todoList.add(dto);
	}

	@Override
	public void update(TodoDTO dto) {
		int i = findIndex(todoList, dto);

		if (i != -1) {
			if(dto.getTitle() != null) {				
				todoList.get(i).setTitle(dto.getTitle());
			}
			todoList.get(i).setDone(dto.isDone());
		}
	}

	@Override
	public void delete(TodoDTO dto) {
		int i = findIndex(todoList, dto);

		if (i != -1) {
			todoList.remove(i);
		}
	}
}
