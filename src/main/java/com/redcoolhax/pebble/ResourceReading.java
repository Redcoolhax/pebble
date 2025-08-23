package com.redcoolhax.pebble;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Class with static methods for reading resource files.
 */
public class ResourceReading {
    /**
     * Reads a resource file as an array of bytes.
     * @param resource The path to the resource file.
     * @return The file's data represented as bytes.
     * @throws IOException If there is an error while trying to read the file.
     */
    public static byte[] readAsBytes(String resource) throws IOException {
        try (InputStream inStream = ResourceReading.class.getClassLoader().getResourceAsStream(resource)) {
            return inStream.readAllBytes();
        }
    }

    /**
     * Reads a resource file as a String.
     * @param resource The path to the resource file.
     * @return The file's data, decoded to UTF-8 format and represented as a String.
     * @throws IOException If there is an error while trying to read the file.
     */
    public static String readAsText(String resource) throws IOException {
        return new String(readAsBytes(resource), StandardCharsets.UTF_8);
    }
}