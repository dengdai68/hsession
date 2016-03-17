package com.hsession.cache;

/**
 * Created by EX-HANJIANKAI557 on 2016/3/7.
 */
public interface Cache {

    /**
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * @param key
     * @param value
     * @param expiry
     * @return
     */
    boolean set(String key, Object value, int expiry);
    boolean add(String key, Object value, int expiry);

}
