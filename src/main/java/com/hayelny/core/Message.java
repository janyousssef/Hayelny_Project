package com.hayelny.core;

import java.util.Map;

public record Message(Map<String, String> content) {
    public Message(String message) {
        this("message", message);
    }

    public Message(String msgName, String message) {
        this(Map.of(msgName, message));
    }
}
