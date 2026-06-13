package org.javaparser.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
    String details,
    LocalDateTime timeStamp
    ){
}
