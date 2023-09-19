package tech.assessment.discount.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class EmployeeStrategy implements DiscountStrategy {

  private static final BigDecimal EMPLOYEE_DISCOUNT_PERCENT = new BigDecimal("0.30");

  @Override
  public FetchStrategyType getStrategyType() {
    return FetchStrategyType.EMPLOYEE;
  }

  @Override
  public BigDecimal applyDiscount(BigDecimal price) {
    return price.multiply(EMPLOYEE_DISCOUNT_PERCENT).setScale(2, RoundingMode.HALF_UP);
  }
}
