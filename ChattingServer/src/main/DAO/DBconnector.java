package DAO;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DBconnector {

	
	//데이터베이스 연결 상태
	protected static Statement stmt;

	//데이터베이스 연결
	protected static Connection con;
	

	
	//결과값
	private Gson gson;
	
    public DBconnector(){
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		System.out.println("jdbc 드라이버 로딩 성공");
	
		}catch(ClassNotFoundException e) {
		System.out.println("jdbc 드라이버 로딩 실패 : "+e.getMessage());
		
		}
		
		String url = "jdbc:mysql://13.125.130.142:3306/CARROT_MARKET";
		
		try {
		
			if(con == null) {
			//해당 데이터베이스 연결
			 con = DriverManager.getConnection(url,"디비 아이디","디비비밀번호");
			System.out.println("db 연결 성공");
			}
			if(stmt == null) {
			//데이터 베이스 연결 상태값 저장
			stmt = con.createStatement();
			}
		}catch(java.lang.Exception e) {
			System.out.println("데이터 베이스 연동 에러");
			e.printStackTrace();
			
			
		}
    }
    
	protected Statement getStmt() {
	
		if(stmt == null) {
			
			System.out.println("DB statement Error : 데이터베이스에 연결된 상태가 아닙니다.");
		}
		
		return stmt;
	
	}
	
	
	protected Connection getConn() {
		if(con == null) {
				
				System.out.println("DB connection error : 데이터베이스에 연결된 상태가 아닙니다.");
		}
			
		return con;		
	}
	
	
	protected void discDB() {
		
		try {
			if(stmt != null || con != null) {
				
				stmt.close();
				con.close();
				System.out.println("데이터 베이스 연결 해제");
				
			}
			
		}catch(java.lang.Exception e) {
			System.out.println("데이터 베이스 연결 해제 에러");
			e.printStackTrace();
			
		}	
		
	}



	
	
}
