package com.parkinglot.model.entity;

import com.parkinglot.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents an entity named {@link ParkingAreaEntity} for parking area.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARKING_AREA")
public class ParkingAreaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(
            name = "NAME",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "CAPACITY",
            nullable = false
    )
    private Integer capacity;

    @Column(
            name = "CITY",
            nullable = false
    )
    private String city;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "PRICE_LIST_ID",
            referencedColumnName = "ID"
    )
    private PriceListEntity priceListEntity;
}
