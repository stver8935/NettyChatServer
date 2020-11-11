package CONTROLLER;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import DAO.ChatRoomDAO;
import DAO.UserDAO;
import DTO.MessageDTO;
import io.netty.channel.Channel;

public class ChatMessage extends ChannelList {

	private Gson gson;


	public ChatMessage() {
		super.getChannelList();
		gson =new Gson();
	
	}
	
	
	// 한명에게 메시지 전송
	public void sendMessage(String id, MessageDTO msg) {

		try {
			ChannelList.getChannelList().getChannel(id).writeAndFlush(msg + "\n");
		}catch(Exception e) {
			System.out.println("sendMessage Error : "+e.getMessage());
			e.printStackTrace();
		}
	
	
	
	}

	// 채팅방의 모든 사용자에게 메시지 전송
	public void sendMsgRoom(String chatroomkey, MessageDTO msg) throws Exception {
		System.out.println("Send ChatMSG to ChatRoom => "
							+ "ChatRoomKey : "+chatroomkey);
		
		//DAO 채팅방에 있는 유저 리스트를 가져온다 서버에서 채팅방 정보를 가져온다.
		ChatRoomDAO chatroom = ChatRoomDAO.ChatRoomDAO();
		//유저 정보를 가져오는 DAO 객체
		UserDAO user=UserDAO.UserDAO();
		
		//JsonArray 형태의 채팅방에 들어와 있는 유저 아이디 리스트
		JsonElement jemt= chatroom.getUserList(chatroomkey);
		System.out.println("user_List "+jemt);
		
		
		//JsonObject 형태의 메시지 객체
		String msg_str=gson.toJson(msg);

		//현재 서버의 접속중인 채널 리스트 가져오기
		ChannelList channellist= ChannelList.getChannelList();
		
		
			//채팅방에 있는 유저수 만큼 반복하면서 채팅서버에 접속하여 채널을 갖고있는 유저에게만 메시지를 전송한다.
			for(int i =0 ; i < jemt.getAsJsonArray().size(); i++) {
				
				String userId=jemt.getAsJsonArray().get(i)
						.getAsJsonObject().get("id").getAsString();
				
		
				if (channellist.getChannel(userId)!= null) {

					System.out.println("send MSG id : "+userId);
					
					channellist.getChannel(userId).writeAndFlush(msg_str + "\n");
				
				} else {
					//해당유저의 채널이 존재하지 않음
					System.out.println("UserChannel dose not exist : " + userId);
				}
				
				
		}
					

		
	}
	

}
