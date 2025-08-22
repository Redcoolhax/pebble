package com.redcoolhax.pebble;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class ResourceReading {
    public static byte[] readAsBytes(String resource) throws IOException {
        try (InputStream inStream = ResourceReading.class.getClassLoader().getResourceAsStream(resource)) {
            return inStream.readAllBytes();
        }
    }

    public static String readAsText(String resource) throws IOException, URISyntaxException {
        return new String(readAsBytes(resource), StandardCharsets.UTF_8);
    }
}