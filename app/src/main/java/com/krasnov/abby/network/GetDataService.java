package com.krasnov.abby.network;

import com.krasnov.abby.models.ItemsAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/2.2/questions?fromdate=1555459200&order=desc&min=1558051200&sort=activity&tagged=android&site=stackoverflow")
    Call<ItemsAPI> getAllItems();
}
