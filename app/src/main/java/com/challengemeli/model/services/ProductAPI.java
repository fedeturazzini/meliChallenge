package com.challengemeli.model.services;

import com.challengemeli.model.pojo.Results;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductAPI {

    @GET("sites/MLA/search")
    Single <Results> getProductResults(@Query("q") String query);

}
