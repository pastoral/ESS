package com.ganonalabs.munir.electrtech.data.remote;

import com.ganonalabs.munir.electrtech.data.model.Brands;
import com.ganonalabs.munir.electrtech.data.model.Brands_Data;
import com.ganonalabs.munir.electrtech.data.model.TokenClass;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface TokenDataApiService {
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @GET("oauth/token")
    //@FormUrlEncoded
    Call<TokenClass> sendOauthdata(@Query("grant_type") String grant_type,
                                   @Query("client_id") String client_id,
                                   @Query("client_secret") String client_secret,
                                   @Query("username") String username,
                                   @Query("password") String password);

    @POST("oauth/token")
    Call<ResponseBody> sendToken(@Body RequestBody params);

    //application/x-www-form-urlencoded
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFhZTY0MmMwZWQ5M2ZkMWYzMDc4N2VkMmI0NTZhODAxMjliYWNmYmM4ZmZiY2Y5MzI4OTRlOTIwMGQ2N2U0NDM4OTU2NzYxOTdjN2MyMGQ5In0.eyJhdWQiOiIyIiwianRpIjoiYWFlNjQyYzBlZDkzZmQxZjMwNzg3ZWQyYjQ1NmE4MDEyOWJhY2ZiYzhmZmJjZjkzMjg5NGU5MjAwZDY3ZTQ0Mzg5NTY3NjE5N2M3YzIwZDkiLCJpYXQiOjE1MjUyNjM4MzUsIm5iZiI6MTUyNTI2MzgzNSwiZXhwIjoxNTU2Nzk5ODM1LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.MG-8CxMAES3NO3yp1AXUa62JNgbEycBOB82QwQZQNDhaIIEHWQed5vH6Ae2lY_Ix_qvXQ81xkCy5z0UHMbQ0dgs_hPRvARdh9L7rUYIuYIujS-mFdGjj6FO4AOeYppsZLhXc9TNPhlfReToIqkoWqf2iFrA9QBseo9GYOdSDlxxkDLPZmHgQLnFzDl98OAZK19jXXCf90dzdTPNCBVySPel09EIroXDA-8I1n-w-CC6VD63AwegPEdK-pz7B6hl11T1EArcdMmyDrgi6SvpKVR__UI0qSIqnu779wdU59IScR4XYEE3ChputprrQQohA9V500U-pSb5T1j23Si5lNS7zpQ7HM1gGQk_0jf3z-mTq73OaasyxgWQg1PKkiJ87dOM4U_TfaRgpivXqUNbxn-LA6db1mIbgfyNkRJjGWAUsCDVPvKfrDwACWLlvg2Av8laoPVB-Tdwp1gxm6NTCqqqwoKRBnSPDAyzfDaCCbBwzp2DL1ttygjuR60oJfq0QlUNj9dHWS9Nqh8QYueUViOCbIFRe0hgc9wfiZn8SaJSMetpExE9qWAyK0Y_BPZB7F1L3c-KrrfIxP5PSgHvr-W_tVzjwO-alOZrvzwM6r4RxQI99yAqM75In5CnZVwuy2keU-3d8QK6bg0INK54LqNpJCFkIX7TMA0rvH113AaQ"
    })
    @POST("api/JobRequests")
    Call<ResponseBody> postJob(@Body RequestBody params);

    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFhZTY0MmMwZWQ5M2ZkMWYzMDc4N2VkMmI0NTZhODAxMjliYWNmYmM4ZmZiY2Y5MzI4OTRlOTIwMGQ2N2U0NDM4OTU2NzYxOTdjN2MyMGQ5In0.eyJhdWQiOiIyIiwianRpIjoiYWFlNjQyYzBlZDkzZmQxZjMwNzg3ZWQyYjQ1NmE4MDEyOWJhY2ZiYzhmZmJjZjkzMjg5NGU5MjAwZDY3ZTQ0Mzg5NTY3NjE5N2M3YzIwZDkiLCJpYXQiOjE1MjUyNjM4MzUsIm5iZiI6MTUyNTI2MzgzNSwiZXhwIjoxNTU2Nzk5ODM1LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.MG-8CxMAES3NO3yp1AXUa62JNgbEycBOB82QwQZQNDhaIIEHWQed5vH6Ae2lY_Ix_qvXQ81xkCy5z0UHMbQ0dgs_hPRvARdh9L7rUYIuYIujS-mFdGjj6FO4AOeYppsZLhXc9TNPhlfReToIqkoWqf2iFrA9QBseo9GYOdSDlxxkDLPZmHgQLnFzDl98OAZK19jXXCf90dzdTPNCBVySPel09EIroXDA-8I1n-w-CC6VD63AwegPEdK-pz7B6hl11T1EArcdMmyDrgi6SvpKVR__UI0qSIqnu779wdU59IScR4XYEE3ChputprrQQohA9V500U-pSb5T1j23Si5lNS7zpQ7HM1gGQk_0jf3z-mTq73OaasyxgWQg1PKkiJ87dOM4U_TfaRgpivXqUNbxn-LA6db1mIbgfyNkRJjGWAUsCDVPvKfrDwACWLlvg2Av8laoPVB-Tdwp1gxm6NTCqqqwoKRBnSPDAyzfDaCCbBwzp2DL1ttygjuR60oJfq0QlUNj9dHWS9Nqh8QYueUViOCbIFRe0hgc9wfiZn8SaJSMetpExE9qWAyK0Y_BPZB7F1L3c-KrrfIxP5PSgHvr-W_tVzjwO-alOZrvzwM6r4RxQI99yAqM75In5CnZVwuy2keU-3d8QK6bg0INK54LqNpJCFkIX7TMA0rvH113AaQ"
    })
    @GET("api/Brands")
    Call<List<Brands_Data>> getBrands();

}
