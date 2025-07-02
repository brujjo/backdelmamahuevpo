package com.nubo.web.socket.server.api.web.controller;


import com.nubo.web.socket.server.api.models.dto.WebSocketMessageInDto;
import com.nubo.web.socket.server.api.service.WebSocketMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Tag(name = "WebSocket")
@RestController
@RequestMapping("/{version}/ws")
@RequiredArgsConstructor
public class WebSocketMessageRestController {

    private final WebSocketMessageService webSocketMessageService;

    @Operation(
            summary = "Send message to a WebSocket user channel",
            description = "Sends a message to all clients subscribed to a specific WebSocket user channel."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message sent successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/notify/user")
    public ResponseEntity<String> notifyUser(@RequestBody WebSocketMessageInDto message) {

        webSocketMessageService.notifyUser(message.getClientUser(), message);

        String safeUser = HtmlUtils.htmlEscape(message.getClientUser());

        return ResponseEntity.ok("Message sent to user channel " + safeUser);

    }

    @Operation(
            summary = "Send message to a WebSocket service channel",
            description = "Sends a message to all clients subscribed to a specific WebSocket service channel."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message sent successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/notify/service")
    public ResponseEntity<String> notifyService(@RequestBody WebSocketMessageInDto message) {

        webSocketMessageService.notifyService(message.getService(), message);

        String safeService = HtmlUtils.htmlEscape(message.getService());

        return ResponseEntity.ok("Message sent to service channel " + safeService);
    }
}