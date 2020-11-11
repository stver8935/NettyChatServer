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
	
	//현재 클래스를 싱글턴패턴으로 생성하기 위한 변수
	private static ChannelList channelList;
	
	//사용자 아이디로 채널 아이디를 찾기위한 해시맵
	private static Map<String ,ChannelId> channelHash;
	
	//서버에 등록된 채널들을 관리하기 위한 채널 그룹;
	private static ChannelGroup channelGroup;
	
	private static JsonElement log;
	private static Gson Gson;
	
	
	protected ChannelList() {
		
	}
	
	//싱글턴 패턴으로 채팅방리스트에 접근
    public static synchronized ChannelList getChannelList(){
    		
    
    		//채널 리스트 생성
    		if(channelList == null) {
    			channelList = new ChannelList();	
    			System.out.println("channelList : create");
    		}else {
    			System.out.println("channelList : already exists");	
    		}
    		//사용자 아이디로 채널 아이디 접근하기 위한 해시맵 생성
    		if(channelHash == null) {
    			channelHash = new HashMap<String, ChannelId>();
    			System.out.println("channelHash : create");
    		}else {
    			System.out.println("channelHash : already exists");	
    		}
    		
    		
    		//사용자의 채널을 관리하기 위한 채널 그룹 생성
    		if(channelGroup == null) {
    			channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);		
    			System.out.println("channelGroup : Create");
    		}else {
    			System.out.println("channelGroup : already exists");	
    		}
    
       	return channelList;
    }
    
    
    
    //사용자 아이디로 채널 반환
    
    public  Channel getChannel(String id) {
    	
    System.out.println("getchannel : "+id);
   	
	    if(channelHash.containsKey(id)) {
			return channelGroup.find(channelHash.get(id));
	    }
	    
	    return null;
    
    }
    
    
    //채널 그룹 반환
    public ChannelGroup getChannelGroup() {
    	
    		return channelGroup;
    }
    
  //채널 아이디로 채널 반환
    public  Channel getChannel(ChannelId channelId) {
    	
    	return channelGroup.find(channelId);	
    
    }
    
    
    //아이디로 채널 삭제
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
  //채널 자체로 채널 삭제
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
    
    //채널과 아이디로 채널을 찾기 위한 해시 맵 추가
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
    
  //채널만 추가
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
