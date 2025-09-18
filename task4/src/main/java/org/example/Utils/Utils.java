package org.example.Utils;

import java.util.Set;

public class Utils {

    public static <T> String setToString(Set<T> set){
        StringBuilder sb = new StringBuilder();
        if(set == null || set.isEmpty()){
            return "0 items []";
        }

        sb.append(set.size()).append(" items [");
        set.forEach(item -> sb.append(item).append(", "));
        sb.setLength(sb.length() - 2);
        sb.append("]");

        return sb.toString();
    }
}
