package org.chescript.krana.server.configuration;

import org.chescript.krana.web.common.KranaMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KranaMessageHandler extends TextWebSocketHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Odbieranie wiadomości
        KranaMessage receivedMessage = objectMapper.readValue(message.getPayload(), KranaMessage.class);

        // Przetwarzanie wiadomości, np. logowanie
        System.out.println("Received message: " + receivedMessage.getMsg());

        // Odpowiedź do klienta (opcjonalnie)
        KranaMessage replyMessage = new KranaMessage("response", "Received your message: " + receivedMessage.getMsg());
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(replyMessage)));
    }
	
}
