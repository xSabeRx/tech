package tech.assessment.discount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import tech.assessment.discount.model.Bill;
import tech.assessment.discount.model.Customer;
import tech.assessment.discount.model.CustomerType;
import tech.assessment.discount.model.Product;
import tech.assessment.discount.model.ProductType;

public class Helper {

  public static List<Product> products() {
    return List.of(new Product("Pen", ProductType.OTHER, new BigDecimal("25.50")),
        new Product("Book", ProductType.OTHER, new BigDecimal("125.50")),
        new Product("Chair", ProductType.OTHER, new BigDecimal("205.50")),
        new Product("Carrot", ProductType.GROCERIES, new BigDecimal("6.50")),
        new Product("Orange", ProductType.GROCERIES, new BigDecimal("15.50")),
        new Product("Tomato", ProductType.GROCERIES, new BigDecimal("9.50"))
    );
  }

  public static List<Product> productsWithLowPrice() {
    return List.of(new Product("Pen", ProductType.OTHER, new BigDecimal("2.50")),
        new Product("Book", ProductType.OTHER, new BigDecimal("3.50")),
        new Product("Chair", ProductType.OTHER, new BigDecimal("4.50")),
        new Product("Carrot", ProductType.GROCERIES, new BigDecimal("6.50")),
        new Product("Orange", ProductType.GROCERIES, new BigDecimal("5.50")),
        new Product("Tomato", ProductType.GROCERIES, new BigDecimal("9.50"))
    );
  }

  public static Customer affiliateCustomer() {
    return new Customer(1, CustomerType.AFFILIATE, "Jack", "Jous", LocalDate.now());
  }

  public static Customer employeeCustomer() {
    return new Customer(2, CustomerType.EMPLOYEE, "Jack", "Jous", LocalDate.now());
  }

  public static Customer longTimeCustomer() {
    return new Customer(3, CustomerType.NORMAL, "Jack", "Jous", LocalDate.now().minusYears(3));
  }

  public static Customer basicCustomer() {
    return new Customer(4, CustomerType.NORMAL, "Jack", "Jous", LocalDate.now());
  }

  public static Bill billWithLowPrice() {
    return new Bill(productsWithLowPrice());
  }

  public static Bill billWithHighPrice() {
    return new Bill(products());
  }
}
