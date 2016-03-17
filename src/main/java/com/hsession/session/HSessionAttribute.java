package com.hsession.session;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EX-HANJIANKAI557 on 2016/3/17.
 */
public class HSessionAttribute implements Serializable{
    private Map<String,Serializable> map = new HashMap<String, Serializable>();

    public void setAttribute(String s, Object o) {

    }
    public void removeAttribute(String s) {

    }
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public Object getAttribute(String s) {
        return null;
    }

}
