package com.lucas.challenge.builders.shared.messages;

public enum MessagesApiProperties {

    // Client - Error
    CLE001("CLE001"),

    // Success
    S001("S001"),
    S002("S002"),

    // Error
    E001("E001");


    MessagesApiProperties(String key) {
        this.key = key;
    }

    private final String key;

    public String getKey(){
        return this.key;
    }
}
