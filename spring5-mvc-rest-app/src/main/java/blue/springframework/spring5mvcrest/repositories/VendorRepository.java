package blue.springframework.spring5mvcrest.repositories;

import blue.springframework.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
