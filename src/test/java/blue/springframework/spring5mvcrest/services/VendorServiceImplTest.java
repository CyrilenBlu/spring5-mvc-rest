package blue.springframework.spring5mvcrest.services;

import blue.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import blue.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import blue.springframework.spring5mvcrest.controllers.v1.VendorController;
import blue.springframework.spring5mvcrest.domain.Vendor;
import blue.springframework.spring5mvcrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(vendorDTOS.size(), 2);
    }

    @Test
    public void getVendorById() {
        //given
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Andy");

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals(vendorDTO.getName(), "Andy");
    }

    @Test
    public void createNewVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Luke");

        Vendor savedVendor = new Vendor();
        savedVendor.setId(1L);
        savedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(savedDTO.getName(), "Luke");
        assertEquals(VendorController.BASE_URL + "1", savedDTO.getVendorURL());
    }

    @Test
    public void saveVendorByDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Ken");

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals(savedDTO.getName(), "Ken");
        assertEquals(VendorController.BASE_URL + "1", savedDTO.getVendorURL());
    }

    @Test
    public void patchVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Rob");

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(vendorDTO.getName());

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedDTO = vendorService.patchVendor(1L, vendorDTO);

        assertEquals(savedDTO.getName(), "Rob");
        assertEquals(VendorController.BASE_URL + "1", savedDTO.getVendorURL());
    }

    @Test
    public void deleteVendorById() {
        Long id = 1L;

        vendorService.deleteVendorById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}