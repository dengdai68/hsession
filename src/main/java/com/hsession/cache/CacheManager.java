package com.hsession.cache;

import org.cache.*;
import org.cache.MemCached;

public class CacheManager {
	
	private static org.cache.Cache cache = new MemCached();
    private static int default_expiry = 60*60*24;

    public static Object get(String key) {
        return cache.get(key);
    }

    public static boolean set(String key, Object value){
        return cache.set(key,value,default_expiry);
    }

    public static boolean add(String key, Object value) {
        return cache.set(key,value,default_expiry);
    }

	public static boolean set(String key, Object value, int expiry) {
        return cache.set(key,value,expiry);
    }
    public static boolean add(String key, Object value, int expiry) {
        return cache.set(key,value,expiry);
    }
}
