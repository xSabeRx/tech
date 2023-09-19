package tech.assessment.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.assessment.discount.model.Bill;
import tech.assessment.discount.strategies.AffiliateStrategy;
import tech.assessment.discount.strategies.DiscountStrategy;
import tech.assessment.discount.strategies.EmployeeStrategy;
import tech.assessment.discount.strategies.FetchStrategyType;
import tech.assessment.discount.strategies.LongTimeCustomerStrategy;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

  @Mock
  AffiliateStrategy affiliateStrategy;

  @Mock
  EmployeeStrategy employeeStrategy;

  @Mock
  DiscountStrategy basicDiscountStrategy;

  @Mock
  LongTimeCustomerStrategy longTimeCustomerStrategy;

  BillService billService;


  @Nested
  class BasicDiscount {

    @Test
    void shouldCalculatePriceOnlyWithBasicDiscount() {
      when(basicDiscountStrategy.getStrategyType()).thenReturn(FetchStrategyType.BASIC);
      when(basicDiscountStrategy.applyDiscount(any())).thenReturn(new BigDecimal("30"));
      billService = new BillService(List.of(basicDiscountStrategy));
      Bill bill = new Bill(Helper.products());

      BigDecimal result = billService.calculateBill(bill, Helper.basicCustomer());

      assertThat(result).isEqualTo(new BigDecimal("358.00"));
    }

    @Test
    void shouldCalculateWithoutDiscount() {
      billService = new BillService(Collections.emptyList());

      Bill bill = new Bill(Helper.productsWithLowPrice());

      BigDecimal result = billService.calculateBill(bill, Helper.basicCustomer());

      assertThat(result).isEqualTo(new BigDecimal("32.00"));
    }
  }

  @Nested
  class AffiliateCustomer {

    @Test
    void shouldCalculatePriceForAffiliateWithoutBasicDiscount() {
      when(affiliateStrategy.applyDiscount(any())).thenReturn(new BigDecimal("5"));
      when(affiliateStrategy.getStrategyType()).thenReturn(FetchStrategyType.AFFILIATE);
      billService = new BillService(List.of(affiliateStrategy));
      Bill bill = new Bill(Helper.productsWithLowPrice());

      BigDecimal result = billService.calculateBill(bill, Helper.affiliateCustomer());

      assertThat(result).isEqualTo(new BigDecimal("27.00"));
    }

    @Test
    void shouldCalculatePriceForAffiliateWithBasicDiscount() {
      when(affiliateStrategy.applyDiscount(any())).thenReturn(new BigDecimal("5"));
      when(affiliateStrategy.getStrategyType()).thenReturn(FetchStrategyType.AFFILIATE);
      when(basicDiscountStrategy.getStrategyType()).thenReturn(FetchStrategyType.BASIC);
      when(basicDiscountStrategy.applyDiscount(any())).thenReturn(new BigDecimal("30"));
      billService = new BillService(List.of(affiliateStrategy, basicDiscountStrategy));
      Bill bill = new Bill(Helper.products());

      BigDecimal result = billService.calculateBill(bill, Helper.affiliateCustomer());

      assertThat(result).isEqualTo(new BigDecimal("353.00"));
    }
  }

  @Nested
  class EmployeeCustomer {

    @Test
    void shouldCalculatePriceForEmployeeWithoutBasicDiscount() {
      when(employeeStrategy.applyDiscount(any())).thenReturn(new BigDecimal("15"));
      when(employeeStrategy.getStrategyType()).thenReturn(FetchStrategyType.EMPLOYEE);
      billService = new BillService(List.of(employeeStrategy));
      Bill bill = new Bill(Helper.productsWithLowPrice());

      BigDecimal result = billService.calculateBill(bill, Helper.employeeCustomer());

      assertThat(result).isEqualTo(new BigDecimal("17.00"));
    }

    @Test
    void shouldCalculatePriceForEmployeeWithBasicDiscount() {
      when(employeeStrategy.applyDiscount(any())).thenReturn(new BigDecimal("15"));
      when(employeeStrategy.getStrategyType()).thenReturn(FetchStrategyType.EMPLOYEE);
      when(basicDiscountStrategy.getStrategyType()).thenReturn(FetchStrategyType.BASIC);
      when(basicDiscountStrategy.applyDiscount(any())).thenReturn(new BigDecimal("30"));
      billService = new BillService(List.of(employeeStrategy, basicDiscountStrategy));
      Bill bill = new Bill(Helper.products());

      BigDecimal result = billService.calculateBill(bill, Helper.employeeCustomer());

      assertThat(result).isEqualTo(new BigDecimal("343.00"));
    }
  }

  @Nested
  class LongTimeCustomer {

    @Test
    void shouldCalculatePriceForLongCustomerWithoutBasicDiscount() {
      when(longTimeCustomerStrategy.applyDiscount(any())).thenReturn(new BigDecimal("2"));
      when(longTimeCustomerStrategy.getStrategyType()).thenReturn(FetchStrategyType.LONG_TIME);
      billService = new BillService(List.of(longTimeCustomerStrategy));
      Bill bill = new Bill(Helper.productsWithLowPrice());
      BigDecimal result = billService.calculateBill(bill, Helper.longTimeCustomer());

      assertThat(result).isEqualTo(new BigDecimal("30.00"));
    }

    @Test
    void shouldCalculatePriceForLongCustomerWithBasicDiscount() {
      when(longTimeCustomerStrategy.applyDiscount(any())).thenReturn(new BigDecimal("2"));
      when(longTimeCustomerStrategy.getStrategyType()).thenReturn(FetchStrategyType.EMPLOYEE);
      when(basicDiscountStrategy.getStrategyType()).thenReturn(FetchStrategyType.BASIC);
      when(basicDiscountStrategy.applyDiscount(any())).thenReturn(new BigDecimal("30"));
      billService = new BillService(
          List.of(longTimeCustomerStrategy, basicDiscountStrategy));
      Bill bill = new Bill(Helper.products());

      BigDecimal result = billService.calculateBill(bill, Helper.employeeCustomer());

      assertThat(result).isEqualTo(new BigDecimal("356.00"));
    }
  }
}