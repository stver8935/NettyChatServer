package CONTROLLER;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import DAO.UserDAO;
import DTO.UserDTO;

public class User {

	private String id; 
	private Gson gson;
	
	//������ ������ ���̵� �����ڷ� �־���
	public User(String id) {
		this.id = id;	
	}
	
	
	//������ �������� ��������
	public UserDTO getInfo() {
		 UserDAO user_dao=UserDAO.UserDAO();
		 
		JsonElement user_elmt=user_dao.getUser(id);
		UserDTO user = gson.fromJson(user_elmt, UserDTO.class);
		
		//dao ��ü ��ȯ
		user_dao=null;
		return user;
	}
	
	
	
}
