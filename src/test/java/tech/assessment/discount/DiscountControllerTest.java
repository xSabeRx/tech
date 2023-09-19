package tech.assessment.discount;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.assessment.CustomerNotFoundException;
import tech.assessment.discount.model.Amount;
import tech.assessment.discount.model.Bill;
import tech.assessment.discount.model.Customer;
import tech.assessment.discount.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class DiscountControllerTest {

  @Mock
  BillService billService;

  @Mock
  CustomerRepository customerRepository;

  DiscountController discountController;

  @BeforeEach
  void setup() {
    discountController = new DiscountController(billService, customerRepository);
  }

  @Test
  void shouldReturnStatusOkWithPrice() {
    Customer customer = Helper.basicCustomer();
    when(customerRepository.findCustomerById(1)).thenReturn(Optional.of(customer));
    Bill bill = Helper.billWithLowPrice();
    when(billService.calculateBill(bill, customer)).thenReturn(new BigDecimal("30.00"));

    ResponseEntity<Amount> result = discountController.provideAmount(1, bill);

    Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(result.getBody().price()).isEqualTo(new BigDecimal("30.00"));
  }

  @Test
  void shouldThrowExceptionCustomerNotFound() {
    when(customerRepository.findCustomerById(5)).thenThrow(new CustomerNotFoundException("Customer not found"));
    Bill bill = Helper.billWithLowPrice();

    assertThrows(CustomerNotFoundException.class, () -> discountController.provideAmount(5, bill));
  }
}