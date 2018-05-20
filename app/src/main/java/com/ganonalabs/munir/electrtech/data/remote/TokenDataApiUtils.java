package com.ganonalabs.munir.electrtech.data.remote;

import com.google.android.gms.auth.TokenData;

public class TokenDataApiUtils {
    public TokenDataApiUtils(){}
    public static final String TOKEN_DATA_BASE_URL = "http://e-tech.com.bd/ess/";
    public static TokenDataApiService getUserDataAPIServices(){
        return  TokenDataRetrofitClient.getTokenDataClient(TOKEN_DATA_BASE_URL).create(TokenDataApiService.class);
    }
}
