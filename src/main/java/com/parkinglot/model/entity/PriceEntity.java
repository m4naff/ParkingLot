package com.parkinglot.model.entity;

import com.parkinglot.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Represents an entity named {@link PriceEntity} for price.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRICE")
public class PriceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(
            name = "LOWER_BOUND",
            nullable = false
    )
    private Integer lowerBound;

    @Column(
            name = "UPPER_BOUND",
            nullable = false
    )
    private Integer upperBound;

    @Column(
            name = "COST",
            nullable = false,
            scale = 24,
            precision = 4
    )
    private BigDecimal cost;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "PRICE_LIST_ID",
            referencedColumnName = "ID"
    )
    private PriceListEntity priceListEntity;
}
