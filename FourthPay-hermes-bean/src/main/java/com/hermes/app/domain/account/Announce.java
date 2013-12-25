package com.hermes.app.domain.account;

import java.io.Serializable;

/**
 * 公告
 * @author of644
 */
public class Announce implements Serializable {
    private String id;          //
    private String title;       //
    private String content;
    private String lastupdtime;
    private String createtime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastupdtime() {
        return lastupdtime;
    }

    public void setLastupdtime(String lastupdtime) {
        this.lastupdtime = lastupdtime;
    }
}
