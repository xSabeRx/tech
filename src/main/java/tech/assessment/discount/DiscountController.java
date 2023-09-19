package tech.assessment.discount;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.assessment.CustomerNotFoundException;
import tech.assessment.discount.model.Amount;
import tech.assessment.discount.model.Bill;
import tech.assessment.discount.model.Customer;
import tech.assessment.discount.repositories.CustomerRepository;

@RestController
@AllArgsConstructor
public class DiscountController {

  private final BillService billService;
  private final CustomerRepository customerRepository;

  @PostMapping("/customers/{id}/bill")
  public ResponseEntity<Amount> provideAmount(@PathVariable int id, @RequestBody Bill bill) {
    Customer customer = customerRepository.findCustomerById(id)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given Id"));
    BigDecimal price = billService.calculateBill(bill, customer);
    return ResponseEntity.status(HttpStatus.OK).body(new Amount(price));
  }
}
