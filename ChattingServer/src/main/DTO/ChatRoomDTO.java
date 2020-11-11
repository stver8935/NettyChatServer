package DTO;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ChatRoomDTO {

	private String ChatRoomKey;
	private Gson gson;
	private JsonArray userList;
	
	
	//ä�ù� ����
	public ChatRoomDTO(String ChatRoomKey,JsonArray userList) {
		
		if(ChatRoomKey != null || !ChatRoomKey.isEmpty()) {
		
			this.ChatRoomKey = ChatRoomKey;
		
		}else {
			
			System.out.println("������ ������ ä�ù�Ű�� �ƴմϴ�.: "+ChatRoomKey);
			
		}
		
		if(1<=userList.size()) {
			
			this.userList = userList;
				
		}else {
			
			System.out.println("ä�ù濡 �������� ������ �����Ƿ� ä�ù��� �������� �ʾҽ��ϴ�..");
		}
		
		
	}
	
	
	//ä�ù濡 �������� ����� ���̵� ����Ʈ getter setter
	public JsonArray getUserList() {
		if(userList.size()<1) {
			System.out.println("ä�ù濡 ����ڰ� �����ϴ�.");
		}else {
			System.out.println("ä�ù� ����� ��� ��ȸ ����"+userList);
		}
		
		return userList;
	}
	
	public void setUserList(JsonArray userList) {
		
		this.userList = userList;
	}
	
	
	//ä�ù濡 ���� ���̵� �߰�
	public void addUser(String UserId) {
		JsonObject user = new JsonObject();
		user.addProperty("userId", UserId);
		
		if(!userList.contains(user)) {
			userList.add(user);	
			System.out.println("ä�ù� ����� �߰�");
		}else {
			System.out.println("ä�ù濡 �̹����� �ϴ� ����� �Դϴ�.");	
		}
		
	}
	
	
	//ä�ù濡�� ����� ����
	public void removeUser(String UserId) {
		JsonObject user = new JsonObject();
		user.addProperty("userId", UserId);
		
		if(userList.contains(user)) {
			userList.remove(user);	
			System.out.println("ä�ù� ����� ����");
		}else {
			System.out.println("ä�ù濡 ���� �����ʴ� ����� �Դϴ�.");	
		}	
	}
	
	
	//ä�ù� ����Ű getter setter 
	public String getChatRoomkey() {
		return ChatRoomKey;
	}
	
	public void setChatRoomKey(String ChatRoomKey) {
		
		this.ChatRoomKey = ChatRoomKey;
	
	}
	
	
	
}
