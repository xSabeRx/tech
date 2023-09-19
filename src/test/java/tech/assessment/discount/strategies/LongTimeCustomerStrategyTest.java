package tech.assessment.discount.strategies;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongTimeCustomerStrategyTest {

  LongTimeCustomerStrategy longTimeCustomerStrategy = new LongTimeCustomerStrategy();

  @ParameterizedTest
  @MethodSource("providePriceAndPriceWithDiscount")
  void shouldReturnCorrectPrice(BigDecimal price, BigDecimal priceWithDiscount) {

    BigDecimal result = longTimeCustomerStrategy.applyDiscount(price);

    assertThat(result).isEqualTo(priceWithDiscount);
  }

  @Test
  void shouldReturnTypeOfStrategy() {

    FetchStrategyType strategyType = longTimeCustomerStrategy.getStrategyType();

    assertThat(strategyType).isEqualTo(FetchStrategyType.LONG_TIME);
  }
  private static Stream<Arguments> providePriceAndPriceWithDiscount() {
    return Stream.of(
        Arguments.of(new BigDecimal("90.00"), new BigDecimal("4.50")),
        Arguments.of(new BigDecimal("200.00"), new BigDecimal("10.00")),
        Arguments.of(new BigDecimal("350.00"), new BigDecimal("17.50")),
        Arguments.of(new BigDecimal("404.00"), new BigDecimal("20.20")),
        Arguments.of(new BigDecimal("610.00"), new BigDecimal("30.50")),
        Arguments.of(new BigDecimal("610.60"), new BigDecimal("30.53"))
    );
  }

}