package com.prajwal.bookingservice.controllers;

import com.prajwal.bookingservice.dtos.CreateBookingRequestDTO;
import com.prajwal.bookingservice.dtos.CreateBookingResponseDTO;
import com.prajwal.bookingservice.dtos.UpdateBooingResponseDto;
import com.prajwal.bookingservice.dtos.UpdateBookingRequestDto;
import com.prajwal.bookingservice.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDTO> createBooking(@RequestBody CreateBookingRequestDTO createBookingRequestDTO) {

        CreateBookingResponseDTO result = bookingService.createBooking(createBookingRequestDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<UpdateBooingResponseDto> updateBooing(@RequestBody UpdateBookingRequestDto updateBookingRequestDto, @PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.updateBooking(updateBookingRequestDto, bookingId), HttpStatus.OK);
    }

}
