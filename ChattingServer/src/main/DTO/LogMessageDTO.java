package DTO;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class LogMessageDTO {
	private Gson gson;
	private JsonElement msg;
	
	public void addLog(String title , String message) {
		msg.getAsJsonObject().addProperty(title, message);
	}
	
	public String getLog() {
	
		return gson.toJson(msg);
	}
	
}
