package DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

public class UserDAO extends DBconnector {
	
	
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private Gson gson;
	private static UserDAO user_dao;
	
	private UserDAO() {
		//�θ� ������ - DB Ŀ�ؼ�
		super();	
	}
	
	public static synchronized UserDAO UserDAO() {
		
		if(user_dao == null) {
			user_dao = new UserDAO();
		}else {
			
		}
		
		
		return user_dao;
	}

	
	//������ ������ �̹��� ����
	public String getProfileImg(String id) {
		String profile = null;
		
		try {
			rs=getStmt().executeQuery("selct profile_image from User_Info WHERE id = "+id);
			 profile = rs.getString("profile_image");
		} catch (SQLException e) {
			
			System.out.println("���� ������ �̹��� �������� ����");
			e.printStackTrace();
		}
		
		return profile;
	}
	
	//������ ��� ���� ����
	public JsonElement getUser(String id) {
		JsonElement user = null;
	
		
		try {
			rs=getStmt().executeQuery("select num , id ,phone ,name , email, profile_image , token from User_Info WHERE id = "+id);
			ResultSetMetaData rsmd;
			int col_cnt;
			
			rsmd=rs.getMetaData();
			col_cnt = rsmd.getColumnCount();
			
			for(int i =1; i <col_cnt; i++) {
				
				String col_name = rsmd.getColumnName(i);
				String col_val = rs.getString(i);
				user.getAsJsonObject().addProperty(col_name, col_val);

			}
		
		} catch (SQLException e) {	
			System.out.println("���� ���� �������� ����");
			e.printStackTrace();
		}
		
		
		return user;
		
	}
	
	
}
