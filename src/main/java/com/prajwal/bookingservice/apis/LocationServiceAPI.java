package com.prajwal.bookingservice.apis;

import com.prajwal.bookingservice.dtos.CreateBookingRequestDTO;
import com.prajwal.bookingservice.dtos.DriverLocationDto;
import com.prajwal.bookingservice.dtos.NearbyDriversRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface LocationServiceAPI {


    @POST("api/v1/location/nearby/drivers")
    Call<DriverLocationDto[]> getNearbyDrivers(@Body NearbyDriversRequestDto request) ;


}