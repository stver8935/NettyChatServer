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
	
	
	//채팅방 생성
	public ChatRoomDTO(String ChatRoomKey,JsonArray userList) {
		
		if(ChatRoomKey != null || !ChatRoomKey.isEmpty()) {
		
			this.ChatRoomKey = ChatRoomKey;
		
		}else {
			
			System.out.println("지정된 형식의 채팅방키가 아닙니다.: "+ChatRoomKey);
			
		}
		
		if(1<=userList.size()) {
			
			this.userList = userList;
				
		}else {
			
			System.out.println("채팅방에 참여중인 유저가 없으므로 채팅방이 생성되지 않았습니다..");
		}
		
		
	}
	
	
	//채팅방에 참여중인 사용자 아이디 리스트 getter setter
	public JsonArray getUserList() {
		if(userList.size()<1) {
			System.out.println("채팅방에 사용자가 없습니다.");
		}else {
			System.out.println("채팅방 사용자 목록 조회 성공"+userList);
		}
		
		return userList;
	}
	
	public void setUserList(JsonArray userList) {
		
		this.userList = userList;
	}
	
	
	//채팅방에 유저 아이디 추가
	public void addUser(String UserId) {
		JsonObject user = new JsonObject();
		user.addProperty("userId", UserId);
		
		if(!userList.contains(user)) {
			userList.add(user);	
			System.out.println("채팅방 사용자 추가");
		}else {
			System.out.println("채팅방에 이미존재 하는 사용자 입니다.");	
		}
		
	}
	
	
	//채팅방에서 사용자 삭제
	public void removeUser(String UserId) {
		JsonObject user = new JsonObject();
		user.addProperty("userId", UserId);
		
		if(userList.contains(user)) {
			userList.remove(user);	
			System.out.println("채팅방 사용자 삭제");
		}else {
			System.out.println("채팅방에 존재 하지않는 사용자 입니다.");	
		}	
	}
	
	
	//채팅방 구분키 getter setter 
	public String getChatRoomkey() {
		return ChatRoomKey;
	}
	
	public void setChatRoomKey(String ChatRoomKey) {
		
		this.ChatRoomKey = ChatRoomKey;
	
	}
	
	
	
}
