package blue.springframework.spring5mvcrest.api.v1.mapper;

import blue.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import blue.springframework.spring5mvcrest.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String LASTNAME = "Tone";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO()
    {
        //given
        Customer customer = new Customer();
        customer.setLastname(LASTNAME);
        customer.setId(1L);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Tone", customerDTO.getLastname());
    }

}