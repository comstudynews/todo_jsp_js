package org.comstudy21.myweb.dbcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
-- H2 데이터 베이스에 todolist 테이블 생성 및 데이터 입력 테스트

drop table IF EXISTS todolist;

create table IF NOT EXISTS todolist (
	seq number primary key auto_increment,
	title varchar2(50) not null,
	done boolean not null
);

INSERT INTO todolist (title, done) values ('할일 입력 테스트', false);
INSERT INTO todolist (title, done) values ('할일 입력 테스트 2', true);

select * from todolist;
*/

public class JdbcUtil {

	public static Connection getConnection() {
		// JDBC를 하기위해 드라이버 준비
		// 1) main()에서 테스트는 프로젝트 > Build Path > 컨피그 빌드패스 등록
		// 드라이브 등록 방법은
		// main() 애플리케이션에서와 Web 애플리케이션에서 다름.
		// 2) Web 앱에서는 webapp > WEB-INF > lib 에 드라이버 라이브러리 복사
		// 두가지 방법 모두 적용해서 준비
		// h2 접속 콘솔을 보고 접속 정보 준비
		String url = "jdbc:h2:tcp://localhost/~/test";
		String user = "sa";
		String password = "";
		Connection conn = null;
		// 예외 처리 필수
		try {
			// 클래스 확장자는 지우고 
			Class.forName("org.h2.Driver");
			// 접속 할 데이터베이스 엔진이 실행 된 상태여야 함.
			// H2 데이터베이스는 매번 수동 실행 해야 함.
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 검색 실패!");
		} catch (SQLException e) {
			System.err.println("접속 실패");
		}
		return conn;
	}
	
	public static void close(Connection obj) {
		if(obj != null) {
			try {
				obj.close();
			} catch (SQLException e) { }
		}
	}
	
	public static void close(ResultSet obj) {
		if(obj != null) {
			try {
				obj.close();
			} catch (SQLException e) { }
		}
	}
	
	public static void close(Statement obj) {
		if(obj != null) {
			try {
				obj.close();
			} catch (SQLException e) { }
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		close(conn);
		close(stmt);
		close(rs);
	}
	// 오버로딩 : 매개변수의 데이터 타입과 갯수(시그너처)가 다르면 같은 이름으로 함수명 사용 가능.
}
