package com.lucas.challenge.builders.config.exception;

import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;
import com.lucas.challenge.builders.shared.messages.MessagesApiSource;
import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    static final long serialVersionUID = 4445433003774424501L;

    private final String errorCode;
    private final String errorMessage;

    public ClientException(MessagesApiProperties messagesProperties) {
        super(MessagesApiSource.getInstance().message(messagesProperties.getKey()));
        this.errorCode = messagesProperties.getKey();
        this.errorMessage = MessagesApiSource.getInstance().message(messagesProperties.getKey());
    }

    public ClientException(MessagesApiProperties messagesProperties, Object... args){
        super(MessagesApiSource.getInstance().message(messagesProperties.getKey(), args));
        this.errorCode = messagesProperties.getKey();
        this.errorMessage = MessagesApiSource.getInstance().message(messagesProperties.getKey());
    }
}
