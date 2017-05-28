package ru.coyul.producthuntclient.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.coyul.producthuntclient.model.ProductResponse;

public interface ProductHuntApi {

    @GET("v1/posts")
    Call<ProductResponse> getPosts(
            @Query("access_token") String token,
            @Query("search[category]") String category);
}
