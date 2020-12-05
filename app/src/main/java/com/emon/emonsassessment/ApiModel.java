package com.emon.emonsassessment;

public class ApiModel {
    private String id;
    private String url;
    private String name;
    private String type;
    private String language;
    private String status;
    private String medium;

    public ApiModel(String id, String url, String name, String type, String language, String status, String medium) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.type = type;
        this.language = language;
        this.status = status;
        this.medium = medium;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getStatus() {
        return status;
    }

    public String getMedium() {
        return medium;
    }
}
