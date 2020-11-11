package CONTROLLER;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import DAO.UserDAO;
import DTO.UserDTO;

public class User {

	private String id; 
	private Gson gson;
	
	//가져올 유저의 아이디를 생성자로 넣어줌
	public User(String id) {
		this.id = id;	
	}
	
	
	//유저의 계정정보 가져오기
	public UserDTO getInfo() {
		 UserDAO user_dao=UserDAO.UserDAO();
		 
		JsonElement user_elmt=user_dao.getUser(id);
		UserDTO user = gson.fromJson(user_elmt, UserDTO.class);
		
		//dao 객체 반환
		user_dao=null;
		return user;
	}
	
	
	
}
