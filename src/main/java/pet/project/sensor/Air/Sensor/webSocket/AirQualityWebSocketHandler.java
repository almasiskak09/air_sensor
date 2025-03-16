package pet.project.sensor.Air.Sensor.webSocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@RequiredArgsConstructor
public class AirQualityWebSocketHandler extends AbstractWebSocketHandler {

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    public void sendToWebSocket(CitiesDto citiesDto){
        try {
            String json = objectMapper.writeValueAsString(citiesDto);
            for(WebSocketSession session : sessions){
                if(session.isOpen()){
                    session.sendMessage(new TextMessage(json));
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
