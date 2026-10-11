package com.prajwal.bookingservice.dtos;

import com.prajwal.entityservice.models.ExactLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingRequestDTO {

private Long passengerId;
private ExactLocation startLocation;
private ExactLocation endLocation;

}
