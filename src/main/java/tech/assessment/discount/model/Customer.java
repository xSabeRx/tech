package tech.assessment.discount.model;

import java.time.LocalDate;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document(collection = "customers")
public class Customer {

  @Id
  int id;
  CustomerType customerType;
  String name;
  String lastName;
  LocalDate createdDate;
}
