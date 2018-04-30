package com.ganonalabs.munir.electrtech.model;

import java.io.Serializable;

/**
 * Created by munirul.hoque on 30/04/2018.
 */
@SuppressWarnings("serial")
public class AppUser implements Serializable {
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;
    private String photoURL;
    private String providerId;
    private String lastSignedInAt;
    private String createdAt;
    private String address;
    private String userCategory;
    private String userCategoryImage;



    public AppUser() {
    }
}
