package tech.assessment.discount.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tech.assessment.discount.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

  @Query("{ '_id' : ?0 } ")
  Optional<Customer> findCustomerById(int id);

}
