package com.prajwal.bookingservice.dtos;

import com.prajwal.entityservice.models.BookingStatus;
import com.prajwal.entityservice.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBooingResponseDto {
    private Long booingId;
    private BookingStatus status;
    private Optional<Driver> driver;
}
