package com.prajwal.bookingservice.services;

import com.prajwal.bookingservice.apis.LocationServiceAPI;
import com.prajwal.bookingservice.dtos.*;
import com.prajwal.bookingservice.repositories.BookingRepository;
import com.prajwal.bookingservice.repositories.DriverRepository;
import com.prajwal.bookingservice.repositories.PassengerRepository;
import com.prajwal.entityservice.models.Booking;
import com.prajwal.entityservice.models.BookingStatus;
import com.prajwal.entityservice.models.Driver;
import com.prajwal.entityservice.models.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {


    //private static final String LOCATION_SERVICE = "http://localhost:7477";

    private final LocationServiceAPI locationServiceAPI;

    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;

    private final RestTemplate restTemplate;
    private final DriverRepository driverRepository;

    public BookingServiceImpl(PassengerRepository passengerRepository,
                              BookingRepository bookingRepository,
                              LocationServiceAPI locationServiceAPI,
                              DriverRepository driverRepository) {

        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
        this.restTemplate = new RestTemplate();
        this.locationServiceAPI = locationServiceAPI;
        this.driverRepository = driverRepository;
    }

    @Override
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO bookingDetails) {
        Optional<Passenger> passengerOptional =
                passengerRepository.findById(bookingDetails.getPassengerId());
        Booking booking = new Booking().builder().bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(bookingDetails.getStartLocation())
                .endLocation(bookingDetails.getEndLocation())
                .passenger(passengerOptional.get())
                .build();
        Booking newBooking = bookingRepository.save(booking);

        // making api call to locationService to fetch nearby  drivers


        NearbyDriversRequestDto request = NearbyDriversRequestDto.builder()
                .latitude(bookingDetails.getStartLocation().getLatitude())
                .longitude(bookingDetails.getStartLocation().getLongitude())
                .build();

        // making async call using retrofit
        processNearByDriversAsync(request);

//        ResponseEntity<DriverLocationDto[]> results = restTemplate.
//                postForEntity(
//                        LOCATION_SERVICE + "/api/v1/location/nearby/drivers",
//                        request,
//                        DriverLocationDto[].class);
//
//
//        List<DriverLocationDto> driverLocations = Arrays.asList(results.getBody());
//        if (results.getStatusCode().is2xxSuccessful() && results.getBody() != null) {
//            driverLocations.forEach(driverLocation ->
//                    System.out.println(driverLocation.getDriverId() + " " + driverLocation.getLatitude() + " " + driverLocation.getLongitude())
//            );
//        }

        // automating above through eureka


        return CreateBookingResponseDTO.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(newBooking.getBookingStatus().toString())
//                .driver(Optional.of(newBooking.getDriver()))
                .build();
    }

    @Override
    public UpdateBooingResponseDto updateBooking(UpdateBookingRequestDto updateBookingRequestDto, Long bookingId) {


        Optional<Driver> driverOptional = driverRepository.findById(updateBookingRequestDto.getDriverId().get());
        bookingRepository.updateBookingStatusAndDriverById(bookingId, updateBookingRequestDto.getStatus(), driverOptional.get());
        Optional<Booking> booking = bookingRepository.findById(bookingId);


        return UpdateBooingResponseDto.builder().booingId(booking.get().getId()).status(booking.get().getBookingStatus())
                .driver(Optional.ofNullable(booking.get().getDriver()))
                .build();
    }

    private void processNearByDriversAsync(NearbyDriversRequestDto requestDto) {
        Call<DriverLocationDto[]> call = locationServiceAPI.getNearbyDrivers(requestDto);

        call.enqueue(new Callback<DriverLocationDto[]>() {
            @Override
            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    String error = "";
                    try {
                        if (response.errorBody() != null) {
                            error = response.errorBody().string();
                        }
                    } catch (IOException ignored) {
                    }
                    System.out.println("Location service returned " + response.code() + " " + error);
                    return;
                }

                List<DriverLocationDto> driverLocations = Arrays.asList(response.body());
                driverLocations.forEach(d ->
                        System.out.println(d.getDriverId() + " " + d.getLatitude() + " " + d.getLongitude()));
            }

            @Override
            public void onFailure(Call<DriverLocationDto[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
