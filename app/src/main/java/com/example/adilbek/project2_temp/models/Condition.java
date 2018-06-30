package com.example.adilbek.project2_temp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * No args constructor for use in serialization
     *
     */
    public Condition() {
    }

    /**
     *
     * @param icon
     * @param text
     * @param code
     */
    public Condition(String text, String icon, int code) {
        super();
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
