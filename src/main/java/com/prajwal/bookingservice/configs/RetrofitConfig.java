package com.prajwal.bookingservice.configs;

import com.netflix.discovery.EurekaClient;
import com.prajwal.bookingservice.apis.LocationServiceAPI;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Autowired
    private EurekaClient eurekaClient;

    private String getServiceUrl(String serviceName) {
        return eurekaClient.getNextServerFromEureka(serviceName, false).getHomePageUrl();
    }

    @Bean
    public LocationServiceAPI locationServiceAPI() {
        return new Retrofit.Builder().baseUrl(getServiceUrl("location-service"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build().create(LocationServiceAPI.class);
    }
}
