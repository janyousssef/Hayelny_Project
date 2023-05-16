package com.hayelny.core.images;

import java.util.Objects;

public class ImageDTO {
    private final String message;

    public ImageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ImageDTO) obj;
        return Objects.equals(this.message,
                              that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ImageDTO[" +
                "message=" + message + ']';
    }

}
