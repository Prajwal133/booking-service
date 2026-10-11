package com.prajwal.bookingservice.controllers;

import com.prajwal.bookingservice.dtos.CreateBookingRequestDTO;
import com.prajwal.bookingservice.dtos.CreateBookingResponseDTO;
import com.prajwal.bookingservice.services.BookingService;
import com.prajwal.entityservice.models.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDTO> createBooking(@RequestBody CreateBookingRequestDTO  createBookingRequestDTO ) {

        CreateBookingResponseDTO result =bookingService.createBooking(createBookingRequestDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
