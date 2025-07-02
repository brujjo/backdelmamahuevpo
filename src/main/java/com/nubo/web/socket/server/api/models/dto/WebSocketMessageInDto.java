package com.nubo.web.socket.server.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessageInDto {

    private String statusCode;
    private String timestamp;
    private String service;
    private String messageDescription;
    private String clientUser;
    private String errorMessage;

}
