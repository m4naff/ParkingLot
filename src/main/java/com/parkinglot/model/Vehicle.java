package com.parkinglot.model;

import com.parkinglot.common.model.BaseDomainModel;
import com.parkinglot.model.enums.VehicleType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents a vehicle domain model object named {@link Vehicle}.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseDomainModel{
    private String id;
    private String licensePlate;
    private VehicleType vehicleType;
    private List<Park> parkList;
    private User user;
}
