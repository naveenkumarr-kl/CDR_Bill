package com.example.cdr_bill.config;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter extends AbstractBeanField<LocalDateTime, String> {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("M/d/yyyy H:mm");

    @Override
    protected LocalDateTime convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(value.trim(), formatter);
    }
}