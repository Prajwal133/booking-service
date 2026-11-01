package com.prajwal.bookingservice.apis;

import com.prajwal.bookingservice.dtos.DriverLocationDto;
import com.prajwal.bookingservice.dtos.NearbyDriversRequestDto;
import com.prajwal.bookingservice.dtos.RideRequestDto;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientSocKetServiceAPI {
    @POST("api/v1/socket/newRide")
    Call<String> getNearbyDrivers(@Body RideRequestDto request) ;

}
