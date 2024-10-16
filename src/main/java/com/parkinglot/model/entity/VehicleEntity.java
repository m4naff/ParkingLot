package com.parkinglot.model.entity;

import com.parkinglot.common.model.entity.BaseEntity;
import com.parkinglot.model.enums.VehicleType;
import com.parkinglot.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents an entity named {@link VehicleEntity} for vehicle.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VEHICLE")
public class VehicleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(
            name = "LICENSE_PLATE",
            nullable = false,
            unique = true,
            length = 10
    )
    private String licensePlate;

    @Column(name = "VEHICLE_TYPE")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "vehicleEntity"
    )
    private List<ParkEntity> parkEntities;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "USER_ID",
            referencedColumnName = "ID"
    )
    private UserEntity userEntity;
}
