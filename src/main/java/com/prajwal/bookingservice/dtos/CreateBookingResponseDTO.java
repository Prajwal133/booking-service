package com.prajwal.bookingservice.dtos;

import com.prajwal.entityservice.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingResponseDTO {
    private long bookingId;
    private String bookingStatus;
    private Optional<Driver> driver;
}
