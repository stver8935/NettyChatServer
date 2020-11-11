package DTO;

import java.lang.annotation.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MessageDTO{

	
	//�޽����� ��� ä�ù�Ű
	private String chatRoomKey;
	
	//��ǰŰ
	private String productKey;
	
	//�޽����� ���� ������ ���� ����
	 private String messageType;
	
	//�޽��� ����
	private String message;
	
	// �޽����� �߼� �ð�
	private String messageTime;
	
	//������ ���̵�
	private String userId;
	

	//�Ʒ� �� ������ �޽��� Ÿ�Կ� ���� �������� ����.
	//��� �޽����� ��� ��ӽð�
	private String apmtTime;
	//��ӽð� ��ð����� �˶��� �ð��� ���� ���� 
	private String apmtNotiTime;
	
	//�ش� Ŭ������ jsonObject�� JsonObject������ String ���� ��ȯ���ֱ� ���� ����
	private Gson gson;
	
	
	//������ getter setter
	public void setUserId(String userid) {
		this.userId = userid;
	}
	
	public String getUserId() {
		return userId;
	}
	
	
	//ä�ù�Ű getter setter 
	public String getChatRoomKey() {
		return chatRoomKey;
	}

	public void setChatRoomKey(String chatRoomKey) {
		this.chatRoomKey = chatRoomKey;
	}

	
	//�ŷ��ϴ� ��ǰ��Ű getter setter 
	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	
	//�޽����� ���� getter setter 
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	
	//�޽��� ���� getter setter 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	//��� �޽��� �϶� ��ӽð� getter setter
	public String getApmtTime() {
		return apmtTime;
	}

	public void setApmtTime(String apmtTime) {
		this.apmtTime = apmtTime;
	}

	
	//��Ӹ޽��� �϶� ��ӽð����� ��ð����� �˶� ���� getter setter 
	public String getApmtNotiTime() {
		return apmtNotiTime;
	}

	public void setApmtNotiTime(String apmtNotiTime) {
		this.apmtNotiTime = apmtNotiTime;
	}


	//�޽��� ���� ���� getter setter
	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	
	//���� Ŭ���� json������ String ������ ��ȯ
	public String toJsonStrg() {
		return gson.toJson(this.getClass());
	}
	
	
	//��ä Ŭ���� jsonobj ���� ��ȯ
	public JsonObject toJsonObj() {
		return (JsonObject) gson.toJsonTree(this.getClass());
		}

}
