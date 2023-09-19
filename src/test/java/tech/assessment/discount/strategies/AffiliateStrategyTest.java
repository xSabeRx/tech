package tech.assessment.discount.strategies;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AffiliateStrategyTest {
  AffiliateStrategy affiliateStrategy = new AffiliateStrategy();

  @ParameterizedTest
  @MethodSource("providePriceAndDiscount")
  void shouldReturnCorrectDiscount(BigDecimal price, BigDecimal discount) {

    BigDecimal result = affiliateStrategy.applyDiscount(price);

    assertThat(result).isEqualTo(discount);
  }

  @Test
  void shouldReturnTypeOfStrategy() {

    FetchStrategyType strategyType = affiliateStrategy.getStrategyType();

    assertThat(strategyType).isEqualTo(FetchStrategyType.AFFILIATE);
  }

  private static Stream<Arguments> providePriceAndDiscount() {
    return Stream.of(
        Arguments.of(new BigDecimal("90.00"), new BigDecimal("9.00")),
        Arguments.of(new BigDecimal("200.00"), new BigDecimal("20.00")),
        Arguments.of(new BigDecimal("350.00"), new BigDecimal("35.00")),
        Arguments.of(new BigDecimal("404.00"), new BigDecimal("40.40")),
        Arguments.of(new BigDecimal("610.00"), new BigDecimal("61.00")),
        Arguments.of(new BigDecimal("610.60"), new BigDecimal("61.06"))
    );
  }

}