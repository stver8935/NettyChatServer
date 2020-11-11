package Run;

import com.google.gson.*;

import CONTROLLER.ChannelList;
import CONTROLLER.ChatMessage;
import DAO.ChatRoomDAO;
import DTO.MessageDTO;

import java.io.IOException;
import java.util.Map;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;



public class ServerHandler extends ChannelInboundHandlerAdapter {


    
    //�޽����� json ���·� �����ϱ����� Gson ��ü
	private Gson gson =new Gson();
	private ChannelList channelList = ChannelList.getChannelList();
	
	

	

    //����ڰ� �߰��Ǿ����� ȣ��Ǵ� �Լ�
	

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
   
        System.out.println("handlerAdded of [SERVER]"+incoming.id());
        
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // ����ڰ� �������� �� ������ ǥ��.
        Channel coming_user_chanel=ctx.channel();
        ctx.channel().read();

        System.out.println("User Access! ip"+coming_user_chanel.remoteAddress()+"\nchanel+id"+coming_user_chanel.id());
        System.out.println("Access meta"+coming_user_chanel.metadata());
        
    }





    //����ڰ� �������� ȣ��Ǵ� �Լ�
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("handlerRemoved of [SERVER]");
        channelList.removeChannel(ctx.channel());
    
    }


    //������ ������ �Ϸ� �Ǿ����� ȣ�� �Ǵ� �Լ�
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    //����ڰ� ������ �����͸� ���������� �����͸� �޾Ƶ��̴� �Լ�
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        Channel incoming = ctx.channel();
        
        
    
        MessageDTO msg = gson.fromJson((String) message, MessageDTO.class);
        
        
        //�߰� ���� 
        System.out.println("MSG_OBJ"+message);
        System.out.println("userId : "+msg.getUserId());
        System.out.println("message : "+msg.getMessage());
        
        

        
        //������ ä�ΰ� �ش� ä�� ������� ���̵� �߰��� ������Ʈ ���ش�.
        channelList.addChannel(msg.getUserId(),incoming);

        //ä�� ������Ʈ�� ���� �޽��� �϶� �޽��� ���� ���� �ʴ´�.
        if(msg.getMessageType().equals("channelupdate")) {
        	System.out.println("channel update");
        }else {
        	try { 
    			ChatMessage sendMsg =new ChatMessage();
    			sendMsg.sendMsgRoom(msg.getChatRoomKey(), msg);
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println("ä�ù� ���� ���� " +e.getMessage());
    		}	
        }

	}
	
	

}

