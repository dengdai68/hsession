package com.hsession.cache;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheManager {

    private static final Logger logger = LoggerFactory.getLogger(CacheManager.class);

	private static Cache cache;

    private static int default_expiry = 60*60*24; //默认一天

    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = Cache.class.getResourceAsStream("/cache.properties");
            properties.load(resourceAsStream);
            cache = (Cache) Class.forName(properties.getProperty("cache.type")).newInstance();
        } catch (ClassNotFoundException e) {
            logger.error("cache init is error!,maybe cache.properties miss cache.type ",e);
        } catch (Exception e) {
            logger.error("cache init is error!",e);
        }

    }

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
