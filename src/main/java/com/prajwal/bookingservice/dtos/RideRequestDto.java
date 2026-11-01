package com.prajwal.bookingservice.dtos;

import com.prajwal.entityservice.models.ExactLocation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideRequestDto {

    private Long passengerId;
    private ExactLocation startLocation;
    private ExactLocation endLocation;
    private List<Long> nearbyDriverIds;
}
