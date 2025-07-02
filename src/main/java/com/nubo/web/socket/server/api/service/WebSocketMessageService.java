package com.nubo.web.socket.server.api.service;

import com.nubo.web.socket.server.api.models.dto.WebSocketMessageInDto;

public interface WebSocketMessageService {

    void notifyUser(String username, WebSocketMessageInDto message);

    void notifyService(String service, WebSocketMessageInDto message);

}