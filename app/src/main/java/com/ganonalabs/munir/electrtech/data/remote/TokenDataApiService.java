package com.ganonalabs.munir.electrtech.data.remote;

import com.ganonalabs.munir.electrtech.data.model.Brands;
import com.ganonalabs.munir.electrtech.data.model.Brands_Data;
import com.ganonalabs.munir.electrtech.data.model.JobPostResponse;
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
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImY1ZWVhYWQ5OTI2YmM4YTM4NWY5NTFhMjkxNGYzZTY0YTExMjY5ZjFiZTg5ODEzYTc4ZjFjODM3ZDRiZWVlYWZkMDRmODdkYTEzMzA3ZDJjIn0.eyJhdWQiOiIyIiwianRpIjoiZjVlZWFhZDk5MjZiYzhhMzg1Zjk1MWEyOTE0ZjNlNjRhMTEyNjlmMWJlODk4MTNhNzhmMWM4MzdkNGJlZWVhZmQwNGY4N2RhMTMzMDdkMmMiLCJpYXQiOjE1Mjc1ODkyOTUsIm5iZiI6MTUyNzU4OTI5NSwiZXhwIjoxNTU5MTI1Mjk1LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.ZJvz3jZR6Lfxx06kqbmiKjNjjC1pvSMaf-mPFUTEnIEw4WrHd_gpOGSmQVTZbmp1g3G7tntNK3IvmPNo_i9Ztu_bfRpYvjZXKBDymRxQmFr5Al4iD5h04ujdajTMSAIBreP2F7n0aV0SDFIWpJ7Y1zMuNUU_X4JTyciRJknszlR8XunfexO1hpXrl_eZhSqF6Tac41cYcybP-NsfdBFwRzjPSOsnYHsPQnZtmSudgJndYgMgWb6FVUlP-uMTrNDlKT4lpDsWs9yC-WFpoJOKyeqtd1hEnDVVyo7VsAe-Qqwm1zKQtcxxdkF022AI0vqqraf8j1VL_9W608pkLmxpVFUlHKn9-W7nLPHyCDU6M1CJ4WWiiTmm_6s7-czzoBn8VyNqC3dYFVg_jBfXM8vmXyDLGlHKy5hlusslDfO6ICnrBvn_KUamzljfmPht6HhBh77cbfA1_dms7xsDzdtnsUBBpXRwoW8cs8DS9-VM9NoAcpFlOLurwfzl0VfSBE9stnGHywpVWzeeXBrDbNe3pTDvTkI_BSRoqPo7UwbIb3Zp7iIIvohLZXdP658jkgfTuZaKDSPNawHRWCnX1tSTp2_6g_NMxIVUrCZ4QFEM0g889PMuruhKQHw7NKJVURcpsHddjIIvOyKZyZ0F_Hpu6j2wH_zhoSzUmEbejVThQC4"
    })

    @POST("api/JobRequests")
    Call<JobPostResponse> postJob(@Body RequestBody params);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImY1ZWVhYWQ5OTI2YmM4YTM4NWY5NTFhMjkxNGYzZTY0YTExMjY5ZjFiZTg5ODEzYTc4ZjFjODM3ZDRiZWVlYWZkMDRmODdkYTEzMzA3ZDJjIn0.eyJhdWQiOiIyIiwianRpIjoiZjVlZWFhZDk5MjZiYzhhMzg1Zjk1MWEyOTE0ZjNlNjRhMTEyNjlmMWJlODk4MTNhNzhmMWM4MzdkNGJlZWVhZmQwNGY4N2RhMTMzMDdkMmMiLCJpYXQiOjE1Mjc1ODkyOTUsIm5iZiI6MTUyNzU4OTI5NSwiZXhwIjoxNTU5MTI1Mjk1LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.ZJvz3jZR6Lfxx06kqbmiKjNjjC1pvSMaf-mPFUTEnIEw4WrHd_gpOGSmQVTZbmp1g3G7tntNK3IvmPNo_i9Ztu_bfRpYvjZXKBDymRxQmFr5Al4iD5h04ujdajTMSAIBreP2F7n0aV0SDFIWpJ7Y1zMuNUU_X4JTyciRJknszlR8XunfexO1hpXrl_eZhSqF6Tac41cYcybP-NsfdBFwRzjPSOsnYHsPQnZtmSudgJndYgMgWb6FVUlP-uMTrNDlKT4lpDsWs9yC-WFpoJOKyeqtd1hEnDVVyo7VsAe-Qqwm1zKQtcxxdkF022AI0vqqraf8j1VL_9W608pkLmxpVFUlHKn9-W7nLPHyCDU6M1CJ4WWiiTmm_6s7-czzoBn8VyNqC3dYFVg_jBfXM8vmXyDLGlHKy5hlusslDfO6ICnrBvn_KUamzljfmPht6HhBh77cbfA1_dms7xsDzdtnsUBBpXRwoW8cs8DS9-VM9NoAcpFlOLurwfzl0VfSBE9stnGHywpVWzeeXBrDbNe3pTDvTkI_BSRoqPo7UwbIb3Zp7iIIvohLZXdP658jkgfTuZaKDSPNawHRWCnX1tSTp2_6g_NMxIVUrCZ4QFEM0g889PMuruhKQHw7NKJVURcpsHddjIIvOyKZyZ0F_Hpu6j2wH_zhoSzUmEbejVThQC4"
    })
    @GET("api/Brands")
    Call<Brands> getBrands();

}
