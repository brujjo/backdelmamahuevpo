package com.nubo.web.socket.server.api.service.impl;

import com.nubo.web.socket.server.api.models.dto.WebSocketMessageInDto;
import com.nubo.web.socket.server.api.service.WebSocketMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketMessageServiceImpl implements WebSocketMessageService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notifyUser(String username, WebSocketMessageInDto message) {
        messagingTemplate.convertAndSend("/topic/user/" + username, message);
    }

    @Override
    public void notifyService(String service, WebSocketMessageInDto message) {
        messagingTemplate.convertAndSend("/topic/service/" + service, message);
    }

}