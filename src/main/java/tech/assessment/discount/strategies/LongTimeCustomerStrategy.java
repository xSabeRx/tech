package tech.assessment.discount.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class LongTimeCustomerStrategy implements DiscountStrategy {

  private static final BigDecimal LONG_TIME_CUSTOMER_DISCOUNT_PERCENT = new BigDecimal("0.05");

  @Override
  public FetchStrategyType getStrategyType() {
    return FetchStrategyType.LONG_TIME;
  }

  @Override
  public BigDecimal applyDiscount(BigDecimal price) {
    return price.multiply(LONG_TIME_CUSTOMER_DISCOUNT_PERCENT).setScale(2, RoundingMode.HALF_UP);
  }
}