package com.parkinglot.model.entity;

import com.parkinglot.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents an entity named {@link PriceListEntity} for price list.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "PRICE_LIST")
public class PriceListEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(
            name = "NAME",
            nullable = false
    )
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "priceListEntity"
    )
    private List<PriceEntity> priceEntities;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "priceListEntity"
    )
    private List<ParkingAreaEntity> parkingAreaEntities;

}
