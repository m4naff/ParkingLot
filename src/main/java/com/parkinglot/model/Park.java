package com.parkinglot.model;

import com.parkinglot.common.model.BaseDomainModel;
import com.parkinglot.model.enums.ParkStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a park domain model object named {@link Park}.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Park extends BaseDomainModel {

    private String id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BigDecimal totalCost;
    private ParkingArea parkingArea;
    private Vehicle vehicle;
    private ParkStatus parkStatus;

}

