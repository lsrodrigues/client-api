package com.lucas.challenge.builders.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;
import com.lucas.challenge.builders.shared.messages.MessagesApiSource;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseDTO {

    private String statusCode;

    private String statusMessage;

    private LocalDateTime timestamp;

    @JsonIgnore
    public void setMessagesApiProperties(MessagesApiProperties messagesProperties, Object... args) {
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
        this.statusCode = messagesProperties.name();
        this.statusMessage = MessagesApiSource.getInstance().message(messagesProperties.name(), args);
    }

    @JsonIgnore
    public void setMessagesApiProperties(MessagesApiProperties messagesProperties) {
        this.setMessagesApiProperties(messagesProperties, null);
    }

}
