package com.parkinglot.utils.price;

import java.math.BigDecimal;

/**
 * Strategy implementation class named {@link SedanFeeCalculationStrategy} for calculating parking fees for Sedan vehicles.
 */
public class SedanFeeCalculationStrategy implements FeeCalculationStrategy {

    private static final double SEDAN_FEE_RATE = 0;

    /**
     * Calculates the total parking price for Sedan vehicles.
     *
     * @param price the base price
     * @return the total price including the fee
     */
    @Override
    public BigDecimal calculatePrice(BigDecimal price) {
        BigDecimal fee = price.multiply(BigDecimal.valueOf(SEDAN_FEE_RATE));

        return price.add(fee);
    }
}
