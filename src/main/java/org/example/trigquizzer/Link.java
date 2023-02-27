package org.example.trigquizzer;

import com.google.gson.GsonBuilder;

public class Link {
    private long id;
    private String url;
    private String description;

    public Link() {
    }

    public Link(long id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }

    public Link(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
