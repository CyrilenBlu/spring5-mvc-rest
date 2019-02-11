package blue.springframework.spring5mvcrest.controllers.v1;

import blue.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import blue.springframework.spring5mvcrest.api.v1.model.CustomerListDTO;
import blue.springframework.spring5mvcrest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "The Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get a list of customers.", notes = "Some notes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers()
    {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO findById(@PathVariable Long id)
    {
        return customerService.getCustomerById(id);
    }

//    @GetMapping("{lastname}")
//    public ResponseEntity<CustomerDTO> findByLastname(@PathVariable String lastname)
//    {
//        return new ResponseEntity<CustomerDTO>(
//                customerService.getCustomerByLastname(lastname), HttpStatus.OK
//        );
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id)
    {
        customerService.deleteCustomerById(id);
    }
}
