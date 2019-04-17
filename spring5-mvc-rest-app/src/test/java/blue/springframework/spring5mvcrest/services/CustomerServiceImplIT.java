package blue.springframework.spring5mvcrest.services;

import blue.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import blue.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import blue.springframework.spring5mvcrest.bootstrap.Bootstrap;
import blue.springframework.spring5mvcrest.domain.Customer;
import blue.springframework.spring5mvcrest.repositories.CategoryRepository;
import blue.springframework.spring5mvcrest.repositories.CustomerRepository;
import blue.springframework.spring5mvcrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception{
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        //setup
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstname() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstname());
        //assertNotEquals(originalFirstname, updatedCustomer.getFirstname());
        assertThat(originalFirstname, not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastname, equalTo(updatedCustomer.getLastname()));
    }

    @Test
    public void patchCustomerUpdateLastname() throws Exception {

    }

    private Long getCustomerIdValue()
    {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers found: " + customers.size());

        //return first id
        return customers.get(0).getId();
    }
}
