package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import vo.Category;

public class BoardDao {
	//1. ī�װ� ��� ���
	public ArrayList<HashMap<String, Object>> categoryList(){
		// ī�װ��� ���� ��ü
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//DB ����
		Connection conn = null;	// ��� ����
		PreparedStatement stmt = null;	// ���� ����
		ResultSet rs = null;	// ������ ������ ������� ���� > select
		//1. ����̹� ����
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
		//2. DB ���� - �����Ƶ��
		
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/blog","root", "java1234");
		// �����
		System.out.println("conn >" + conn);
		
		//3. SQL ���� �ۼ�
		// �ڿ��� ��Ī���� ���� (�÷� �̸�)
		String sql = "SELECT category_name categoryName, create_date createDate, update_date updateDate FROM category";
		stmt = conn.prepareStatement(sql); // ���� ����
		rs = stmt.executeQuery(); // ������ ����� ����
		System.out.println("rs >" + rs);
		
		while(rs.next()) { // true�̸� �� �� �� ����(����)
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