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
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJjMzc1YTE3NGY2ZjkwMDQ5ODRjMjEwYjYwZDJlZmMyMGZmOWRhNzU2ZTNjNTM0NjY0NjgwOGNlNjg5MTVhOGEyY2FiOWE0MTI4ZGRiN2QyIn0.eyJhdWQiOiIyIiwianRpIjoiYmMzNzVhMTc0ZjZmOTAwNDk4NGMyMTBiNjBkMmVmYzIwZmY5ZGE3NTZlM2M1MzQ2NjQ2ODA4Y2U2ODkxNWE4YTJjYWI5YTQxMjhkZGI3ZDIiLCJpYXQiOjE1MjcwNDkwNzMsIm5iZiI6MTUyNzA0OTA3MywiZXhwIjoxNTU4NTg1MDczLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.vXQOkF05PhMtRh1OjpNmuiLkb6XB0ec-F7z07x9XJJyhXGx3nRiW1TeEtle3iX84Qan3dSgTznYbrXHXY3ZIWTDj0At5TXwBzn_8NEGu9iqaL3fMofdYwX7Vu3rbqmVngVkNQGun9H0uedlgSMHwLhVdGg0L1M-bYFOpAiQrLZ7WCjxcYRJ5raZ3F09nPtW8AtE5KEzx_w2h4fFPteaQ5z-79pIyNxX6GaQKaUW_J0jL1KJCCbLIttVDLbMPjxi4mDN1cDw8cCX5853GWCSLIlMUvOj1M3FTHU8Q2PeS8uvdoIPq-kAW2WVyFJCV0fKTnFIx2NsED5J5L46Aqc2IRePQaUwsAgpNbrqj1B_0w6gPG17R8UloDVUHtnr8sLiG2ENYU25RwbBDqhrY305F2yHbUK9h1nAuGC-zs0L8XlFOUIkPnvPOF503XitkX89Ax-7Nn4EVhTPVHBxIqnA3V8yKw-FHDyMVSlOCgoy5MtDYkVeDDa2tOxXcPGdJxlUUeur18jxqpYPugcPrzlu2jFFEqVVzoVaqDQrbmVLCSMJTRR5FHZNKqpii5AKL6A-kdwCrHoM3pi_xJ4Pko7co-JIxX2vt1S275mn695wkZTFNtuP15pcOyDvYNRZQCKUTyEqTjvVs_5RIgHKaNcGWbU2uscPy4u1E6FLUH8v2irI"
    })

    @POST("api/JobRequests")
    Call<JobPostResponse> postJob(@Body RequestBody params);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJjMzc1YTE3NGY2ZjkwMDQ5ODRjMjEwYjYwZDJlZmMyMGZmOWRhNzU2ZTNjNTM0NjY0NjgwOGNlNjg5MTVhOGEyY2FiOWE0MTI4ZGRiN2QyIn0.eyJhdWQiOiIyIiwianRpIjoiYmMzNzVhMTc0ZjZmOTAwNDk4NGMyMTBiNjBkMmVmYzIwZmY5ZGE3NTZlM2M1MzQ2NjQ2ODA4Y2U2ODkxNWE4YTJjYWI5YTQxMjhkZGI3ZDIiLCJpYXQiOjE1MjcwNDkwNzMsIm5iZiI6MTUyNzA0OTA3MywiZXhwIjoxNTU4NTg1MDczLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.vXQOkF05PhMtRh1OjpNmuiLkb6XB0ec-F7z07x9XJJyhXGx3nRiW1TeEtle3iX84Qan3dSgTznYbrXHXY3ZIWTDj0At5TXwBzn_8NEGu9iqaL3fMofdYwX7Vu3rbqmVngVkNQGun9H0uedlgSMHwLhVdGg0L1M-bYFOpAiQrLZ7WCjxcYRJ5raZ3F09nPtW8AtE5KEzx_w2h4fFPteaQ5z-79pIyNxX6GaQKaUW_J0jL1KJCCbLIttVDLbMPjxi4mDN1cDw8cCX5853GWCSLIlMUvOj1M3FTHU8Q2PeS8uvdoIPq-kAW2WVyFJCV0fKTnFIx2NsED5J5L46Aqc2IRePQaUwsAgpNbrqj1B_0w6gPG17R8UloDVUHtnr8sLiG2ENYU25RwbBDqhrY305F2yHbUK9h1nAuGC-zs0L8XlFOUIkPnvPOF503XitkX89Ax-7Nn4EVhTPVHBxIqnA3V8yKw-FHDyMVSlOCgoy5MtDYkVeDDa2tOxXcPGdJxlUUeur18jxqpYPugcPrzlu2jFFEqVVzoVaqDQrbmVLCSMJTRR5FHZNKqpii5AKL6A-kdwCrHoM3pi_xJ4Pko7co-JIxX2vt1S275mn695wkZTFNtuP15pcOyDvYNRZQCKUTyEqTjvVs_5RIgHKaNcGWbU2uscPy4u1E6FLUH8v2irI"
    })
    @GET("api/Brands")
    Call<Brands> getBrands();

}
