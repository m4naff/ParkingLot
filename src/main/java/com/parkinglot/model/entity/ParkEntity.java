package com.parkinglot.model.entity;

import com.parkinglot.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents an entity named {@link ParkEntity} for park.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARK")
public class ParkEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "CHECK_IN")
    private LocalDateTime checkIn;

    @Column(name = "CHECK_OUT")
    private LocalDateTime checkOut;

    @Column(
            name = "TOTAL_COST",
            nullable = false,
            scale = 24, // scale is the total number of digits
            precision = 4 // precision is what comes after the dot
    )
    private BigDecimal totalCost;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "PARKING_AREA_ID",
            referencedColumnName = "ID"
    )
    private ParkingAreaEntity parkingAreaEntity;


}
