package com.example.project_ln.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiUtil {


    /*https://jsonplaceholder.typicode.com/*/
    private static final String BASE_URL = "http://10.0.2.2:3000/api/";

    public static RetrofitInterface getServiceClass() {
        return RetrofitAPI.getRetrofit(BASE_URL).create(RetrofitInterface.class);
    }

    private static Retrofit instance;
    public static Retrofit getInstance () {
        if (instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance;
    }

}
