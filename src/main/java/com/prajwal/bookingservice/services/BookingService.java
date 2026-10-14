package com.prajwal.bookingservice.services;

import com.prajwal.bookingservice.dtos.CreateBookingRequestDTO;
import com.prajwal.bookingservice.dtos.CreateBookingResponseDTO;
import com.prajwal.bookingservice.dtos.UpdateBooingResponseDto;
import com.prajwal.bookingservice.dtos.UpdateBookingRequestDto;

public interface BookingService {

    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO bookingDetails);

    public UpdateBooingResponseDto updateBooking(UpdateBookingRequestDto updateBookingRequestDto, Long bookingId);
}
