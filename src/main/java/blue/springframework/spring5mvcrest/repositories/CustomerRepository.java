package blue.springframework.spring5mvcrest.repositories;

import blue.springframework.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByLastname(String lastname);
}
