package com.parkinglot.model.dto.request.parking_area;

import com.parkinglot.model.dto.request.priceLIst.PriceListCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A request class named {@link ParkingAreaCreateRequest} representing the update of a parking area.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingAreaCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Min(value = 0)
    private Integer capacity;

    @NotBlank
    private String city;

    @Valid
    @NotNull
    private PriceListCreateRequest priceListRequest;
}
