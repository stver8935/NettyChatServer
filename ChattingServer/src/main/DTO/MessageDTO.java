package DTO;

import java.lang.annotation.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MessageDTO{

	
	//메시지에 담긴 채팅방키
	private String chatRoomKey;
	
	//상품키
	private String productKey;
	
	//메시지의 종류 구분을 위한 변수
	 private String messageType;
	
	//메시지 내용
	private String message;
	
	// 메시지가 발송 시간
	private String messageTime;
	
	//전송자 아이디
	private String userId;
	

	//아래 두 변수는 메시지 타입에 따라 없을수도 있음.
	//약속 메시지일 경루 약속시간
	private String apmtTime;
	//약속시간 몇시간전에 알람을 시간을 위한 변수 
	private String apmtNotiTime;
	
	//해당 클래스를 jsonObject나 JsonObject형태의 String 으로 변환해주기 위한 변수
	private Gson gson;
	
	
	//전송자 getter setter
	public void setUserId(String userid) {
		this.userId = userid;
	}
	
	public String getUserId() {
		return userId;
	}
	
	
	//채팅방키 getter setter 
	public String getChatRoomKey() {
		return chatRoomKey;
	}

	public void setChatRoomKey(String chatRoomKey) {
		this.chatRoomKey = chatRoomKey;
	}

	
	//거래하는 제품의키 getter setter 
	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	
	//메시지의 유형 getter setter 
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	
	//메시지 내용 getter setter 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	//약속 메시지 일때 약속시간 getter setter
	public String getApmtTime() {
		return apmtTime;
	}

	public void setApmtTime(String apmtTime) {
		this.apmtTime = apmtTime;
	}

	
	//약속메시지 일때 약속시간에서 몇시간전에 알람 줄지 getter setter 
	public String getApmtNotiTime() {
		return apmtNotiTime;
	}

	public void setApmtNotiTime(String apmtNotiTime) {
		this.apmtNotiTime = apmtNotiTime;
	}


	//메시지 전송 시작 getter setter
	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	
	//현재 클래스 json형태의 String 값으로 반환
	public String toJsonStrg() {
		return gson.toJson(this.getClass());
	}
	
	
	//현채 클래스 jsonobj 으로 반환
	public JsonObject toJsonObj() {
		return (JsonObject) gson.toJsonTree(this.getClass());
		}

}
