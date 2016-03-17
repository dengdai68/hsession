package com.hsession.session;

import java.io.Serializable;
import java.util.*;

/**
 * Created by EX-HANJIANKAI557 on 2016/3/17.
 */
public class HSessionAttribute implements Serializable{
    private Map<String,Object> map = new HashMap<String, Object>();

    public void setAttribute(String s, Object o) {
        this.map.put(s,o);
    }
    public void removeAttribute(String s) {
        this.map.remove(s);
    }
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(this.map.keySet());
    }

    public Object getAttribute(String s) {
        return map.get(s);
    }

    public void removeAllAttribute() {
        this.map.clear();
    }
}
