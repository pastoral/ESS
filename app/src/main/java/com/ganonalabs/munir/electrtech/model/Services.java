package com.ganonalabs.munir.electrtech.model;

public class Services {
    public String service_id;
    public String name;
    public String imageUrl;
    public String service_tex;
    public String service_text;
    public String tag;
    public String base_price;

    public Services(){}

    public Services(String service_id, String name, String imageUrl, String service_tex, String service_text, String tag, String base_price) {
        this.service_id = service_id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.service_tex = service_tex;
        this.service_text = service_text;
        this.tag = tag;
        this.base_price = base_price;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getService_tex() {
        return service_tex;
    }

    public void setService_tex(String service_tex) {
        this.service_tex = service_tex;
    }

    public String getService_text() {
        return service_text;
    }

    public void setService_text(String service_text) {
        this.service_text = service_text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
    }
}
