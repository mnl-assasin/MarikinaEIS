package com.olfu.meis.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mykelneds on 11/01/2017.
 */

public interface ApiInterface {

    @GET("reports")
    Call<List<EarthquakeModel>> getReports();

    @GET("forecasts")
    Call<List<EarthquakeModel>> getForecast();
}
