package com.redcoolhax.pebble;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

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

    /**
     * Converts a HttpClient.Version enum to its respective String (as it shows up 
     * in an HTTP request).
     * @param version HttpClient.Version representation of an HTTP version.
     * @return The respective String as it shows up in an HTTP request.
     */
    public static String versionEnumToString(HttpClient.Version version) {
        return switch (version) {
            case HTTP_1_1 -> "HTTP/1.1";
            case HTTP_2 -> "HTTP/2";
        };
    }

    /**
     * Turns an HttpResponse into an easily readable, sorted section of text.
     * @param response A String HttpResponse
     * @return A readable String form of the response.
     */
    public static String responseToString(HttpResponse<String> response) {
        StringBuilder builder = new StringBuilder();
        builder.append("Version: " + versionEnumToString(response.version()) + "\n");
        builder.append("Status Code: " + response.statusCode() + "\n\n");
        builder.append("Headers:\n");
        response.headers().map().forEach((key, value) -> {
            builder.append(key + ": " + value + "\n");
        });
        builder.append("Body:\n" + response.body());
        return builder.toString();
    }
}