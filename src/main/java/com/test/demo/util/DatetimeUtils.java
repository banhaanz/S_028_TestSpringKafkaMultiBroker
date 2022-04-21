package com.test.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DatetimeUtils {

    public static final String D_MMM = "d MMM";
    public static final String D_MMM_YY = "d MMM yy";
    public static final String THAI_DATETIME_RANGE_FORMAT = "%s - %s";
    public static final String D_MMM_YY_HH_MM_SS = "d MMM yy HH:mm:ss น.";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final Locale thaiLocal = new Locale("th", "TH");
    ZoneId zoneId = ZoneId.systemDefault();

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static String asString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(df);
    }

    public static String asString(LocalDate localDate, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(df);
    }

    public static LocalDateTime asLocalDateTime(String dateTimeStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, df);
    }

    public static LocalDate asLocalDate(String dateStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, df);
    }

    public static String convertPattern(String dateTimeStr, String fromPattern, String toPattern) {
        DateTimeFormatter fromDf = DateTimeFormatter.ofPattern(fromPattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, fromDf);
        DateTimeFormatter toDf = DateTimeFormatter.ofPattern(toPattern);
        return localDateTime.format(toDf);
    }

    public static String formatThaiDate(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withChronology(ThaiBuddhistChronology.INSTANCE)
                .localizedBy(thaiLocal);
        return localDateTime.format(formatter);
    }

    public static String formatRangeThaiDate(LocalDate LocalDate1, String pattern1, LocalDate LocalDate2, String pattern2) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(pattern1)
                .withChronology(ThaiBuddhistChronology.INSTANCE)
                .localizedBy(thaiLocal);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2)
                .withChronology(ThaiBuddhistChronology.INSTANCE)
                .localizedBy(thaiLocal);
        return String.format(THAI_DATETIME_RANGE_FORMAT, LocalDate1.format(formatter1), LocalDate2.format(formatter2));
    }
}
