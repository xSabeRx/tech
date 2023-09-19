package tech.assessment.discount.strategies;

import java.time.LocalDate;
import tech.assessment.discount.model.Customer;
import tech.assessment.discount.model.CustomerType;

public enum FetchStrategyType {

  EMPLOYEE, AFFILIATE, LONG_TIME, BASIC, NO_DISCOUNT;

  public static FetchStrategyType from(Customer customer) {
    CustomerType customerType = customer.getCustomerType();

    if (customerType.equals(CustomerType.EMPLOYEE)) {
      return EMPLOYEE;
    }

    if (customerType.equals(CustomerType.AFFILIATE)) {
      return AFFILIATE;
    }

    if (customer.getCreatedDate().isBefore(LocalDate.now().minusYears(2))) {
      return LONG_TIME;
    }

    return NO_DISCOUNT;
  }
}
