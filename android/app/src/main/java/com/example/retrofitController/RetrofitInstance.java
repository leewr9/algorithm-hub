package com.example.retrofitController;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private Retrofit retrofit;
    private MySqlService mySqlService;
    private ApiService apiService;

    public RetrofitInstance(final String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public MySqlService getMySqlService() {
        mySqlService = retrofit.create(MySqlService.class);
        return mySqlService;
    }
}
