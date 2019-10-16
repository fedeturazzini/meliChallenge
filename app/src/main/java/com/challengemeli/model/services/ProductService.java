package com.challengemeli.model.services;

import com.challengemeli.model.pojo.Results;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductService {

    private static final String BASE_URL = "https://api.mercadolibre.com/";

    private static ProductService instanceProduct;

    private OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    private ProductAPI api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ProductAPI.class);

    public ProductService() {

    }

    public static ProductService getInstanceProduct() {
        if (instanceProduct == null) {
            instanceProduct = new ProductService();
        }
        return instanceProduct;
    }

    public Single <Results> getResultProduct(String query) {
        return api.getProductResults(query);
    }
}
