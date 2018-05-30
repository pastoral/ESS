package com.ganonalabs.munir.electrtech.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class News implements Serializable {
    private String description;
    private String title;
    private String imageURL;
    private String rules;
    private String topCard;
    private int discount;
    private long revtimestamp;

    public News(){

    }

    public News(String description, String title, String imageURL, String rules, String topCard, int discount, long revtimestamp) {
        this.description = description;
        this.title = title;
        this.imageURL = imageURL;
        this.rules = rules;
        this.topCard = topCard;
        this.discount = discount;
        this.revtimestamp = revtimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getTopCard() {
        return topCard;
    }

    public void setTopCard(String topCard) {
        this.topCard = topCard;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public long getRevtimestamp() {
        return revtimestamp;
    }

    public void setRevtimestamp(long revtimestamp) {
        this.revtimestamp = revtimestamp;
    }
}
