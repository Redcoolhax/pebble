# Pebble
Pebble is a GUI-based HTTP client tool that allows the user to quickly build and test text-based HTTP exchanges.

## Features
- Very lightweight and simple GUI, with all necessary components immediately visible to the user.
- Easy-to-navigate text windows for reading HTTP responses and warning messages.

## Planned Features
- Provide 'context' for certain components by clicking on their respective labels, making the application more beginner-friendly, especially to those who are learning HTTP.
- Better error handling. Although Pebble can produce error messages to warn the user that something has gone wrong, these can be somewhat complicated, and future updates will focus on providing a more intuitive experience for the user.
- Possibly make the HTTP responses more closely resemble their conventional format, depending on user feedback.

## Limitations
- Pebble can only send and receive content in text-based format (such as plain text, html, json, etc). At this time, there are no plans to support other forms of content such as audio or images.

## Dependencies
- Java 17
- Maven

## How to Run
When building from source, the easiest way is to use the exec-maven-plugin. These two commands will compile the Java files into class files, and then run the application with dependencies included:
```bash
mvn compile
mvn exec:java
```

## License
This project is licensed under the [MIT License](LICENSE) © 2025 Redcoolhax.