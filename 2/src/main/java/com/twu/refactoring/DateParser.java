package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateTimeString - should be in format ISO 8601 format
     *                       examples -
     *                       2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                       2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                       2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;
        year = getDateTimeInfo(0, 4, ErrorMessage.YEAR_STRING_IS_LESS_THAN_4_CHARACTERS, ErrorMessage.YEAR_IS_NOT_AN_INTEGER);
        checked(year, 2012, 2000, ErrorMessage.YEAR_CANNOT_BE_LESS_THAN_2000_OR_MORE_THAN_2012);
        month = getDateTimeInfo(5, 7, ErrorMessage.MONTH_STRING_IS_LESS_THAN_2_CHARACTERS, ErrorMessage.MONTH_IS_NOT_AN_INTEGER);
        checked(month, 12, 1, ErrorMessage.MONTH_CANNOT_BE_LESS_THAN_1_OR_MORE_THAN_12);
        date = getDateTimeInfo(8, 10, ErrorMessage.DATE_STRING_IS_LESS_THAN_2_CHARACTERS, ErrorMessage.DATE_IS_NOT_AN_INTEGER);
        checked(date, 31, 1, ErrorMessage.DATE_CANNOT_BE_LESS_THAN_1_OR_MORE_THAN_31);
        if (dateTimeString.charAt(11) == 'Z') {
            hour = 0;
            minute = 0;
        } else {
            hour = getDateTimeInfo(11, 13, ErrorMessage.HOUR_STRING_IS_LESS_THAN_2_CHARACTERS, ErrorMessage.HOUR_IS_NOT_AN_INTEGER);
            checked(hour, 23, 0, ErrorMessage.HOUR_CANNOT_BE_LESS_THAN_0_OR_MORE_THAN_23);
            minute = getDateTimeInfo(14, 16, ErrorMessage.MINUTE_STRING_IS_LESS_THAN_2_CHARACTERS, ErrorMessage.MINUTE_IS_NOT_AN_INTEGER);
            checked(minute, 59, 0, ErrorMessage.MINUTE_CANNOT_BE_LESS_THAN_0_OR_MORE_THAN_59);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private void checked(int info, int lower, int higher, String errorMessage) {
        if (info < higher || info > lower)
            throw new IllegalArgumentException(errorMessage);
    }

    private int getDateTimeInfo(int beginIndex, int endIndex, String yearStringIsLessThan4Characters, String yearIsNotAnInteger) {
        int info;
        try {
            info = getDateTimePart(beginIndex, endIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(yearStringIsLessThan4Characters);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(yearIsNotAnInteger);
        }
        return info;
    }

    private int getDateTimePart(int beginIndex, int endIndex) {
        int info;
        String yearString = dateTimeString.substring(beginIndex, endIndex);
        info = Integer.parseInt(yearString);
        return info;
    }
}
