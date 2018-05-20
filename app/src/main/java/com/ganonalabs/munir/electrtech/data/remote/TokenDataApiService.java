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
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVkYTExMDczOTE5ZTMyYWY0NzI4ZGM1NTQzNTJkYTljNGY0OWNkOWVkNDRiOWRhNGRiMzI2MGY5NWJkZjE0MTU5NGJmMGIxMmNmNjk4OTJmIn0.eyJhdWQiOiIyIiwianRpIjoiNWRhMTEwNzM5MTllMzJhZjQ3MjhkYzU1NDM1MmRhOWM0ZjQ5Y2Q5ZWQ0NGI5ZGE0ZGIzMjYwZjk1YmRmMTQxNTk0YmYwYjEyY2Y2OTg5MmYiLCJpYXQiOjE1MjY0NzE1NTMsIm5iZiI6MTUyNjQ3MTU1MywiZXhwIjoxNTU4MDA3NTUzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.YCcgCc2HgbGdJb3in1HfeFA6sn9LhJTd1kfpyBCyoADcE6L89Wd4PovozOBDi552cLCKBdVpKSyFko_vKAu5w9CIZ_9njNV6OxVGhRi735eRjyH33KIy0R1AosDCrzbctElJFg8qw-q9qzhtO4BqimO3SH6dFTQJAMsxEM38hy3mp9Wr8CEf-iBQ2i_VkrslfKH5UouADba_MG_Zzwi9UOuKUyNqK5g3o_gbOWW2WMYbmFvzeAmFyzxZYpicrXpg9vnDGAW9TyUJEZe6tKObxGbIlID_KOIndqoL1dpzFDM835sFvZoGZdkd6uw2DvyRlJ7oVNIbuN7MeZUBXEeqvsNnhoCFwyTz4RqckyYkiu-raAcxyfZo4RWf-AhgbjoR0w0nef4zaNYc71ASfupbfphT_nybqWxT9Mpch55r1zio7OtZd8QOK07mAN9jgx5EkYjg7A9rgb63kFqjf8uz0iOFqZm0EDN3IUtJ8luzgEtuMj88U1LmK0RPLqrQ-SoYSiWluLvePXSh1K8BhEnU_j-GXNDkSh067RNKMZdr3PYpdL3mqEbiVQ8FwYLwhWlg_PG87i3lkPwzDq-9YdtLgoS3McXUts9AD3Zo_EI1liOld4veg0qFY9dvvZkBbPlngBmqOWk1jY_xRcM1xa_hoN37EWxODcndqt_kQ1kEEqY"
    })
    @POST("api/JobRequests")
    Call<ResponseBody> postJob(@Body RequestBody params);

    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVkYTExMDczOTE5ZTMyYWY0NzI4ZGM1NTQzNTJkYTljNGY0OWNkOWVkNDRiOWRhNGRiMzI2MGY5NWJkZjE0MTU5NGJmMGIxMmNmNjk4OTJmIn0.eyJhdWQiOiIyIiwianRpIjoiNWRhMTEwNzM5MTllMzJhZjQ3MjhkYzU1NDM1MmRhOWM0ZjQ5Y2Q5ZWQ0NGI5ZGE0ZGIzMjYwZjk1YmRmMTQxNTk0YmYwYjEyY2Y2OTg5MmYiLCJpYXQiOjE1MjY0NzE1NTMsIm5iZiI6MTUyNjQ3MTU1MywiZXhwIjoxNTU4MDA3NTUzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.YCcgCc2HgbGdJb3in1HfeFA6sn9LhJTd1kfpyBCyoADcE6L89Wd4PovozOBDi552cLCKBdVpKSyFko_vKAu5w9CIZ_9njNV6OxVGhRi735eRjyH33KIy0R1AosDCrzbctElJFg8qw-q9qzhtO4BqimO3SH6dFTQJAMsxEM38hy3mp9Wr8CEf-iBQ2i_VkrslfKH5UouADba_MG_Zzwi9UOuKUyNqK5g3o_gbOWW2WMYbmFvzeAmFyzxZYpicrXpg9vnDGAW9TyUJEZe6tKObxGbIlID_KOIndqoL1dpzFDM835sFvZoGZdkd6uw2DvyRlJ7oVNIbuN7MeZUBXEeqvsNnhoCFwyTz4RqckyYkiu-raAcxyfZo4RWf-AhgbjoR0w0nef4zaNYc71ASfupbfphT_nybqWxT9Mpch55r1zio7OtZd8QOK07mAN9jgx5EkYjg7A9rgb63kFqjf8uz0iOFqZm0EDN3IUtJ8luzgEtuMj88U1LmK0RPLqrQ-SoYSiWluLvePXSh1K8BhEnU_j-GXNDkSh067RNKMZdr3PYpdL3mqEbiVQ8FwYLwhWlg_PG87i3lkPwzDq-9YdtLgoS3McXUts9AD3Zo_EI1liOld4veg0qFY9dvvZkBbPlngBmqOWk1jY_xRcM1xa_hoN37EWxODcndqt_kQ1kEEqY"
    })
    @GET("api/Brands")
    Call<Brands> getBrands();

}
