package com.javainuse.websocket.config;

import java.io.IOException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {

		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);
		if(jsonObject.get("user").equals("")){
			session.sendMessage(new TextMessage("Hello anonymous user , please tell us your identity!"));
		}else if(jsonObject.get("user").equals("info")){
			session.sendMessage(new TextMessage("This is an example using web socket . YAY !!!"));
		}else if(jsonObject.get("user").equals("keywords")){
			session.sendMessage(new TextMessage("Use : 1.info - basic information for this application \n" +
					"2.keywords - shows all automatic keyword you may use "));
		}else {
			session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
		}
	}

}