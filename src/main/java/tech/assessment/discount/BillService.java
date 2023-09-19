package tech.assessment.discount;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.assessment.discount.model.Bill;
import tech.assessment.discount.model.Customer;
import tech.assessment.discount.model.Product;
import tech.assessment.discount.model.ProductType;
import tech.assessment.discount.strategies.DiscountStrategy;
import tech.assessment.discount.strategies.FetchStrategyType;

@Service
@AllArgsConstructor
public class BillService {

  private final List<DiscountStrategy> discountStrategies;

  public BigDecimal calculateBill(Bill bill, Customer customer) {
    FetchStrategyType strategyType = FetchStrategyType.from(customer);

    DiscountStrategy percentageStrategy = getPercentageStrategy(strategyType);
    DiscountStrategy basicStrategy = getBasicStrategy();

    if (applyStrategy(percentageStrategy)) {
      BigDecimal priceWithoutGroceries = getPriceWithoutGroceries(bill);
      BigDecimal percentageDiscount = percentageStrategy.applyDiscount(priceWithoutGroceries);
      BigDecimal price = getPrice(bill).subtract(percentageDiscount);

      if (price.compareTo(new BigDecimal("100.00")) > 0) {
        BigDecimal basicDiscount = basicStrategy.applyDiscount(price);
        return price.subtract(basicDiscount).setScale(2);
      }
      return price;
    }

    if (applyStrategy(basicStrategy)) {
      BigDecimal price = getPrice(bill);
      BigDecimal discount = basicStrategy.applyDiscount(price);
      return price.subtract(discount).setScale(2);
    }

    return getPrice(bill).setScale(2);
  }

  private static boolean applyStrategy(DiscountStrategy strategy) {
    return strategy != null;
  }

  private DiscountStrategy getPercentageStrategy(FetchStrategyType strategyType) {
    return discountStrategies.stream()
        .filter(strategy -> strategyType == strategy.getStrategyType())
        .findFirst()
        .orElse(null);
  }

  private DiscountStrategy getBasicStrategy() {
    return discountStrategies.stream()
        .filter(strategy -> FetchStrategyType.BASIC == strategy.getStrategyType())
        .findFirst()
        .orElse(null);
  }

  private BigDecimal getPriceWithoutGroceries(Bill bill) {
    return bill.getProducts().stream()
        .filter(product -> product.getProductType().equals(ProductType.OTHER))
        .map(Product::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getPrice(Bill bill) {
    return bill.getProducts().stream()
        .map(Product::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}