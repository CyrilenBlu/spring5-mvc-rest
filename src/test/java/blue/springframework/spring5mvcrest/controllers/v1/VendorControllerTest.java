package blue.springframework.spring5mvcrest.controllers.v1;

import blue.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import blue.springframework.spring5mvcrest.services.ResourceNotFoundException;
import blue.springframework.spring5mvcrest.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest extends AbstractRestControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllVendors() throws Exception {

        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(1l);
        vendor1.setName("Hey");

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setId(2l);
        vendor2.setName("Yo");

        List<VendorDTO> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getVendorById() throws Exception {
        Long id = 1L;

        VendorDTO vendor = new VendorDTO();
        vendor.setId(id);
        vendor.setName("Jack");

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor);

        mockMvc.perform(get(VendorController.BASE_URL + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Bob");

        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(vendor);

        mockMvc.perform(post(VendorController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Bob")));
    }

    @Test
    public void updateVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setId(1L);
        vendor.setName("Lenny");
        vendor.setVendorURL(VendorController.BASE_URL + "1");

        when(vendorService.saveVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(vendor);

        mockMvc.perform(put(VendorController.BASE_URL + "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Lenny")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "1")));
    }

    @Test
    public void patchVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Jill");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorURL(VendorController.BASE_URL + "1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(VendorController.BASE_URL + "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Jill")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "1")));
    }

    @Test
    public void deleteVendor() throws Exception {

        mockMvc.perform(delete(VendorController.BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception
    {
        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.BASE_URL + "250")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}