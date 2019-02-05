package blue.springframework.spring5mvcrest.bootstrap;

import blue.springframework.spring5mvcrest.domain.Category;
import blue.springframework.spring5mvcrest.domain.Customer;
import blue.springframework.spring5mvcrest.repositories.CategoryRepository;
import blue.springframework.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data Loaded = " + categoryRepository.count() );

        Customer customer1 = new Customer();
        customer1.setFirstname("Susan");
        customer1.setLastname("Tanner");

        Customer customer2 = new Customer();
        customer2.setFirstname("Jake");
        customer2.setLastname("Systen");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        System.out.println("Data Loaded = " + categoryRepository.count() );


    }
}
