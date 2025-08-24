package com.redcoolhax.pebble;

import java.net.http.HttpClient;

public class HttpParsing {
    /**
     * Converts a String representing an HTTP version (as it shows up in an HTTP request 
     * or the httpVersionSelection JComboBox) to its equivalent HttpClient.Version enum value.
     * @param version A String representing an HTTP version as it shows up in an HTTP request.
     * @return The respective HttpClient.Version enum value.
     * @throws IllegalArgumentException If the provided String does not correspond to a valid 
     * HTTP version.
     */
    public static HttpClient.Version versionStringToEnum(String version) {
        switch (version) {
            case "HTTP/1.1" -> {return HttpClient.Version.HTTP_1_1;}
            case "HTTP/2" -> {return HttpClient.Version.HTTP_2;}
            default -> {throw new IllegalArgumentException(
                "Version: '" + version + "'' does not correspond to a valid HTTP version."
            );}
        }
    }
}