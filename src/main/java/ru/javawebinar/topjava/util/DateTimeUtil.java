package ru.javawebinar.topjava.util;

import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDateTime;

public class DateTimeUtil {

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.toString().replace("T", " ");
    }

    public static String toString(LocalDateTime dateTime) {
        return formatDateTime(dateTime);
    }
}