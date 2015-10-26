package com.dyg.health.work.volleytest.modle;

import java.io.Serializable;

/**
 * Created by wzy on 2015-10-10.
 */
public class Info implements Serializable{
    private String imgurl;
    private String text;

    public String getImgurl() {
        return imgurl;
    }

    public String getText() {
        return text;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setText(String text) {
        this.text = text;
    }
}
