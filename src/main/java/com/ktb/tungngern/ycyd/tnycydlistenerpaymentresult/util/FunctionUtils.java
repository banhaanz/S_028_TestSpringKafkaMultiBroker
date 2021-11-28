package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.util;

import org.apache.logging.log4j.util.Strings;

public class FunctionUtils {

    public static String formatCustomerName(String customerName) {
        if(Strings.isBlank(customerName)) {
            return "";
        }
        String[] parts = customerName.split(" ");
        String strTrim = trimName(parts[parts.length - 1]);
        parts[parts.length - 1] = String.format("%s.", strTrim);
        String formatStr = "";
        for (int i = 0; i <= parts.length - 1; i++) {
            if (parts.length == 1) {
                formatStr = String.format("%s", customerName);
            } else {
                formatStr = String.format("%s %s", parts[0], parts[i]);
            }
        }
        return formatStr.trim();
    }

    public static String trimName(String s) {
        Integer idx = 3;
        if (s.length() <= 3) {
            idx = 1;
        }
        return s.substring(0, idx);

    }
    
}
