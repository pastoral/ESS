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
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY5OTE3ZDYxZGQ0ZGQ3ZWVkMzAwNDE2OTVkYjJkNGJlMTE4M2YyYjA5MzA4OWIwMTk4NDVjZjg5YWEyMmUzZGIyNzJjYjViZTI2ZWFkNmZiIn0.eyJhdWQiOiIyIiwianRpIjoiNjk5MTdkNjFkZDRkZDdlZWQzMDA0MTY5NWRiMmQ0YmUxMTgzZjJiMDkzMDg5YjAxOTg0NWNmODlhYTIyZTNkYjI3MmNiNWJlMjZlYWQ2ZmIiLCJpYXQiOjE1MjY5ODA3NjgsIm5iZiI6MTUyNjk4MDc2OCwiZXhwIjoxNTU4NTE2NzY4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.uBAPtILz7dB97Kc92K8Ap8I1Hz63wY6VF_DemXDNRwYVOEB7RZCZLAJjs_D8A9s_xVjaCm6QXsZQpIto7g81XpSlAM7Z10wZ6K98Mb7GmuM9VVGBUqiFUYmJXX8oUJlwHrGGcWcGCQa3XoIOpl6npC0IWDYo1Aw33DHv0pWy0_RobMIkgHKfKGLue72PO0y6SpJ6iwu68wyvPrIkZIRjS0uWpHeogLmWIo4f8L24FDlIJ1S941wgHibZN5eNBextfo8Vur5iiGjr_amtvNpOSOkEKdfqAsAz6b3DDbroYrPgSi1ja-_9kr8kAZqKIYMk7vpKRupjaa_C7a5hsTzMyQx8s4s8swbse8sqDGvJ6_mOBzT5v1Y1bYjWCR5UubFUiDR9UkJnMQkaNO5cxXz6B3MtJes3-Qdrz9tcLftSc9HAGnqcparRrn4Zv1tUFJt-1-hNtCMtTOw1SvFyyuYtIRezrAcvPqrqOTQZ4F1tMCwSPayECxxOqrDmk6L5hPfWpyYhANScO31EW2TrsdIGberv1h9Uk_2lmQR9OUuQ9ZRjwj6NtpivkYtm7akw4dKORyeg0b7ZdXKiQvmSFkHrn16-vAubvRRMf3MqLbCg-IRQvJNlozrMEx2vkQuNbOdMWRE79nbm6lqNYZiwYmn6n12Usl2zGuyMJN-htmlK4bc"
    })

    @POST("api/JobRequests")
    Call<JobPostResponse> postJob(@Body RequestBody params);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY5OTE3ZDYxZGQ0ZGQ3ZWVkMzAwNDE2OTVkYjJkNGJlMTE4M2YyYjA5MzA4OWIwMTk4NDVjZjg5YWEyMmUzZGIyNzJjYjViZTI2ZWFkNmZiIn0.eyJhdWQiOiIyIiwianRpIjoiNjk5MTdkNjFkZDRkZDdlZWQzMDA0MTY5NWRiMmQ0YmUxMTgzZjJiMDkzMDg5YjAxOTg0NWNmODlhYTIyZTNkYjI3MmNiNWJlMjZlYWQ2ZmIiLCJpYXQiOjE1MjY5ODA3NjgsIm5iZiI6MTUyNjk4MDc2OCwiZXhwIjoxNTU4NTE2NzY4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.uBAPtILz7dB97Kc92K8Ap8I1Hz63wY6VF_DemXDNRwYVOEB7RZCZLAJjs_D8A9s_xVjaCm6QXsZQpIto7g81XpSlAM7Z10wZ6K98Mb7GmuM9VVGBUqiFUYmJXX8oUJlwHrGGcWcGCQa3XoIOpl6npC0IWDYo1Aw33DHv0pWy0_RobMIkgHKfKGLue72PO0y6SpJ6iwu68wyvPrIkZIRjS0uWpHeogLmWIo4f8L24FDlIJ1S941wgHibZN5eNBextfo8Vur5iiGjr_amtvNpOSOkEKdfqAsAz6b3DDbroYrPgSi1ja-_9kr8kAZqKIYMk7vpKRupjaa_C7a5hsTzMyQx8s4s8swbse8sqDGvJ6_mOBzT5v1Y1bYjWCR5UubFUiDR9UkJnMQkaNO5cxXz6B3MtJes3-Qdrz9tcLftSc9HAGnqcparRrn4Zv1tUFJt-1-hNtCMtTOw1SvFyyuYtIRezrAcvPqrqOTQZ4F1tMCwSPayECxxOqrDmk6L5hPfWpyYhANScO31EW2TrsdIGberv1h9Uk_2lmQR9OUuQ9ZRjwj6NtpivkYtm7akw4dKORyeg0b7ZdXKiQvmSFkHrn16-vAubvRRMf3MqLbCg-IRQvJNlozrMEx2vkQuNbOdMWRE79nbm6lqNYZiwYmn6n12Usl2zGuyMJN-htmlK4bc"
    })
    @GET("api/Brands")
    Call<Brands> getBrands();

}
