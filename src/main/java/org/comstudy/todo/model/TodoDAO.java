package org.comstudy.todo.model;

import java.util.List;

public class TodoDAO implements Dao {
	// Jdbc로 변경하기
	// 데이터 베이스 선택 : H2 데이터베이스 or 자신있는 DB or db4free.net 등
	// 참고: https://green333.tistory.com/16

	@Override
	public List<TodoDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoDTO selectOne(TodoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(TodoDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TodoDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(TodoDTO dto) {
		// TODO Auto-generated method stub

	}

}
