package org.chescript.krana;

import org.chescript.krana.web.common.KranaMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KranaMessageHandler extends TextWebSocketHandler {

	private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Wysłanie wiadomości do serwera
        KranaMessage message = new KranaMessage("greeting", "Hello, server!");
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Odbieranie wiadomości od serwera
        KranaMessage receivedMessage = objectMapper.readValue(message.getPayload(), KranaMessage.class);
        System.out.println("Received from server: " + receivedMessage.getMsg());
    }

}
