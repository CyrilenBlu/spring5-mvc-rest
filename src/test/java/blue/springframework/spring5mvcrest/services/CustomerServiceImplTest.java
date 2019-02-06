package blue.springframework.spring5mvcrest.services;

import blue.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import blue.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import blue.springframework.spring5mvcrest.domain.Customer;
import blue.springframework.spring5mvcrest.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    public static final Long ID = 2L;
    public static final String LASTNAME = "Jackson";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerByLastname() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setLastname(LASTNAME);

        when(customerRepository.findByLastname(anyString())).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByLastname(LASTNAME);

        //then
        assertEquals(ID, customerDTO.getId());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }
}