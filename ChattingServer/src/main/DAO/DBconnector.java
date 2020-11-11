package DAO;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DBconnector {

	
	//�����ͺ��̽� ���� ����
	protected static Statement stmt;

	//�����ͺ��̽� ����
	protected static Connection con;
	

	
	//�����
	private Gson gson;
	
    public DBconnector(){
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		System.out.println("jdbc ����̹� �ε� ����");
	
		}catch(ClassNotFoundException e) {
		System.out.println("jdbc ����̹� �ε� ���� : "+e.getMessage());
		
		}
		
		String url = "jdbc:mysql://13.125.130.142:3306/CARROT_MARKET";
		
		try {
		
			if(con == null) {
			//�ش� �����ͺ��̽� ����
			 con = DriverManager.getConnection(url,"��� ���̵�","����й�ȣ");
			System.out.println("db ���� ����");
			}
			if(stmt == null) {
			//������ ���̽� ���� ���°� ����
			stmt = con.createStatement();
			}
		}catch(java.lang.Exception e) {
			System.out.println("������ ���̽� ���� ����");
			e.printStackTrace();
			
			
		}
    }
    
	protected Statement getStmt() {
	
		if(stmt == null) {
			
			System.out.println("DB statement Error : �����ͺ��̽��� ����� ���°� �ƴմϴ�.");
		}
		
		return stmt;
	
	}
	
	
	protected Connection getConn() {
		if(con == null) {
				
				System.out.println("DB connection error : �����ͺ��̽��� ����� ���°� �ƴմϴ�.");
		}
			
		return con;		
	}
	
	
	protected void discDB() {
		
		try {
			if(stmt != null || con != null) {
				
				stmt.close();
				con.close();
				System.out.println("������ ���̽� ���� ����");
				
			}
			
		}catch(java.lang.Exception e) {
			System.out.println("������ ���̽� ���� ���� ����");
			e.printStackTrace();
			
		}	
		
	}



	
	
}
