package com.hsession.cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class MemCached implements Cache {
    private static final Logger logger = Logger.getLogger(MemCached.class);
    boolean init = false;
	private MemCachedClient memcachedClient ;
    private void load(){
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = Cache.class.getResourceAsStream("/app.properties");
            properties.load(resourceAsStream);
            String [] servers = properties.getProperty("").split(",");
            String [] weights = properties.getProperty("").split(",");
            Integer[] weights_ = new Integer[weights.length];
            for(int i=0;i<weights.length;i++){
                weights_[i] = Integer.valueOf(weights[i]);
            }
            SockIOPool pool = SockIOPool.getInstance();
            pool.setServers(servers);
            pool.setWeights(weights_);
            pool.setInitConn(5);
            pool.setMinConn(5);
            pool.setMaxConn(200);
            pool.setMaxIdle(1000*30*30);
            pool.setMaintSleep(30);
            pool.setNagle(false);
            pool.setSocketTO(30);
            pool.setSocketConnectTO(0);
            pool.initialize();
            this.memcachedClient = new MemCachedClient();
            this.init = true;
        }catch (Exception e){
            logger.error("memcached init error!");
        }
    }

	public Object get(String key) {
        if(!this.init){
            load();
        }
        return memcachedClient.get(key);
    }

    @Override
    public boolean set(String key, Object value, int expiry) {
        if(!this.init){
            load();
        }
        return memcachedClient.set(key,value,new Date(expiry));
    }

    @Override
    public boolean add(String key, Object value, int expiry) {
        if(!this.init){
            load();
        }
        return memcachedClient.add(key,value,new Date(expiry));
    }
}
