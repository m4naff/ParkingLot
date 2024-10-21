package com.parkinglot.model.dto.response.park;

import com.parkinglot.common.model.BaseDomainModel;
import com.parkinglot.model.dto.response.vehicle.VehicleCheckOutResponse;
import com.parkinglot.model.enums.ParkStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A response class named {@link ParkCheckOutResponse} representing the check-out response.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParkCheckOutResponse extends BaseDomainModel {

    private String parkingAreaId;
    private String parkingAreaName;
    private VehicleCheckOutResponse vehicleCheckOutResponse;
    private ParkStatus parkStatus;
    private LocalDateTime checkOut;
    private BigDecimal totalCost;
}