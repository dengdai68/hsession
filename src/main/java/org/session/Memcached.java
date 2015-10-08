package org.session;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class Memcached {
	
	private static MemCachedClient memcachedClient = new MemCachedClient();
	
	static{
        String [] addr ={"127.0.0.1:11211"};
        Integer [] weights = {3};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(addr);
        pool.setWeights(weights);
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(200);
        pool.setMaxIdle(1000*30*30);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(30);
        pool.setSocketConnectTO(0);
        pool.initialize();
	}
	
	public static Object get(String key) {
        return memcachedClient.get(key);
    }

	public static void set(String key, int cacheTimeSeconds, Object o) {
         memcachedClient.set(key, o, new Date(cacheTimeSeconds*1000));
    }
    
    public static void set(String key, Object o) {
        set(key,30,o);
    }

    public static void delete(String key) {
        memcachedClient.delete(key);
    }

}
