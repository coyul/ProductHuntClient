package ru.coyul.producthuntclient.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductHuntNetworkService {

    private final String API_URL = "https://api.producthunt.com/";
    private Retrofit retrofit;

    public ProductHuntNetworkService() {
        retrofit = getInstance();
    }

    private Retrofit getInstance() {
        Retrofit result = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return result;
    }

    public ProductHuntApi getApi() {
        return retrofit.create(ProductHuntApi.class);
    }
}
