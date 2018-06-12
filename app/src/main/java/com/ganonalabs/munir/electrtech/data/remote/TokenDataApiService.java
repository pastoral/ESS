package com.ganonalabs.munir.electrtech.data.remote;

import com.ganonalabs.munir.electrtech.data.model.Brands;
import com.ganonalabs.munir.electrtech.data.model.Brands_Data;
import com.ganonalabs.munir.electrtech.data.model.JobPostResponse;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequestList;
import com.ganonalabs.munir.electrtech.data.model.TokenClass;



import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


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


    @POST("api/JobRequests")
    Call<JobPostResponse> postJob(@Body RequestBody params);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQ4NDY4YmZlYjI2OGVkMzY5MjEyNTgyMjY5Yzk2ZTEzOGE1N2I4MjAzOTIwNjJlYjllMWZjZjQ4MTdiNjY1ZjIyNDkzNWY2NzA3ZTdjMWIwIn0.eyJhdWQiOiIyIiwianRpIjoiZDg0NjhiZmViMjY4ZWQzNjkyMTI1ODIyNjljOTZlMTM4YTU3YjgyMDM5MjA2MmViOWUxZmNmNDgxN2I2NjVmMjI0OTM1ZjY3MDdlN2MxYjAiLCJpYXQiOjE1Mjg2MDc1ODMsIm5iZiI6MTUyODYwNzU4MywiZXhwIjoxNTYwMTQzNTgzLCJzdWIiOiI4Iiwic2NvcGVzIjpbXX0.WGmAIx0lK1UirtN_MX9E6Kt_WKwuqZTyTI4SYm2JWrIdzTJUA3kP9hmXf_2-WNiX6L9LQbdet4aqPopWxJbjvBNnr-fDRMmKNyIBkqPhHD2_CwjZf_9kXgRDVXA6KVTbLOM1aAd7DoNb8yTpLzSRtSPvtM-aVfWcpSrd-0SQghx2VUHHbb5Z1UdEpYRc1yY6wgPowntDOaBVprqeGKisRwS0s2FVHE_YZYiTBNkDjhjWVr2QaeoWig0_LPtkNt11AlLIVSoRc8SvrU8WiBxwfX19FtmgptZJ_54xy6FNX_9Dw3OFUker5ctHhxqBJRtzO4bJGNFzwhM1ZeZz2YZ-aW4vzSNkxrJulxd8aO5v5IDNIT_JdEBLEQe5npi9M3o_Wh8Ax4Chpmt9209u4nsg7BU4kSr5n_eqpkEIS3LgZndMSNQT4VNeOIzIAnnOx-LFxkc_nlA8cUZlnZ5vqPrvJ9DaC0QerpSWg3O5Ld4gh438p0LiUAGcvHVoNtx6PrOEf6Uef3Y15NtWeNVfLPPuXJ0xDmXVpkQ0XP379xezjefWcoJRYnkPgRPVGlDJFKEwd0PhTfuM-5Ja81FzNF_xXJTqHocgpY0NyY4jf5A9gKFiNm28eH_d5tocd3S-VzzGsY6FhWpYccT1TfjHC4k6heXP_cCM2Y92bNzxua4KsYI"
    })
    @GET("api/Brands")
    Call<Brands> getBrands();

    @GET
    Call<MyJobRequestList> getMyJobRequests(@Url String url);

}
