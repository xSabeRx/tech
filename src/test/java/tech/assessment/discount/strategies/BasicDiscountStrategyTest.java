package tech.assessment.discount.strategies;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasicDiscountStrategyTest {

  BasicDiscountStrategy basicDiscountStrategy = new BasicDiscountStrategy();

  @ParameterizedTest
  @MethodSource("providePriceAndPriceWithDiscount")
  void shouldReturnCorrectPrice(BigDecimal price, BigDecimal priceWithDiscount) {

    BigDecimal result = basicDiscountStrategy.applyDiscount(price);

    assertThat(result).isEqualTo(priceWithDiscount);
  }

  @Test
  void shouldReturnTypeOfStrategy() {

    FetchStrategyType strategyType = basicDiscountStrategy.getStrategyType();

    assertThat(strategyType).isEqualTo(FetchStrategyType.BASIC);
  }

  private static Stream<Arguments> providePriceAndPriceWithDiscount() {
    return Stream.of(
        Arguments.of(new BigDecimal(90), new BigDecimal(0)),
        Arguments.of(new BigDecimal(200), new BigDecimal("10.00")),
        Arguments.of(new BigDecimal(350), new BigDecimal("15.00")),
        Arguments.of(new BigDecimal(404), new BigDecimal("20.00")),
        Arguments.of(new BigDecimal(610), new BigDecimal("30.00")),
        Arguments.of(new BigDecimal("610.60"), new BigDecimal("30.00"))
    );
  }
}