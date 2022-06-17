package com.lucas.challenge.builders.config.exception;

import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;

public class NotFoundException extends ClientException {

    public NotFoundException(MessagesApiProperties messagesProperties) {
        super(messagesProperties);
    }
}
