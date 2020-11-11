package CONTROLLER;


import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChannelList {
	
	//���� Ŭ������ �̱����������� �����ϱ� ���� ����
	private static ChannelList channelList;
	
	//����� ���̵�� ä�� ���̵� ã������ �ؽø�
	private static Map<String ,ChannelId> channelHash;
	
	//������ ��ϵ� ä�ε��� �����ϱ� ���� ä�� �׷�;
	private static ChannelGroup channelGroup;
	
	private static JsonElement log;
	private static Gson Gson;
	
	
	protected ChannelList() {
		
	}
	
	//�̱��� �������� ä�ù渮��Ʈ�� ����
    public static synchronized ChannelList getChannelList(){
    		
    
    		//ä�� ����Ʈ ����
    		if(channelList == null) {
    			channelList = new ChannelList();	
    			System.out.println("channelList : create");
    		}else {
    			System.out.println("channelList : already exists");	
    		}
    		//����� ���̵�� ä�� ���̵� �����ϱ� ���� �ؽø� ����
    		if(channelHash == null) {
    			channelHash = new HashMap<String, ChannelId>();
    			System.out.println("channelHash : create");
    		}else {
    			System.out.println("channelHash : already exists");	
    		}
    		
    		
    		//������� ä���� �����ϱ� ���� ä�� �׷� ����
    		if(channelGroup == null) {
    			channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);		
    			System.out.println("channelGroup : Create");
    		}else {
    			System.out.println("channelGroup : already exists");	
    		}
    
       	return channelList;
    }
    
    
    
    //����� ���̵�� ä�� ��ȯ
    
    public  Channel getChannel(String id) {
    	
    System.out.println("getchannel : "+id);
   	
	    if(channelHash.containsKey(id)) {
			return channelGroup.find(channelHash.get(id));
	    }
	    
	    return null;
    
    }
    
    
    //ä�� �׷� ��ȯ
    public ChannelGroup getChannelGroup() {
    	
    		return channelGroup;
    }
    
  //ä�� ���̵�� ä�� ��ȯ
    public  Channel getChannel(ChannelId channelId) {
    	
    	return channelGroup.find(channelId);	
    
    }
    
    
    //���̵�� ä�� ����
    public void removeChannel(String id) {
    	
    	try {
        	channelGroup.remove(channelHash.get(id));
        	channelHash.remove(id);
    	}
    	catch(Exception e){
    		System.out.println("removeChannel : "+e.getMessage());
    		e.printStackTrace();
    	}
    }
  //ä�� ��ü�� ä�� ����
    public void removeChannel(Channel channel) {
    	
    	try {
        	channelGroup.remove(channel);
        	
        	for(String id : channelHash.keySet()) {
        			
        		if(channelHash.get(id).equals(channel.id())) {
        			channelHash.remove(id);
            	}	
        	}
        	
    	}
    	catch(Exception e){
    		System.out.println("removeChannel Error : "+e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    //ä�ΰ� ���̵�� ä���� ã�� ���� �ؽ� �� �߰�
    public void addChannel(String id , Channel channel) {
    	
    	try {
        	channelHash.put(id, channel.id());
        	channelGroup.add(channel);
    	}
    	catch(Exception e){
    		System.out.println("addChannel Error : "+e.getMessage());
    		e.printStackTrace();
    		
    	}
    	
    }
    
  //ä�θ� �߰�
    public void addChannel(Channel channel) {
    	
    	try {
        	channelGroup.add(channel);	
    	}
    	catch(Exception e){
    		System.out.println("addChannel Error : "+e.getMessage());
    		e.printStackTrace();
    	}
    	
    }
    
    
	
}
