package com.lucas.challenge.builders.shared.messages;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class MessagesApiSource {

    private static final String PATH_MESSAGES = "messages/api/messages";
    private static final ReloadableResourceBundleMessageSource MESSAGE_SOURCE = new ReloadableResourceBundleMessageSource();

    private static MessagesApiSource instance;

    private MessagesApiSource() {

    }

    public static synchronized MessagesApiSource getInstance() {
        if (instance == null) {
            instance = new MessagesApiSource();
        }
        return instance;
    }

    public String message(String key) {
        return MESSAGE_SOURCE.getMessage(key, null, Locale.ROOT);
    }

    public String message(String key, Object[] args) {
        return MESSAGE_SOURCE.getMessage(key, args, Locale.ROOT);
    }

    static {
        MESSAGE_SOURCE.setCacheSeconds(3600);
        MESSAGE_SOURCE.setDefaultLocale(Locale.ROOT);
        MESSAGE_SOURCE.setDefaultEncoding(StandardCharsets.UTF_8.name());
        MESSAGE_SOURCE.setFallbackToSystemLocale(true);
        MESSAGE_SOURCE.setBasename(PATH_MESSAGES);
    }
}
