package tech.assessment.discount.strategies;

import java.math.BigDecimal;

public interface DiscountStrategy {

  FetchStrategyType getStrategyType();

  BigDecimal applyDiscount(BigDecimal price);
}