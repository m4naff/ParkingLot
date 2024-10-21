package com.parkinglot.model.dto.response.park;

import com.parkinglot.common.model.BaseDomainModel;
import com.parkinglot.model.dto.response.vehicle.VehicleCheckInResponse;
import com.parkinglot.model.enums.ParkStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * A response class named {@link ParkCheckInResponse} representing the check-in response.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParkCheckInResponse extends BaseDomainModel {

    private String parkingAreaId;
    private VehicleCheckInResponse vehicleCheckInResponse;
    private ParkStatus parkStatus;
    private LocalDateTime checkIn;
}
