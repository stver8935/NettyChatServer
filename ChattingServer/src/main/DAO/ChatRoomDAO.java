package DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ChatRoomDAO extends DBconnector {

	
	//������
	private String sql;
	
	//�����
	private JsonObject result;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
	
	
	private Gson gson;
	
	private static ChatRoomDAO chatroom_dao;
	

	
	//�⺻ ������
	private ChatRoomDAO() {
		//�θ� ������ - �����ͺ��̽� Ŀ�ؼ�
		super();
	}
	
	public static synchronized ChatRoomDAO ChatRoomDAO(){
		
		if(chatroom_dao == null) {
			chatroom_dao = new ChatRoomDAO();
			
		}else {
			
		}
	
		return chatroom_dao;
	}
	
	
	
	
	/*
	��ȯ������ ���� == userList
	userList=[
	{id:�������̵�}
	,{id:�������̵�}
	]
	 
    */
	
	
	
	public JsonArray getUserList(String chatroomkey){
		
		//���� ����Ʈ
		JsonArray userList = new JsonArray();
		
		//����� �÷��� ��
		int col_count;
		
		//������ ���̵�
		String user_id;
		String col_name;
		
		try {
			//�����
			rs=getStmt().executeQuery("SELECT *FROM Chat_Room_Participants WHERE chat_room_key = "+chatroomkey);
			
			while(rs.next()) {
				//����� �÷�
				rsmd=rs.getMetaData();
				//�÷��� ����
				col_count=rsmd.getColumnCount();
				
				//ä�ù� �������� ������ ���� ��ü
				JsonObject user=new JsonObject();
				
					//��ȯ�������� �ι�° �÷� ������
					col_name = rsmd.getColumnName(2);
					user_id = rs.getString(2);
					
					user.addProperty(col_name, user_id);
					userList.add(user);
				
			}
		
		}catch(SQLException  e) {
			System.out.println("query Error : "+"getUserList");
			e.printStackTrace();
		}
		
		return userList;
		
	}
	
	
	public void removeUser(String chatroomkey , String user_id) {
	
		try {
			getStmt().executeQuery("DELETE *FROM Chat_Room_Participants ChatRoomKey = "+chatroomkey+" AND "+"UserId = "+user_id);
	
		}catch(SQLException e) {
			System.out.println("remove User Error");
			e.printStackTrace();
		}
		
		
	}
	
	
	public void addUser(String chatroomkey, String user_id) {
		try {
			getStmt().executeQuery("INSERT INTO Chat_Room_Participants(UserId) VALUE("+user_id+") WHERE ChatRoomKey = "+chatroomkey);
		
		}catch(SQLException e) {
			System.out.println("add User Error");
			e.printStackTrace();
		}	
		
	}
	
	
	
}
