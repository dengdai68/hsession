package com.hsession.session;

import java.io.Serializable;

/**
 * Created by EX-HANJIANKAI557 on 2016/3/17.
 */
public class HSessionHeader implements Serializable{

    private Long creationTime;
    private Long lastAccessedTime;

    public HSessionHeader() {
        creationTime = System.currentTimeMillis();
    }

    public Long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }



}
