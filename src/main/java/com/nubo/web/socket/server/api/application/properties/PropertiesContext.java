package com.nubo.web.socket.server.api.application.properties;

import com.nubo.web.socket.server.api.application.context.GlobalContext;
import com.nubo.web.socket.server.api.application.util.ApplicationUtils;
import com.nubo.web.socket.server.api.models.enums.PropertiesEnum;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PropertiesContext {

    private PropertiesContext() {
    }

    public static String getClientIp() {
        return GlobalContext.getProperty(PropertiesEnum.CLIENT_IP.getValue());
    }

    public static String getClientUser() {
        return GlobalContext.getProperty(PropertiesEnum.CLIENT_USER.getValue());
    }

    public static LocalDateTime getClientDatetime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationUtils.getFormatDatetime());
        String clientDatetime = GlobalContext.getProperty(PropertiesEnum.CLIENT_DATETIME.getValue());
        if (ObjectUtils.isEmpty(clientDatetime)) {
            return LocalDateTime.now();
        }
        return LocalDateTime.parse(clientDatetime, formatter);
    }

    public static String getVersion() {
        return GlobalContext.getProperty(PropertiesEnum.VERSION_API.getValue());
    }

    public static String getAuthToken() {
        return GlobalContext.getProperty(PropertiesEnum.AUTH_TOKEN.getValue());
    }

}

