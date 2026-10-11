package com.prajwal.bookingservice.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearbyDriversRequestDto {
    double longitude;
    double  latitude;
}
