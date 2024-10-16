package org.chescript.krana.client.configuration;

import org.chescript.krana.KranaMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketClientConfig {

    @Bean
    public WebSocketConnectionManager connectionManager() {
        // Tworzymy połączenie WebSocket z użyciem managera
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
            new StandardWebSocketClient(), 
            new KranaMessageHandler(), 
            "ws://localhost:8123/ws/message"
        );
        manager.setAutoStartup(true); // Automatyczne nawiązanie połączenia przy uruchomieniu aplikacji
        return manager;
    }
}
