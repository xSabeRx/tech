package tech.assessment.discount.strategies;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EmployeeStrategyTest {

  EmployeeStrategy employeeStrategy = new EmployeeStrategy();

  @ParameterizedTest
  @MethodSource("providePriceAndPriceWithDiscount")
  void shouldReturnCorrectPrice(BigDecimal price, BigDecimal priceWithDiscount) {

    BigDecimal result = employeeStrategy.applyDiscount(price);

    assertThat(result).isEqualTo(priceWithDiscount);
  }

  @Test
  void shouldReturnTypeOfStrategy() {

    FetchStrategyType strategyType = employeeStrategy.getStrategyType();

    assertThat(strategyType).isEqualTo(FetchStrategyType.EMPLOYEE);
  }

  private static Stream<Arguments> providePriceAndPriceWithDiscount() {
    return Stream.of(
        Arguments.of(new BigDecimal("90.00"), new BigDecimal("27.00")),
        Arguments.of(new BigDecimal("200.00"), new BigDecimal("60.00")),
        Arguments.of(new BigDecimal("350.00"), new BigDecimal("105.00")),
        Arguments.of(new BigDecimal("404.00"), new BigDecimal("121.20")),
        Arguments.of(new BigDecimal("610.00"), new BigDecimal("183.00")),
        Arguments.of(new BigDecimal("610.60"), new BigDecimal("183.18"))
    );
  }

}