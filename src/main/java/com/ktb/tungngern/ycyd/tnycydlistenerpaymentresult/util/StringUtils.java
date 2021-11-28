package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.util;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

    public static String replace(String input, String[] keys, String[] vals) {
        for (int i = 0; i < keys.length; i++) {
            input = input.replace(keys[i], vals[i]);
        }
        return input;
    }

    public static String currency(String input) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(Double.valueOf(input));
    }

    public static String nowDatetime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }
  
    public static String formatPayerName(String payerName) {
        var name = payerName.trim().split(" ");
        name[name.length - 1] = trimName(name[name.length - 1]) + ".";
        return Arrays.stream(name).collect(Collectors.joining(" "));
    }
    
    public static String trimName(String s) {
        int idx = 3;
        if (s.length() <= 3) {
            idx = 1;
        }
        return s.substring(0, idx);
    }
    
}
