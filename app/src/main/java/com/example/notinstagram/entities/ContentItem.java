package com.example.notinstagram.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentItem implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("contents")
    @Expose
    private String contents;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContents() {
        return contents;
    }
                                                        
    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
