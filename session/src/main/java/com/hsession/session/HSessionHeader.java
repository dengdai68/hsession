package com.hsession.session;

import java.io.Serializable;

/**
 * Created by EX-HANJIANKAI557 on 2016/3/17.
 */
public class HSessionHeader implements Serializable{

    private Long creationTime;
    private Long lastAccessedTime;
    private boolean isNew = true;

    public HSessionHeader() {
        creationTime = System.currentTimeMillis();
        isNew = true;
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
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }


}
