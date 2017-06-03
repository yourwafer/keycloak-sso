package com.demo;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Date;

public class LocalDateTest {
    @Test
    public void name() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.toInstant(ZoneOffset.ofHours(8));
        System.out.println(instant.getEpochSecond());
        Date date = new Date();
        System.out.println(date.getTime());

    }
}
