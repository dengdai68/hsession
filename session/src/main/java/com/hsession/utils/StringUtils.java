package com.hsession.utils;

/**
 * Created by EX-HANJIANKAI557 on 2016/3/17.
 */
public class StringUtils {

    public static boolean isEmpty(String s){
        if(s == null || s.length() == 0){
            return true;
        }
        return false;
    }
}
