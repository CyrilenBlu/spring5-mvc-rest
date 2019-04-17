package blue.springframework.spring5mvcrest.api.v1.mapper;

import blue.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import blue.springframework.spring5mvcrest.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Jack");

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendorDTO.getId(), Long.valueOf(1L));
        assertEquals(vendorDTO.getName(), "Jack");
    }
}