package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import vo.Category;

public class BoardDao {
	//1. 카테고리 목록 출력
	public ArrayList<HashMap<String, Object>> categoryList(){
		// 카테고리를 담을 객체
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//DB 연동
		Connection conn = null;	// 디비 연결
		PreparedStatement stmt = null;	// 쿼리 실행
		ResultSet rs = null;	// 실행한 쿼리의 결과들을 저장 > select
		//1. 드라이버 연동
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
		//2. DB 접속 - 마리아디비
		
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/blog","root", "java1234");
		// 디버깅
		System.out.println("conn >" + conn);
		
		//3. SQL 쿼리 작성
		// 뒤에는 별칭들을 붙임 (컬럼 이름)
		String sql = "SELECT category_name categoryName, create_date createDate, update_date updateDate FROM category";
		stmt = conn.prepareStatement(sql); // 쿼리 실행
		rs = stmt.executeQuery(); // 실행할 결과물 저장
		System.out.println("rs >" + rs);
		
		while(rs.next()) { // true이면 한 행 씩 실행(저장)
			HashMap<String, Object> map = new HashMap<>();
			map.put("categoryName", rs.getString("categoryName"));
			list.add(map);
		}
		System.out.println("list >" + list);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}