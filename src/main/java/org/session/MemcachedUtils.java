package org.session;

public class MemcachedUtils {
	
	private static Memcached memcachedClient = new Memcached();
	
	
	public static Object get(String key) {
        return memcachedClient.get(key);
    }

	public static void set(String key, int cacheTimeSeconds, Object o) {
         memcachedClient.set(key, cacheTimeSeconds, o);
    }
    
    public static void set(String key, Object o) {
        set(key,30,o);
    }

    public static void delete(String key) {
        memcachedClient.delete(key);
    }

    public static void main(String[] args){
    	MemcachedUtils.set("cccc", 1);
    	System.out.println(MemcachedUtils.get("cccc"));
    }
}
