package com.parkinglot.model.dto.response.vehicle;

import com.parkinglot.model.enums.VehicleType;
import lombok.*;

/**
 * A response class named {@link VehicleCheckOutResponse} representing the vehicle check-out response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleCheckOutResponse {

    private String licensePlate;
    private VehicleType vehicleType;
}
