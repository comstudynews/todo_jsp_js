package org.comstudy.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.todo.model.Dao;
import org.comstudy.todo.model.TodoDAOList;
import org.comstudy.todo.model.TodoDTO;

public class TodoController extends HttpServlet {
	
	private Dao dao = new TodoDAOList();
	
	private PrintWriter out;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet - TodoController 실행");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		out = resp.getWriter();
		
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		int beginIndex = ctxPath.length();
		String urlPattern = reqUri.substring(beginIndex);
		
		System.out.println("urlPattern: " + urlPattern);
		
		if("/todo/list.do".equals(urlPattern)) {
			List<TodoDTO> todoList = dao.selectAll();
			out.println(todoList);
		} else {
			if(req.getParameter("seq") != null) {				
				int seq = Integer.parseInt(req.getParameter("seq") );
				System.out.println("seq => " + seq);
			}
			if("/todo/ok.do".equals(urlPattern)) {
				if(req.getParameter("seq") != null) {				
					int seq = Integer.parseInt(req.getParameter("seq") );
					System.out.println("seq => " + seq);
					TodoDTO todo = dao.selectOne(new TodoDTO(seq, null, false));
					dao.update(new TodoDTO(seq, null, !todo.isDone()) );
				}
			} else if("/todo/del.do".equals(urlPattern)) {
				if(req.getParameter("seq") != null) {				
					int seq = Integer.parseInt(req.getParameter("seq") );
					System.out.println("seq => " + seq);
					dao.delete(new TodoDTO(seq, null, false) );
				}
			}
			// 처리가 긑나고 화면에 목록을 JSON으로 다시 출력한다.
			List<TodoDTO> todoList = dao.selectAll();
			out.println(todoList);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 새 할일 저장, 수정 기능
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		int beginIndex = ctxPath.length();
		String urlPattern = reqUri.substring(beginIndex);
		
		System.out.println("urlPattern: " + urlPattern);
		
		if("/todo/save.do".equals(urlPattern)) {
			// 새 할일 저장
			String title = req.getParameter("title");
			dao.insert(new TodoDTO(0, title, false) );
		} else if("/todo/edit.do".equals(urlPattern)) {
			System.out.println("/todo/edit.do 호출");
			// 할일 제목, 확인 수정
			if(req.getParameter("seq") != null) {
				int seq = Integer.parseInt(req.getParameter("seq") );
				String title = req.getParameter("title");
				//boolean done = Boolean.valueOf(req.getParameter("done"));
				boolean done = false;
				if("true".equals(req.getParameter("done"))) {
					done = true;
				}
				dao.update(new TodoDTO(seq, title, done) );
			}
		}
	}
}
