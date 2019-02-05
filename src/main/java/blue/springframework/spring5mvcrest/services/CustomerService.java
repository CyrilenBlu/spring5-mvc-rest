package blue.springframework.spring5mvcrest.services;

import blue.springframework.spring5mvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByLastname(String lastname);
}
