package com.joakim.cabinbookingbv.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }
        return dates;
    }
}

