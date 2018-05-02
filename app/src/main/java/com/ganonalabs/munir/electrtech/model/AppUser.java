package com.ganonalabs.munir.electrtech.model;

@SuppressWarnings("serial")
public class AppUser {
    public String uid;
    public String name;
    public String email;
    public String phoneNumber;
    public String photoURL;
    public String providerId;
    public String userCategory;
    public String userCategoryImage;
    public String createdAt;
    public String lastSignedInAt;
    public String userType;
    public String address;

    public AppUser(){}

    public AppUser(String uid, String name, String email, String phoneNumber, String photoURL, String providerId, String userCategory, String userCategoryImage, String createdAt, String lastSignedInAt, String userType, String address) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photoURL = photoURL;
        this.providerId = providerId;
        this.userCategory = userCategory;
        this.userCategoryImage = userCategoryImage;
        this.createdAt = createdAt;
        this.lastSignedInAt = lastSignedInAt;
        this.userType = userType;
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getUserCategoryImage() {
        return userCategoryImage;
    }

    public void setUserCategoryImage(String userCategoryImage) {
        this.userCategoryImage = userCategoryImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastSignedInAt() {
        return lastSignedInAt;
    }

    public void setLastSignedInAt(String lastSignedInAt) {
        this.lastSignedInAt = lastSignedInAt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
