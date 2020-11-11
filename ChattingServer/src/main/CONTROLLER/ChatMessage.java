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
	
	
	// �Ѹ��� �޽��� ����
	public void sendMessage(String id, MessageDTO msg) {

		try {
			ChannelList.getChannelList().getChannel(id).writeAndFlush(msg + "\n");
		}catch(Exception e) {
			System.out.println("sendMessage Error : "+e.getMessage());
			e.printStackTrace();
		}
	
	
	
	}

	// ä�ù��� ��� ����ڿ��� �޽��� ����
	public void sendMsgRoom(String chatroomkey, MessageDTO msg) throws Exception {
		System.out.println("Send ChatMSG to ChatRoom => "
							+ "ChatRoomKey : "+chatroomkey);
		
		//DAO ä�ù濡 �ִ� ���� ����Ʈ�� �����´� �������� ä�ù� ������ �����´�.
		ChatRoomDAO chatroom = ChatRoomDAO.ChatRoomDAO();
		//���� ������ �������� DAO ��ü
		UserDAO user=UserDAO.UserDAO();
		
		//JsonArray ������ ä�ù濡 ���� �ִ� ���� ���̵� ����Ʈ
		JsonElement jemt= chatroom.getUserList(chatroomkey);
		System.out.println("user_List "+jemt);
		
		
		//JsonObject ������ �޽��� ��ü
		String msg_str=gson.toJson(msg);

		//���� ������ �������� ä�� ����Ʈ ��������
		ChannelList channellist= ChannelList.getChannelList();
		
		
			//ä�ù濡 �ִ� ������ ��ŭ �ݺ��ϸ鼭 ä�ü����� �����Ͽ� ä���� �����ִ� �������Ը� �޽����� �����Ѵ�.
			for(int i =0 ; i < jemt.getAsJsonArray().size(); i++) {
				
				String userId=jemt.getAsJsonArray().get(i)
						.getAsJsonObject().get("id").getAsString();
				
		
				if (channellist.getChannel(userId)!= null) {

					System.out.println("send MSG id : "+userId);
					
					channellist.getChannel(userId).writeAndFlush(msg_str + "\n");
				
				} else {
					//�ش������� ä���� �������� ����
					System.out.println("UserChannel dose not exist : " + userId);
				}
				
				
		}
					

		
	}
	

}
