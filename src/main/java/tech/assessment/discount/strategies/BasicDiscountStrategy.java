package tech.assessment.discount.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class BasicDiscountStrategy implements DiscountStrategy {

  public static final int HUNDRED = 100;

  private static final int BASIC_DISCOUNT = 5;

  @Override
  public FetchStrategyType getStrategyType() {
    return FetchStrategyType.BASIC;
  }

  @Override
  public BigDecimal applyDiscount(BigDecimal price) {

    BigDecimal value = price.setScale(0, RoundingMode.UP);
    if (value.intValue() > HUNDRED) {
      int numberOfDiscount = price.divide(new BigDecimal(HUNDRED)).intValue();
      int discount = numberOfDiscount * BASIC_DISCOUNT;
      return new BigDecimal(discount).setScale(2, RoundingMode.HALF_UP);
    }
    return BigDecimal.ZERO;
  }


}
