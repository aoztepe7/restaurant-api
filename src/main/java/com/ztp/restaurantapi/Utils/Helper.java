package com.ztp.restaurantapi.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Helper {
    public static String dateFormatter(String date) {
        String[] dates = date.split("-");
        StringBuilder sb = new StringBuilder();
        String[] formattedDate = new String[3];
        formattedDate[0] = dates[2] + '-';
        formattedDate[1] = dates[1] + '-';
        formattedDate[2] = dates[0];
        sb.append(formattedDate[0]);
        sb.append(formattedDate[1]);
        sb.append(formattedDate[2]);
        return sb.toString();
    }

    public static String dateFormatForDb(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6, 10);
        StringBuilder sb = new StringBuilder();
        sb.append(year).append('-').append(month).append('-').append(day);
        return sb.toString();
    }

}
