package com.prajwal.bookingservice.services;

import com.prajwal.bookingservice.dtos.CreateBookingRequestDTO;
import com.prajwal.bookingservice.dtos.CreateBookingResponseDTO;
import com.prajwal.entityservice.models.Booking;

public interface BookingService {

     public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO bookingDetails);

}
