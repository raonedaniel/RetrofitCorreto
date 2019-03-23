package com.cursoandroid.raone.retrofit;

import com.cursoandroid.raone.retrofit.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityService {

   public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";

   @GET("courses")
   Call<UdacityCatalog> listCatalog();
}
