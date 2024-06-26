package org.comstudy.todo.model;

import java.util.List;

public interface Dao {

	List<TodoDTO> selectAll();

	TodoDTO selectOne(TodoDTO dto);

	void insert(TodoDTO dto);

	void update(TodoDTO dto);

	void delete(TodoDTO dto);

}