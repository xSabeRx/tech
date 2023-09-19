package tech.assessment.discount.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class AffiliateStrategy implements DiscountStrategy {

  private static final BigDecimal AFFILIATE_DISCOUNT_PERCENT = new BigDecimal("0.10");

  @Override
  public FetchStrategyType getStrategyType() {
    return FetchStrategyType.AFFILIATE;
  }

  @Override
  public BigDecimal applyDiscount(BigDecimal price) {
    return price.multiply(AFFILIATE_DISCOUNT_PERCENT).setScale(2, RoundingMode.HALF_UP);
  }
}
