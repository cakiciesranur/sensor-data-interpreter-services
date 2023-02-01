package com.eny.sensordatainterpreter.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtil {

    public static LocalDateTime stringToLocalDateTime(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}
